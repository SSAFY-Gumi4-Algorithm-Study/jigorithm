#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int n;

int main() {
	ios::sync_with_stdio(false);
	cin.tie(NULL); cout.tie(NULL);
	cin >> n;

	vector<int> arr(n);
	int total[4] = { 0 };

	for (int i = 0; i < n; i++) {
		cin >> arr[i];
		total[arr[i]]++;
	}

	int cnt[4][4] = { 0 };

	for (int i = 0; i < n; i++) {
		int section;

		if (i < total[1]) {
			section = 1;
		}
		else if (i < total[1] + total[2]) {
			section = 2;
		}
		else {
			section = 3;
		}

		cnt[section][arr[i]]++;
	}

	int answer = 0;

	// 1구간의 2와 2구간의 1 교환
	int x = min(cnt[1][2], cnt[2][1]);
	answer += x;
	cnt[1][2] -= x;
	cnt[2][1] -= x;

	// 1구간의 3과 3구간의 1 교환
	x = min(cnt[1][3], cnt[3][1]);
	answer += x;
	cnt[1][3] -= x;
	cnt[3][1] -= x;

	// 2구간의 3과 3구간의 2 교환
	x = min(cnt[2][3], cnt[3][2]);
	answer += x;
	cnt[2][3] -= x;
	cnt[3][2] -= x;

	int remain = 0;

	//3-cycle은 2번의 swap이 필요함.
	for (int section = 1; section <= 3; section++) {
		for (int num = 1; num <= 3; num++) {
			if (section != num) {
				remain += cnt[section][num];
			}
		}
	}

	answer += (remain / 3) * 2;

	cout << answer;

	return 0;
}
