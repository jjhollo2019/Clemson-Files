#include <iostream>
#include <map>
#include <vector>
#include <string>

typedef std::map<std::pair<std::string, std::string>, bool> route_map;

const route_map populate_routes()
{
    route_map routes;
    routes[std::make_pair("wolf", "goat")] = false;
    routes[std::make_pair("goat", "wolf")] = false;
    routes[std::make_pair("goat", "cabbage")] = false;
    routes[std::make_pair("cabbage", "goat")] = false;
    routes[std::make_pair("wolf", "cabbage")] = true;
    routes[std::make_pair("cabbage", "wolf")] = true;
    return routes;
}

std::ostream& operator << (std::ostream& out, const std::vector<std::string> vec)
{
    for(const std::string it : vec)
    {
        std::string end = (it.compare(vec.back())) ? ", " : " ";
        out << it << end;
    }
    return out;
}

void print_status(std::vector<std::string> &side1, std::vector<std::string> &side2){
    std::cout << side1 << "|river| " << side2 << '\n';
    return;
}

void print_action(std::string item)
{
    std::cout << "Took " + item << '\n';
}

void cross_river(route_map routes, std::vector<std::string> &side1, std::vector<std::string> &side2)
{
    if(side1.size() == 0)
    {
        return;
    }
    else
    {
        std::string item = side1.back();
        side1.pop_back();
        if(side1.size() == 2)
        {
            if(routes[std::make_pair(side1.at(0), side1.at(1))])
            {
                side2.push_back(item);
                print_action(item);
                print_status(side1, side2);
            }
            else
            {
                side1.emplace(side1.begin(), item);
            }
            return cross_river(routes, side1, side2);
        }
        else if(side1.size() == 1 && side2.size() == 1)
        {
            if(routes[std::make_pair(item, side2.at(0))])
            {
                side2.push_back(item);
                print_action(item);
                print_status(side1, side2);
            }
            else
            {
                std::swap(item, side2.at(0));
                std::cout << "Swapped " + item << " for " << side2.at(0) << "\n";
                print_status(side1, side2);
                if(routes[std::make_pair(item, side2.at(0))])
                {
                    side2.push_back(item);
                    print_action(item);
                    print_status(side1, side2);
                }
                else
                {
                    std::swap(item, side1.at(0));
                    std::cout << "Swapped " + item << " for " << side1.at(0) << "\n";
                    print_status(side1, side2);
                    if(routes[std::make_pair(item, side2.at(0))])
                    {
                        side2.push_back(item);
                        print_action(item);
                        print_status(side1, side2);
                    }
                }
            }
            return cross_river(routes, side1, side2);
        }
        else
        {
            side2.push_back(item);
            print_action(item);
            print_status(side1, side2);
            return cross_river(routes, side1, side2);
        }
              
    }
}

int main(void)
{
    const route_map routes = populate_routes();
    std::vector<std::string> side1 = std::vector<std::string>{"goat", "cabbage", "wolf"};
    std::vector<std::string> side2;
    print_status(side1, side2);
    cross_river(routes, side1, side2);
    return 0;
}