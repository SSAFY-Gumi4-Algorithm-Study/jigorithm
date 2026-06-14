#include <iostream>
#include <algorithm>
#define FASTIO ios::sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);

using namespace std;

string str1, str2;

int main(){
    FASTIO;

    cin >> str1 >> str2;

    int size1 = str1.size();
    int size2 = str2.size();

    int dp[size1 + 1][size2 + 1];

    for(int i=0; i< size1 + 1; i++){
        for(int j=0; j< size2 + 1; j++){
            dp[i][j] = 0;
        }
    }

    for(int i = 1; i<= size1; i++){
        for(int j= 1; j <= size2; j++){
            if(str1[i - 1] == str2[j - 1]){
                dp[i][j] = dp[i - 1][j - 1] + 1;
            }else{
                dp[i][j] = max(dp[i - 1][j], dp[i][j - 1]);
            }
        }
    }

    cout << dp[size1][size2];
}
