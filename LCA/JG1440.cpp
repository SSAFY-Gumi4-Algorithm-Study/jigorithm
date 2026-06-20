#include <iostream>
#include <vector>

using namespace std;

vector<vector<int>> graph(10001);
vector<int> degree(10001);
vector<int> depth(10001);
vector<int> parent(10001);
vector<int> visited(10001);

int n, a, b;
int p;

void dfs(int x, int dep){
    
    depth[x] = dep;

    for(auto it : graph[x]){
        if(visited[it] == 0){
            visited[it] = 1;
            parent[it] = x;
            dfs(it, dep + 1);
            visited[it] = 0;
        }
    }
}

int lca(int q, int w){
    while(depth[q] > depth[w]){
        q = parent[q];
    }

    while(depth[w] > depth[q]){
        w = parent[w];
    }

    while(q != w){
        q = parent[q];
        w = parent[w];
    }

    return q;
}

int main(){
    ios::sync_with_stdio(false);
    cin.tie(NULL);

    int n;
    cin >> n;

    for(int i=0; i< n - 1; i++){
        cin >> a >> b;
        graph[a].push_back(b);
        graph[b].push_back(a);
        degree[b]++;
    }

    for(int i=1; i<=n; i++){
        if(degree[i] == 0) p = i;
    }

    visited[p] = 1;
    dfs(p, 0);

    cin >> a >> b;
    
    cout << lca(a, b);
    return 0;
}