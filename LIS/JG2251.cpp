#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int n;
vector<int> arr1;
vector<int> arr2;
vector<int> arr3;
vector<int> lis;
vector<int> parent;

int main(){
    ios::sync_with_stdio(false);
    cin.tie(NULL); cout.tie(NULL);

    cin >> n;

    arr1.assign(n + 1, 0);
    arr2.assign(n + 1, 0);
    arr3.assign(n + 1, 0);
    parent.assign(n + 1, -1);

    for(int i = 1; i <= n; i++){
        int a;
        cin >> a;

        arr1[a] = i;
    }

    for(int i = 1; i <= n; i++){
        int a;
        cin >> a;
        arr3[i] = a;
        arr2[i] = arr1[a];
    }

    lis.push_back(0);

    for(int i = 1; i <= n; i++){
        auto idx = lower_bound(lis.begin() + 1, lis.end(), arr2[i]);
        int index = idx - lis.begin();

        parent[i] = index;

        if(idx == lis.end()){
            lis.push_back(arr2[i]);
        }else{
            *idx = arr2[i];
        }
    }

    int tmp = lis.size() - 1;
    int last = 1e9;

    vector<int> result;

    for(int i = n; i >= 1; i--){
        if(parent[i] == tmp && arr2[i] < last){
            result.push_back(arr3[i]);
            last = arr2[i];
            tmp--;
        }
    }

    sort(result.begin(), result.end());

    cout << result.size() << "\n";

    for(int i = 0; i < result.size(); i++){
        cout << result[i] << " ";
    }

    return 0;
}
