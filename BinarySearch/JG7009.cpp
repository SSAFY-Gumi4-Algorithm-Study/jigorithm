#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int n, m;
vector<int> arr1;
vector<int> arr2;

bool binarySearch(int tar){
    int l = 0;
    int r = arr1.size() - 1;

    while(l <= r){
        int mid = (l + r) / 2;
        if(tar < arr1[mid]){
            r = mid - 1;
        }else if(tar == arr1[mid]){
            return true;
        }else{
            l = mid + 1;
        }
    }

    return false;
}

int main(){
    ios::sync_with_stdio(false);
    cin.tie(NULL); cout.tie(NULL);

    cin >> n >> m;
    
    for(int i =0; i< n; i++){
        int a;
        cin >> a;
        arr1.push_back(a);
    }

    for(int i =0; i< m; i++){
        int a;
        cin >> a;
        arr2.push_back(a);
    }

    sort(arr1.begin(), arr1.end());
    
    int cnt = 0;
    for(int i = 0; i< m; i++){
        if(!binarySearch(arr2[i])){
            cout << arr2[i] << " ";
            cnt++;
        }
    }

    if(cnt == 0){
        cout << -1;
    }
    
}
