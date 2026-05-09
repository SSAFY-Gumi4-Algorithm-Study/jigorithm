#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

string s1, s2;
int dp[4001][4001];

int main() {
	ios::sync_with_stdio(false);
	cin.tie(NULL); cout.tie(NULL);

	cin >> s1 >> s2;
	
	int size1 = s1.length();
	int size2 = s2.length();
	int MAX = 0;

	for (int i = 1; i <= size1; i++) {
		for (int j = 1; j <= size2; j++) {
			if (s1[i - 1] == s2[j - 1]) {
				cout << i << " " << j << "\n";
				dp[i][j] = dp[i - 1][j - 1] + 1;
			}
			else {
				dp[i][j] = 0;
			}
			MAX = max(MAX, dp[i][j]);
		}
	}


	for (int i = 1; i <= size1; i++) {
		cout << "\n";
		for (int j = 1; j <= size2; j++) {
			cout << dp[i][j] << " ";
		}
	}

	cout << MAX;
}