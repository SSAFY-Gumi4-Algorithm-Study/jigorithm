#include <iostream>
#include <queue>

using namespace std;

int n;

queue<int> q;

int main() {
	ios::sync_with_stdio(false);
	cin.tie(NULL); cout.tie(NULL);

	cin >> n;

	for (int i = 0; i < n; i++) {
		char c;
		cin >> c;

		if (c == 'i') {
			int value;
			cin >> value;
			q.push(value);
		}
		else if(c == 'o') {
			if (q.empty()) {
				cout << "empty" << "\n";
				continue;
			}
			auto p = q.front();
			q.pop();
			cout << p << "\n";
		}
		else {
			cout << q.size() << "\n";
		}

	}
}
