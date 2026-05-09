#include <iostream>
#include <string>

using namespace std;


int n;
bool a = false;
void dfs(string str, int depth) {
	if (depth > n) return;

	int len = str.length();
	for (int pos = len - 1, i = 1; pos >= ((len + 1) / 2); pos--, i++) {
		string str1 = str.substr(pos - i, i);
		string str2 = str.substr(pos, i);
		if (str1 == str2) {
			return;
		}
	}

	if(depth == n){
		cout << str;
		a = true;
	}
	if (a) return;
	dfs(str + "1", depth + 1);
	if (a) return;
	dfs(str + "2", depth + 1);
	if (a) return;
	dfs(str + "3", depth + 1);
}

/*
* 
* 1 2
*  |
* 0 1 
* 
* 1 2 3
*    |
* 1 2
* 
* 
* 1 2 3 4 
*      |
*    |
* 2 3
* 01 23
* 
*/

int main() {
	ios::sync_with_stdio(false);
	cin.tie(NULL); cout.tie(NULL);

	cin >> n;

	dfs("", 0);
}