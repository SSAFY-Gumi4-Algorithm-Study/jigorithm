#include <iostream>
#include <queue>

using namespace std;

int n, m;
char map[4001][4001];
int visited[4001][4001];

int startY, startX, endY, endX;

int dy[4] = {-1, 0, 0, 1};
int dx[4] = {0, -1, 1, 0};

bool isRange(int y, int x){
    return 0 <= y && y < n && 0 <= x && x < m;
}

int main(){
    ios::sync_with_stdio(false);
    cin.tie(NULL); cout.tie(NULL);

    cin >> n >>  m;

    for(int i = 0; i< n; i++){
        string str;
        cin >> str;
        for(int j= 0; j< m; j++){
            map[i][j] = str[j];
            if(map[i][j] == 'S'){
                startY = i;
                startX = j;
                map[i][j] = '.';
            }else if(map[i][j] == 'G'){
                endY = i;
                endX = j;
                map[i][j] = '.';
            }            
        }
    }

    for(int i=0; i< 4001; i++){
        for(int j=0; j< 4001; j++){
            visited[i][j] = -1;
        }
    }

    queue<pair<int, int>> q;
    q.push({startY, startX});
    visited[startY][startX] = 0;

    while(!q.empty()){
        auto it = q.front();
        q.pop();
        int y = it.first;
        int x = it.second;

        if(y == endY && x == endX){
            break;
        }

        for(int d = 0; d < 4; d++){
            int ny = y + dy[d];
            int nx = x + dx[d];

            if(isRange(ny, nx) && map[ny][nx] == '.' && visited[ny][nx] == -1){
                visited[ny][nx] = visited[y][x] + 1;
                q.push({ny, nx});
            }
        }
    }

    cout << visited[endY][endX];
}
