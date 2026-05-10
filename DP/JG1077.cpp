#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

struct Backpack {
	int value;
	int weight;
};

vector<Backpack> arr;
int N, W;

int dp[1001][10001] = { 0 };

int main() {
	ios::sync_with_stdio(false);
	cin.tie(NULL); cout.tie(NULL);

	cin >> N >> W;

	for (int i = 0; i < N; i++) {
		int a, b;
		cin >> a >> b;
		arr.push_back({ a,b });
	}

	for (int i = 1; i <= N; i++) {
		Backpack bp = arr[i - 1];
		int v = bp.value;
		int w = bp.weight;
		for (int j = 1; j <= W; j++) {
			if (j < v) {
				dp[i][j] = dp[i - 1][j];
			}
			else {
				dp[i][j] = max(dp[i - 1][j], dp[i][j - v] + w);
			}
			
		}
	}

	cout << dp[N][W];
}


