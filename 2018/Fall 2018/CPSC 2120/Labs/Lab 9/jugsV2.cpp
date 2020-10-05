#include <iostream>
#include <list>
#include <map>

const int A[4] = {0,1,2,3};
const int B[5] = {0,1,2,3,4};

std::ostream& operator << (std::ostream& out, const std::pair<int,int> pair)
{
    int a = std::get<0>(pair);
    int b = std::get<1>(pair);

    out << a << " + " << b << " = " << a + b << "\n";
    return out;
}

std::map<std::pair<int,int>, int> make_routes()
{
    std::map<std::pair<int,int>, int> routes;
    std::cout << "Valid Solutions:\n";
    for(int x : A)
    {
        for(int y : B)
        {
            if(x + y == 5)
            {
                routes[std::make_pair(x,y)] = x + y;
                std::pair<int,int> temp = std::make_pair(x,y);
                std::cout << temp;
            }
        }
    }
    std::cout << "\n\n";
    return routes;
}

std::pair<int,int> make_5_gallons(std::map<std::pair<int,int>, int> routes, std::pair<int, int> &jugs)
{
    int a = std::get<0>(jugs);
    int b = std::get<1>(jugs);

    if(routes[std::make_pair(a,b)] == 5)
    {
        return jugs;
    }
    
    if(b > a && routes[std::make_pair(b,0)] <= 5)
    {
        if(b == 4)
        {
            std::cout << "Fill Bucket A From Bucket B\n";
            a = 3;
            b = 1;
        }
        else
        {
            std::cout << "Fill Bucket A From Bucket B\n";
            a = b;
            b = 0;
        }
    }
    else if(b == 0)
    {
        std::cout << "Fill Bucket B\n";
        b = 4;
    }
    else if(a > b)
    {
        std::cout << "Empty Bucket A\n";
        a = 0;
    }
    else if(a == 0)
    {
        std::cout << "Fill Bucket A\n";
        a = 3;
    }
    std::pair<int,int> temp = std::make_pair(a,b);
    std::cout << temp;
    return make_5_gallons(routes, temp);
}

int main(void)
{
    std::map<std::pair<int,int>, int> routes = make_routes();
    std::pair<int, int> jugs = std::make_pair(0,0);
    std::cout << "Starting Jugs: " << jugs << "\n\n";
    std::pair<int, int> five_gal = make_5_gallons(routes, jugs);
    std::cout << "\n\nFinal Jugs: " << five_gal;
    return 0;
}