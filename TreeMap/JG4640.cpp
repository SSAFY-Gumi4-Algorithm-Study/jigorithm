#include <iostream>
#include <map>

using namespace std;

int n;

map<int, int> m;

int main() {
	ios::sync_with_stdio(false);
	cin.tie(NULL); cout.tie(NULL);

	cin >> n;

	for (int i = 0; i < n; i++) {
		char c;
		int a;
		cin >> c >> a;

		// 1 2 3 4 5 
		if (c == 'i') {
			m.insert({ a,1 });
		}
		else if (c == 'r') {
			m.erase(a);
		}
		else if (c == 'b') {
			auto it = m.lower_bound(a);
			if (it != m.end()) {
				cout << it->first << "\n";
			}
		}
		else if (c == 's') {
			auto it = m.upper_bound(a);
			if (it != m.begin()) {
				it--;
				cout << it->first << "\n";
			}
		}
	}

	return 0;

}
