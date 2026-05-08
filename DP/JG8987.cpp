#include <iostream>
#include <algorithm>
#include <vector>

using namespace std;

const int INF = 1e9;
int n, m;
string s1, s2;

int main() {
	ios::sync_with_stdio(false);
	cin.tie(NULL);

	cin >> n >> m;
	cin >> s1 >> s2;


	vector<vector<int>> dp(n + 1, vector<int>(m + 1, INF));

	for (int i = 0; i <= n; i++) {
		dp[i][0] = 0;
	}

	for (int i = 1; i <= n; i++) {
		for (int j = 0; j <= m; j++) {
			dp[i][j] = min(dp[i][j], dp[i - 1][j]);

			if (j >= 1) {
				int cost = (s1[i - 1] == s2[j - 1]) ? 0 : 1;
				dp[i][j] = min(dp[i][j], dp[i - 1][j - 1] + cost);
			}
		}
	}

	cout << dp[n][m];
}
