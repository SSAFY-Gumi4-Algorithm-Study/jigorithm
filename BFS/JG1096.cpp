#include <iostream>
#include <queue>

using namespace std;

int dy[8] = {-1,-1,-1, 0, 0, 1, 1, 1};
int dx[8] = {-1, 0, 1,-1, 1,-1, 0, 1};

int map[701][701];
int visited[701][701];

int n, m;

bool isRange(int y, int x){
    return 1 <= y && y <= n && 1 <= x && x <= m;
}

bool bfs(int y, int x){
    queue<pair<int, int>> q;
    q.push({y, x});
    visited[y][x] = 1;

    int height = map[y][x];
    bool isPeak = true;

    while(!q.empty()){
        auto it = q.front();
        q.pop();

        int cy = it.first;
        int cx = it.second;

        for(int d = 0; d < 8; d++){
            int ny = cy + dy[d];
            int nx = cx + dx[d];

            if(!isRange(ny, nx)) continue;

            if(map[ny][nx] > height){
                isPeak = false;
            }

            if(visited[ny][nx] == 0 && map[ny][nx] == height){
                q.push({ny, nx});
                visited[ny][nx] = 1;
            }
        }
    }

    return isPeak;
}

int main(){
    ios::sync_with_stdio(false);
    cin.tie(NULL); 
    cout.tie(NULL);

    cin >> n >> m;

    for(int i = 1; i <= n; i++){
        for(int j = 1; j <= m; j++){
            cin >> map[i][j];
        }
    }

    int cnt = 0;

    for(int i = 1; i <= n; i++){
        for(int j = 1; j <= m; j++){
            if(visited[i][j] == 0){
                if(bfs(i, j)){
                    cnt++;
                }
            }
        }
    }

    cout << cnt;
}