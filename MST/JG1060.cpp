#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

struct Node{
    int y, x, cost;
    Node(int y, int x, int cost){
        this -> y =y;
        this -> x = x;
        this -> cost = cost;
    }
};

int n;
vector<vector<int>> map;
vector<Node> nodes;
vector<int> parent;


int Find(int x){
    if(parent[x] == x) return x;
    return parent[x] = Find(parent[x]);
}

bool merge(int a, int b){
    a = Find(a);
    b = Find(b);
    if(a == b) return false;
    parent[b] = a;
    return true;
}


int main(){
    ios::sync_with_stdio(false);
    cin.tie(NULL);cout.tie(NULL);

    cin >> n;

    map.assign(n + 1, vector<int>(n + 1));

    for(int i=1; i<= n; i++){
        for(int j=1; j<= n; j++){
            int a;
            cin >> a;
            map[i][j] = a;
        }
    }

    for(int i=1; i<=n; i++){
        for(int j=1; j<=n; j++){
            if(map[i][j] !=0 ){
                nodes.push_back(Node(i, j, map[i][j]));
            }
        }
    }

    sort(nodes.begin(), nodes.end(), [](const Node& a, const Node& b){
        return a.cost < b.cost;
    });

    for(int i=0; i <= n; i++){
        parent.push_back(i);
    }

    int len = nodes.size();

    int cost = 0;
    for(int i=0; i< len; i++){
        auto it = nodes[i];
        if(merge(it.y, it.x)){
            cost += it.cost;
        }
    }

    cout << cost;

}