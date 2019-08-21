/* Jeremy Holloway
 * CPSC-3220-001
 * Project 4
 * 6/18/2019
 */

#include <iostream>
#include <fstream>
#include <string>
#include <cstdlib>
#include "paging.h"

void config(){
    std::ifstream file;
    file.open("paging.cfg");
    int vals[3];
    std::string in;
    for(int i = 0; i < 3; i++){
        file >> in;
        file >> in;
        vals[i] = std::stoi(in);
    }
    pageFrames = vals[0];
    TLBentries = vals[1];
    shifts = vals[2];
    memory_access = 1;
    TLB_misses = 0;
    frameNumber = 0;
    TLB = new TLBE[TLBentries];
    pageTable = new PTE[65536];
    coreMap = new CME[pageFrames];
    for(int i = 0; i < pageFrames; i++){
        coreMap[i].use_vector = 0;
        coreMap[i].valid = 0;
        coreMap[i].vpn = 0;
    }
    for(int i = 0; i < TLBentries; i++){
        TLB[i].pfn = 0;
        TLB[i].valid = 0;
        TLB[i].vpn = 0;
    }
    TLB_index = 0;
    return;
}

void table_init(){
    for(int i = 0; i < max_size; i++){
        pageTable[i].pfn = 0;
        pageTable[i].presence = 0;
    }
    return;
}

unsigned int get_vpn(unsigned int n){
    unsigned int x = 0b111111111111111100000000;
    x &= n;
    return x >>= 8;
}

unsigned int get_offset(unsigned int n){
    unsigned int x = 0b000000000000000011111111;
    return x &= n;
}

void shift_uses(){
    for(int i = 0; i < pageFrames; i++){
        coreMap[i].use_vector >>= 1;
    }
    if(vflag)
        std::cout << "shift use vectors\n";
    return;
}

void update_use(unsigned int vpn){
    for(int i = 0; i < pageFrames; i++){
        if(coreMap[i].vpn == vpn){
            coreMap[i].use_vector |= 0b10000000;
            return;
        }
    }
    return;
}

bool check_TLB(unsigned int vpn){
    for(int i = 0; i < TLBentries; i++){
        if(TLB[i].vpn == vpn && TLB[i].valid == 1){
            frameNumber = TLB[i].pfn;
            return true;
        }
    }
    return false;
}

void simple_stats(){
    std::cout << "\nstatistics\n";
    std::cout << "  accesses\t" << (memory_access - 1) << "\n";
    std::cout << "  tlb misses\t" << TLB_misses << "\n";
    std::cout << "  page fault\t" << pageFaults << "\n\n";
    return;
}

void stats(){
    simple_stats();
    std::cout << "tlb\n";
    for(int i = 0; i < TLBentries; i++){
        printf("  valid = %d, vpn = 0x%04X, pfn = 0x%02X\n", TLB[i].valid, TLB[i].vpn, TLB[i].pfn);
    }
    std::cout << "\ncore map table\n";
    for(int i = 0; i < pageFrames; i++){
        printf("  pfn = 0x%02X: valid = %d, use vector = 0x%02X, vpn = 0x%04X\n", i, coreMap[i].valid, coreMap[i].use_vector, coreMap[i].vpn);
    }
    std::cout << "\nfirst ten entries of page table\n";
    for(int i = 0; i < 10; i++){
        printf("  vpn = 0x%04X: presence = %d, pfn = 0x%02X\n", i, pageTable[i].presence, pageTable[i].pfn);
    }
}

void update_tlb(unsigned int vpn){
    for(int i = 0; i < TLBentries; i++){
        if(TLB[i].valid == 0){
            TLB[i].valid = 1;
            TLB[i].pfn = pageTable[vpn].pfn;
            TLB[i].vpn = vpn;
            update_use(vpn);
            TLB_index++;
            if(vflag)
                printf("  tlb update of vpn 0x%04X with pfn 0x%02X\n", vpn, pageTable[vpn].pfn);
            return;
        }
    }
    int entry = TLB_index % TLBentries;
    TLB[entry].pfn = pageTable[vpn].pfn;
    TLB[entry].vpn = vpn;
    update_use(vpn);
    TLB_index++;
    if(vflag)
        printf("  tlb update of vpn 0x%04X with pfn 0x%02X\n", vpn, pageTable[vpn].pfn);
    return;
}

