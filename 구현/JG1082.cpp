    #include <iostream>
    #include <queue>

    using namespace std;

    int n, m;
    char map[51][51];
    int visited[51][51];
    queue<pair<int,int>> q;
    queue<pair<int,int>> fire;
    int sy, sx, ey, ex;

    int dy[4] = {-1, 0, 0, 1};
    int dx[4] = {0, -1, 1, 0};


    bool isRange(int y, int x){
        return 1 <= y && y <= n && 1 <= x && x <= m;
    }

    int main(){
        ios::sync_with_stdio(false);
        cin.tie(NULL); cout.tie(NULL);

        for(int i=0 ; i<=50 ; i++){
            for(int j=0; j<=50; j++){
                visited[i][j] = -1;
            }
        }

        cin >> n >> m;

        for(int i= 1; i<= n; i++){
            for(int j= 1; j <= m; j++){
                char c;
                cin >> c;
                if(c == 'S'){
                    sy = i;
                    sx = j;
                    map[i][j] = '.';
                }else if(c == 'D'){
                    ey = i;
                    ex = j;
                    map[i][j] = 'D';
                }else if(c == '*'){
                    fire.push({i, j});
                    map[i][j] = '*';
                }else{
                    map[i][j] = c;
                }
            }
        }

        q.push({sy,sx});
        visited[sy][sx] = 0;

        while(1){
            if(q.empty()) break;

            //불 먼저 전파
            int fireSize = fire.size();
            for(int i= 0; i< fireSize; i++){
                auto it = fire.front();
                fire.pop();

                for(int d = 0; d< 4; d++){
                    int ny = it.first + dy[d];
                    int nx = it.second + dx[d];
                    if(isRange(ny, nx) && map[ny][nx] == '.'){
                        map[ny][nx] = '*';
                        fire.push({ny, nx});
                    }
                }
            }

            //이동
            int qSize = q.size();
            for(int i =0; i< qSize; i++){
                auto it = q.front();
                int cy = it.first;
                int cx = it.second;
                q.pop();
                for(int d = 0; d< 4; d++){
                    int ny = cy + dy[d];
                    int nx = cx + dx[d];
                    if(isRange(ny, nx) && visited[ny][nx] == -1 && (map[ny][nx] == '.' || map[ny][nx] == 'D')){
                        if(map[ny][nx] == 'D'){
                            cout << visited[cy][cx] + 1;
                            exit(0);
                        }
                        visited[ny][nx] = visited[cy][cx] + 1;
                        q.push({ny,nx});
                    }
                }
            }

        }

        cout << "impossible";
    }