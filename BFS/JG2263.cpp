#include <iostream>
#include <vector>
#include <string>
#include <queue>
#include <unordered_map>
#include <algorithm>

using namespace std;

int n, k, m;

vector<int> code;
vector<int> parentNode;
vector<int> visited;

unordered_map<int, int> codeToIndex;

void bfs() {
	queue<int> q;

	visited[1] = 1;
	parentNode[1] = 0;
	q.push(1);

	while (!q.empty()) {
		int cur = q.front();
		q.pop();

		int curCode = code[cur];

		for (int bit = 0; bit < k; bit++) {
			int nextCode = curCode ^ (1 << bit);

			if (codeToIndex.find(nextCode) == codeToIndex.end()) {
				continue;
			}

			int next = codeToIndex[nextCode];

			if (visited[next]) {
				continue;
			}

			visited[next] = 1;
			parentNode[next] = cur;
			q.push(next);
		}
	}
}

void printPath(int target) {
	if (visited[target] == 0) {
		cout << -1 << "\n";
		return;
	}

	vector<int> path;

	int cur = target;

	while (cur != 0) {
		path.push_back(cur);
		cur = parentNode[cur];
	}

	reverse(path.begin(), path.end());

	for (int x : path) {
		cout << x << " ";
	}

	cout << "\n";
}

int main() {
	ios::sync_with_stdio(false);
	cin.tie(NULL);cout.tie(NULL);

	cin >> n >> k;

	code.assign(n + 1, 0);
	parentNode.assign(n + 1, 0);
	visited.assign(n + 1, 0);

	for (int i = 1; i <= n; i++) {
		string s;
		cin >> s;

		int value = stoi(s, nullptr, 2);

		code[i] = value;
		codeToIndex[value] = i;
	}

	bfs();

	cin >> m;

	for (int i = 0; i < m; i++) {
		int target;
		cin >> target;

		printPath(target);
	}

	return 0;
}
