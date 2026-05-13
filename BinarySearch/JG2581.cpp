#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int n, m;
vector<int> arr;

int upperbound() {
	int l = 0;
	int r = 1'000'000'001;
	while (l < r) {
		int mid = (l + r) / 2;
		long long sum = 0;
		for (int i = 0; i < n; i++) {
			if (arr[i] < mid) {
				sum += arr[i];
			}
			else {
				sum += mid;
			}
		}

		if (sum <= m) {
			l = mid + 1;
		}
		else {
			r = mid;
		}
	}

	return l - 1;
}

int main() {
	ios::sync_with_stdio(false);
	cin.tie(NULL); cout.tie(NULL);

	int b = 0;
	int MAX = 0;
	cin >> n;
	for (int i = 0; i < n; i++) {
		int a;
		cin >> a;
		arr.push_back(a);
		b += a;
		MAX = max(MAX, a);
	}
	cin >> m;

	if (b <= m) {
		cout << MAX;
		return 0;
	}

	cout << upperbound();

}