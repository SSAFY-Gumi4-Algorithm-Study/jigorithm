#include <iostream>
#include <algorithm>
#include <vector>

using namespace std;

int n, w;
vector<int> arr;
int dp[64001];

int main() {
	ios::sync_with_stdio(false);
	cin.tie(NULL); cout.tie(NULL);

	cin >> n;
	for (int i = 0; i < n; i++) {
		int a;
		cin >> a;
		arr.push_back(a);
	}
	cin >> w;
	fill(dp, dp + 64001, 1e9);
	sort(arr.begin(), arr.end());

	int size = arr.size();
	dp[0] = 0;

	for (int i = 0; i < size; i++) {
		int value = arr[i];
		for (int j = value; j <= w; j++) {
			dp[j] = min(dp[j], dp[j - value] + 1);
		}
	}

	if (dp[w] == 1e9) {
		cout << "impossible";
	}
	else {
		cout << dp[w];
	}

}
