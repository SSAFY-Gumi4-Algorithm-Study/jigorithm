#include <iostream>
#include <algorithm>

using namespace std;

int n,m;
int arr[50'001], dp[4][50'001], prefix[50'000];


int main(){
    ios::sync_with_stdio(false);
    cin.tie(NULL); cout.tie(NULL);

    cin >> n;
    for(int i= 1; i<= n; i++){
        int a;
        cin >> a;
        arr[i] = a;
    }
    cin >> m;

    for(int i= 1; i<= n; i++){
        prefix[i] = prefix[i - 1] + arr[i];
    }

    for(int i =1; i<= 3; i++){
        for(int j = i * m; j <= n; j++){
            dp[i][j] = max(dp[i][j - 1], dp[i - 1][j - m] + prefix[j] - prefix[j - m]);
        }
    }

    cout << dp[3][n];

}

