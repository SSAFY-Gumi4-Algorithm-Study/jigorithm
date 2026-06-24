#include <iostream>
#include <queue>

using namespace std;

struct Node{
    int y, x, dir;
};

int n, m;
int map[101][101];
int visited[4][101][101];

int dy[4] = {0, 1, 0, -1}; // 동 남 서 북
int dx[4] = {1, 0, -1, 0};

bool isRange(int y, int x){
    return 1 <= y && y <= n && 1 <= x && x <= m;
}

int converDir(int dir){
    if(dir == 1){
        return 0;
    }
    if(dir == 2){
        return 2;
    }
    if(dir == 3){
        return 1;
    }
    if(dir == 4){
        return 3;
    }
}

int main(){
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    cin >> n >> m;

    for(int i=1; i<=n; i++){
        for(int j=1; j<=m; j++){
            cin >> map[i][j];
        }
    }

    int sy, sx, sd;
    int ey, ex, ed;

    cin >> sy >> sx >> sd;
    cin >> ey >> ex >> ed;

    sd = converDir(sd);
    ed = converDir(ed);

    for(int d=0; d<4; d++){
        for(int i=0; i<=100; i++){
            for(int j=0; j<=100; j++){
                visited[d][i][j] = -1;
            }
        }
    }

    queue<Node> q;

    visited[sd][sy][sx] = 0;
    q.push({sy, sx, sd});

    while(!q.empty()){
        Node cur = q.front();
        q.pop();

        int y = cur.y;
        int x = cur.x;
        int dir = cur.dir;

        for(int k=1; k<=3; k++){

            int ny = y + dy[dir] * k;
            int nx = x + dx[dir] * k;

            if(!isRange(ny, nx))
                break;

            if(map[ny][nx] == 1)
                break;

            if(visited[dir][ny][nx] != -1)
                continue;

            visited[dir][ny][nx] = visited[dir][y][x] + 1;

            q.push({ny, nx, dir});
        }

        // 회전
        int ndir;
        
        ndir = (dir + 1) % 4;
        if(visited[ndir][y][x] == -1){
            visited[ndir][y][x] = visited[dir][y][x] + 1;
            q.push({y, x, ndir});
        }

        ndir = (dir + 3) % 4;
        if(visited[ndir][y][x] == -1){
            visited[ndir][y][x] = visited[dir][y][x] + 1;
            q.push({y, x, ndir});
        }

    }

    cout << visited[ed][ey][ex];
}