unsigned int update_core(unsigned int vpn){
    unsigned int entry = 0;
    unsigned int min = coreMap[0].use_vector;
    for(unsigned int i = 0; i < pageFrames; i++){
        if(coreMap[i].use_vector < min){
            min = coreMap[i].use_vector;
            entry = i;
        }
        if(coreMap[i].valid == 0){
            coreMap[i].valid = 1;
            coreMap[i].use_vector = 0b10000000;
            coreMap[i].vpn = vpn;
            std::cout << "  unused page frame allocated\n";
            return i;   
        }
    }

    coreMap[entry].use_vector = 0b10000000;
    std::cout << "  replace frame " << entry << "\n";
    coreMap[entry].vpn = vpn;
    return entry;
}

int main(int argc, char** argv){
    if(argc > 2){
        std::cout << "ERROR, proper usage: ./paging -v(optional) filename\n";
        return 0;
    }
    vflag = false;
    if(argc > 1){
        std::string flag = "-v";
        if((std::string) argv[1] == flag){
            vflag = true;

        }
    }
    config();
    table_init();

    if(vflag){
        std::cout << "\npaging simulation\n";
        std::cout << "  65536 virtual pages in the virtual address space\n";
        std::cout << "  " << pageFrames << " physical page frames\n";
        std::cout << "  " << TLBentries << " TLB entries\n";
        std::cout << "  use vectors in core map are shifted every " << shifts << " accesses\n\n";
    }

    unsigned int inAddr;

    while(std::cin >> std::hex >> inAddr){
        bool tooBig = false;
        if(inAddr > 0xffffff){
            stats();
            tooBig = true;
        }
        if(vflag && !tooBig)
            std::cout << "access " << memory_access << ":\n";
        if(!tooBig)
            memory_access++;
        
        int misses = 0;
        unsigned int vpn = get_vpn(inAddr);
        if(vflag && !tooBig)
            printf("  virtual address is\t\t  0x%06X\n", inAddr);
        unsigned int offset = get_offset(inAddr);
        bool TLB_hit = check_TLB(vpn);
        if(!TLB_hit && !tooBig){
            if(vflag){
                std::cout << "  tlb miss\n";
            }
            TLB_misses++;
            if(pageTable[vpn].presence == 0){
                if(vflag)
                    std::cout << "  page fault\n";
                pageFaults++;
                pageTable[vpn].presence = 1;
                pageTable[vpn].pfn = update_core(vpn);
                if(vflag){
                    printf("  physical address is \t\t    0x%04X\n", ((pageTable[vpn].pfn << 8) + offset));
                }
                update_tlb(vpn);
            }
            else{
                if(coreMap[pageTable[vpn].pfn].vpn != vpn){
                    if(vflag){
                        std::cout << "  page fault\n";
                        std::cout << "  page replacement needed\n";
                    }
                    pageFaults++;
                    pageTable[coreMap[vpn].vpn].presence = 0;
                    pageTable[coreMap[vpn].vpn].pfn = 0;
                    for(int i = 0; i < TLBentries; i++){
                        if(TLB[i].pfn == pageTable[vpn].pfn){
                            printf("  TLB invalidate of vpn 0x%01X\n", TLB[i].vpn);
                            TLB[i].valid = 0;
                        }
                    }
                    pageTable[vpn].pfn = update_core(vpn);
                    if(vflag){
                        printf("  physical address is \t\t    0x%04X\n", ((pageTable[vpn].pfn << 8) + offset));
                    }
                    update_tlb(vpn);
                }
                else{
                    if(vflag){
                        printf("  page hit, physical address is  0x%06X\n", pageTable[vpn].pfn);
                    }
                    update_tlb(vpn);
                }
            }
            
        }
        else{
            if(vflag && !tooBig){
                unsigned int pAddr = (pageTable[vpn].pfn << 8) + offset;
                printf("  tlb hit, physical address is\t    0x%04X\n", pAddr);
            }
            update_use(vpn);
        }
        int accessed = memory_access - 1;
        if((accessed % shifts) == 0 && accessed > 0){
            shift_uses();
        }
    }
    if(!vflag){
        simple_stats();
    }
    else{
        stats();
    }
    return 0;
}