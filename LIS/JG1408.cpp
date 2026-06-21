#include <iostream>
#include <algorithm>


using namespace std;


vector<pair<int, int>> arr;
vector<int> lis;
int n;

int main(){
    ios::sync_with_stdio(false);
    cin.tie(NULL); cout.tie(NULL);

    cin >> n;

    for(int i=0; i< n; i++){
        int a, b;
        cin >> a >> b;
        arr.push_back({a, b});
    }

    sort(arr.begin(), arr.end(), [](const pair<int,int>& a, const pair<int,int> &b){
        return a.first<b.first;
    });

    
    for(int i=0; i< n; i++){
        auto it = lower_bound(lis.begin(), lis.end(), arr[i].second);

        if(it == lis.end()){
            lis.push_back(arr[i].second);
        }else{
            *it = arr[i].second;
        }
    }

    cout << n - lis.size();
}