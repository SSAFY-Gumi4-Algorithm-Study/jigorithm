#include <iostream>
#include <algorithm>
#include <queue>

using namespace std;

int dy[4] = {-1, 0, 0, 1};
int dx[4] = {0, -1, 1, 0};

int n, f;

int map[501][501];
bool visited[501][501];

bool isRange(int y, int x);
int bfs(int y, int x, int h);
int binarySearch();

int binarySearch(){
    int l = 0;
    int r = 1'000'001;

    while(l < r){
        int mid = (l + r) / 2;
        // cout << "l = " << l << " r = " << r << " mid = " << mid << " \n";
        int MAX = 0;
        for(int i=1; i<= n; i++){
            for(int j=1; j<=n; j++){
                visited[i][j] = false;
            }
        }

        for(int i=1; i<= n; i++){
            for(int j=1; j<=n; j++){
                MAX = max(MAX, bfs(i,j, mid));
            }
        }

        if(MAX < f){
            l = mid + 1;
        }else{
            r = mid;
        }
    }

    return l;
}

bool isRange(int y, int x){
    return 1 <= y && y <= n && 1 <= x && x <= n;
}

int bfs(int y, int x, int h){
    if(visited[y][x]) return 0;

    queue<pair<int,int>> q;
    q.push({y, x});
    visited[y][x] = true;
    int m = 1;

    while(!q.empty()){
        auto it = q.front();
        q.pop();
        int cy = it.first;
        int cx = it.second;
        for(int d = 0; d< 4; d++){
            int ny = cy + dy[d];
            int nx = cx + dx[d];
            if(isRange(ny, nx) && !visited[ny][nx]){
                if((map[cy][cx] - h <= map[ny][nx]) && (map[ny][nx] <= map[cy][cx] + h)){
                    visited[ny][nx] = true;
                    q.push({ny, nx});
                    m++;
                }
            }
        }
    }

    return m;
}

// h = 3        map[y][x] = 5       ->      2       8

int main(){
    ios::sync_with_stdio(false);
    cin.tie(NULL); cout.tie(NULL);

    cin >> n;
    f = (n * n * 3 / 4);

    for(int i=1; i<=n; i++){
        for(int j= 1; j<=n; j++){
            int a;
            cin >> a;
            map[i][j] = a;
        }
    }

    
    cout << binarySearch();
}
