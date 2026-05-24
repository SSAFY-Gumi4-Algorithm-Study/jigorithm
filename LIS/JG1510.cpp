#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

class Paper{
public:
    int a, b;

    Paper(int a, int b){
        this->a = a;
        this->b = b;
    }
};

int n;
vector<Paper> arr;
vector<int> lis;

int main(){
    ios::sync_with_stdio(false);
    cin.tie(NULL); cout.tie(NULL);

    cin >> n;

    for(int i=0; i< n; i++){
        int a, b;
        cin >> a >> b;
        if(a < b){
            arr.push_back(Paper(a, b));
        }else{
            arr.push_back(Paper(b, a));
        }
    }

    sort(arr.begin(), arr.end(), [](const Paper& p1, const Paper& p2){
        if(p1.a == p2.a){
            return p1.b < p2.b;
        }
        return p1.a < p2.a;
    });


    for(int i=0; i< n; i++){
        auto idx = upper_bound(lis.begin(), lis.end(), arr[i].b);
        if(idx == lis.end()){
            lis.push_back(arr[i].b);
        }else{
            *idx = arr[i].b;
        }
    }

    cout << lis.size();

}

