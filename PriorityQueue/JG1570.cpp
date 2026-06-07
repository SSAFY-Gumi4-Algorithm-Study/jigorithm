#include <iostream>
#include <queue>
#include <vector>

using namespace std;

struct Node {
    int value;
};

struct lCmp {
    bool operator()(Node a, Node b) {
        return a.value < b.value;
    }
};

struct rCmp {
    bool operator()(Node a, Node b) {
        return a.value > b.value;
    }
};

int n, lSize = 0, rSize = 0;

priority_queue<Node, vector<Node>, lCmp> lPq;
priority_queue<Node, vector<Node>, rCmp> rPq;

void rebuild(){
    
    while(lSize != rSize + 1){
        Node node = lPq.top();
        lPq.pop();

        rPq.push(node);
        lSize--;
        rSize++;
    }

    while(1){
        Node lNode = lPq.top();
        Node rNode = rPq.top();

        int lKey = lNode.value;
        int rKey = rNode.value;

        if(lKey > rKey){
            lPq.pop();
            rPq.pop();

            lPq.push(rNode);
            rPq.push(lNode);
        }else{
            break;
        }
    }
}

int main(){
    ios::sync_with_stdio(false);
    cin.tie(NULL); cout.tie(NULL);

    cin >> n;
    int a, b;
    cin >> a;

    lPq.push({a});
    lSize++;
    cout << a << "\n";

    for(int i=0; i< (n-1) / 2; i++){
        cin >> a >> b;

        lPq.push({a});
        lPq.push({b});
        lSize += 2;

        rebuild();

        cout << lPq.top().value << "\n";
    }
}