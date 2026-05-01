#include <iostream>
#include <vector>
#include <queue>

using namespace std;

int dy[] = { -1,-2,-2,-1,1,2,2,1 };
int dx[] = { -2,-1,1,2,2,1,-1,-2 };

bool visited[1001][1001];
vector<int> board[1001];
int n, m;
int akfY, akfX, whfY, whfX;

bool isRange(int y, int x) {
	return 0 <= y && y < n && 0 <= x && x < m;
}

int main() {
	ios::sync_with_stdio(false);
	cin.tie(NULL); cout.tie(NULL);
	cin >> n >> m;

	for (int i = 0; i < n; i++) {
		for (int j = 0; j < m; j++) {
			board[i].push_back(0);
		}
	}

	cin >> akfY >> akfX >> whfY >> whfX;
	akfY--; akfX--; whfY--; whfX--;


	queue<tuple<int,int,int>> q;
	q.push({ akfY, akfX ,0});
	visited[akfY][akfX] = true;

	while (!q.empty()) {
		auto [y, x, cnt] = q.front();
		q.pop();

		if (y == whfY && x == whfX) {
			cout << cnt;
			break;
		}
		
		for (int d = 0; d < 8; d++) {
			int ny = y + dy[d];
			int nx = x + dx[d];
			if (isRange(ny, nx) && !visited[ny][nx]) {
				q.push({ ny, nx, cnt + 1 });
				visited[ny][nx] = true;
			}
		}
	}

}