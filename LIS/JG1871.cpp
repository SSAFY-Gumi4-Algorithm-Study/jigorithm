#include <iostream>
#include <vector>


using namespace std;

int n;

vector<int> arr;
vector<int> lis;

int main() {
	ios::sync_with_stdio(false);
	cin.tie(NULL); cout.tie(NULL);

	cin >> n;
	for (int i = 0; i < n; i++) {
		int a;
		cin >> a;
		arr.push_back(a);
	}


	for (int i = 0; i < n; i++) {
		auto idx = lower_bound(lis.begin(), lis.end(), arr[i]);

		if (idx == lis.end()) {
			lis.push_back(arr[i]);
		}
		else {
			*idx = arr[i];
		}
	}

	cout << n - lis.size();

}
