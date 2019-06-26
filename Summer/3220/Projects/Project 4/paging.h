/* Jeremy Holloway
 * CPSC-3220-001
 * 6/19/2019
 * Project 4
*/

unsigned int max_size = 65536;

typedef struct {
    unsigned int presence;
    unsigned int pfn;
} PTE;

typedef struct {
    unsigned int valid;
    unsigned short vpn;
    unsigned int pfn;
} TLBE;

typedef struct {
    unsigned int valid;
    unsigned int use_vector;
    unsigned short vpn;
} CME;

PTE *pageTable;
TLBE *TLB;
CME *coreMap;

int memory_access;
int TLB_misses;
int pageFaults;

int pageFrames;
int TLBentries;
int shifts;

unsigned int frameNumber;
bool vflag;

int TLB_index;
