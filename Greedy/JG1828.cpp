#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int n;
vector<pair<int, int>> arr;
 
int main(){
    ios::sync_with_stdio(false);
    cin.tie(NULL); cout.tie(NULL);

    cin >> n;
    for(int i=0; i<n; i++){
        int a, b;
        cin >> a >> b;
        arr.push_back({a, b});
    }

        sort(arr.begin(), arr.end(), [](pair<int, int> &p1 , pair<int, int> &p2){
            if(p1.first == p2.first){
                return p1.second < p2.second;
            }
            return p1.first < p2.first;
        });

    int cnt = 1;
    int s1 = arr[0].first;
    int e1 = arr[0].second;
    
    for(int i=1; i< n; i++){
        int s2 = arr[i].first;
        int e2 = arr[i].second;
        // cout << s1 << " " << e1 << " " << s2 << " " << e2 << " " << "\n";
        if(e1 < s2){
            cnt++;
            s1 = s2;
            e1 = e2;
        } else if(e2 < e1){
            e1 = e2;
        }
    }

    cout << cnt;
}

/**
 * 1. (e1 < s1) ->  cnt + 1 , start,end 초기화
 * 2. (e2 < e1) ->  end = e2
 * 3. (s1 < s2 && e1 < e2)  ->  end = 그대로 
 */

/**
 * -15      -10     5       10      27      36      44      73
 *  xxxxxxxxxxxxxxxxx
 *           xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
 *                          xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
 *                                  xxxxxxxxxxxxxxxxxx
 */