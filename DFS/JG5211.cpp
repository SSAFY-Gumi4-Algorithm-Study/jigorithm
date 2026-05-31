#include <iostream>

using namespace std;

char map[10][10];
int visited[10][10];

int n, m, nm;

int dy[4] = { -1, 0, 0, 1 };
int dx[4] = { 0, -1, 1, 0 };

bool isRange(int y, int x) {
	return 1 <= y && y <= n && 1 <= x && x <= m;
}

bool finished = false;

void dfs(int y, int x ,int cnt) {
	
	if (finished) {
		return;
	}

	if (cnt == nm) {
		finished = true;
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= m; j++) {
				cout << visited[i][j] << " ";
			}
			cout << "\n";
		}

		return;
	}

	for (int d = 0; d < 4; d++) {
		int ny = y + dy[d];
		int nx = x + dx[d];

		if (isRange(ny, nx) && visited[ny][nx] == 0) {
			if ((map[y][x] == 'A' && map[ny][nx] == 'B') || 
				(map[y][x] == 'B' && map[ny][nx] == 'C') || 
				(map[y][x] == 'C' && map[ny][nx] == 'A')) {
				visited[ny][nx] = visited[y][x] + 1;
				dfs(ny, nx, cnt + 1);
				visited[ny][nx] = visited[y][x];
			}
		}

	}


}

int main() {
	ios::sync_with_stdio(false);
	cin.tie(NULL); cout.tie(NULL);

	cin >> n >> m;

	for (int i = 1; i <= n; i++) {
		for (int j = 1; j <= m; j++) {
			char c;
			cin >> c;
			map[i][j] = c;
		}
	}

	nm = n * m;

	for (int i = 1; i <= n; i++) {
		visited[i][1] = 1;
		dfs(i, 1, 1);
		visited[i][1] = 0;

		visited[i][m] = 1;
		dfs(i, m, 1);
		visited[i][m] = 0;
	}


	for (int i = 1; i <= m; i++) {
		visited[1][i] = 1;
		dfs(1, i, 1);
		visited[1][i] = 0;

		visited[n][i] = 1;
		dfs(n, i, 1);
		visited[n][i] = 0;
	}


	if (!finished) {
		cout << "impossible";
	}

}
