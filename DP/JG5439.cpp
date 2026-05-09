#include <iostream>
#include <algorithm>
#include <vector>

using namespace std;

vector<int> arr;
int n;
int dp[10'001];

int main() {
	ios::sync_with_stdio(false);
	cin.tie(NULL); cout.tie(NULL);

	for (int i = 1; i <= 100; i++) {
		arr.push_back(i * i);
	}

	cin >> n;
	
	fill(dp, dp +10001, 1e9);
	dp[0] = 0;

	for (int i = 0; i < 100; i++) {
		int value = arr[i];
		for (int j = value; j <= n; j++) {
			dp[j] = min(dp[j], dp[j - value] + 1);
		}
	}

	cout << dp[n];
}