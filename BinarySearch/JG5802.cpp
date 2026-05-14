#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int n, m, len;
vector<int> arr;


int getCnt(int s) {
	int cnt = 1;
	int last = arr[0];

	for (int i = 1; i < n; i++) {
		if (arr[i] >= s + last) {
			last = arr[i];
			cnt++;
		}
	}

	return cnt;
}

int main() {
	ios::sync_with_stdio(false);
	cin.tie(NULL); cout.tie(NULL);

	cin >> n >> m;
	for (int i = 0; i < n; i++) {
		int a;
		cin >> a;
		arr.push_back(a);
	}
	len = arr.size();

	sort(arr.begin(), arr.end());

	int l = 0;
	int r = 1'000'000'001;

	while (l < r) {
		int mid = (l + r) / 2;
		int cnt = getCnt(mid);
		if (m <= cnt) {
			l = mid + 1;
		}
		else {
			r = mid;
		}
	}

	cout << l - 1;
}
