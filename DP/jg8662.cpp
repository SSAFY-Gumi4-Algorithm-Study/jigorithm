#include <iostream>
#include <vector>
#include <string>
#include <algorithm>

using namespace std;

int n;
string s;
const int INF = -1e9;

vector<vector<vector<int>>> dp;

int main() {
	ios::sync_with_stdio(false);
	cin.tie(NULL); cout.tie(NULL);

	cin >> n >> s;

	dp.assign(n + 1, vector<vector<int>>(2, vector<int>(3, INF)));

	dp[0][0][0] = 0;

	for (int i = 0; i < n; i++) {
		for (int last = 0; last <= 1; last++) {
			for (int cntX = 0; cntX <= 2; cntX++) {
				if (dp[i][last][cntX] == INF) continue;

				if (s[i] == 'O' || s[i] == '?') {
					if (last != 1) {
						dp[i + 1][1][0] = max(dp[i + 1][1][0], dp[i][last][cntX] + 1);
					}
				}

				if (s[i] == 'X' || s[i] == '?') {
					if (cntX < 2) {
						dp[i + 1][0][cntX + 1] = max(dp[i + 1][0][cntX + 1], dp[i][last][cntX]);
					}
				}
			}
		}
	}

	int answer = INF;

	for (int last = 0; last <= 1; last++) {
		for (int cntX = 0; cntX <= 2; cntX++) {
			answer = max(answer, dp[n][last][cntX]);
		}
	}

	if (answer == INF) cout << -1 << '\n';
	else cout << answer << '\n';

	return 0;
}