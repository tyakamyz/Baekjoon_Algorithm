package Silver.Level_2;

/*
문제
그래프를 DFS로 탐색한 결과와 BFS로 탐색한 결과를 출력하는 프로그램을 작성하시오. 단, 방문할 수 있는 정점이 여러 개인 경우에는 정점 번호가 작은 것을 먼저 방문하고, 더 이상 방문할 수 있는 점이 없는 경우 종료한다. 정점 번호는 1번부터 N번까지이다.

입력
첫째 줄에 정점의 개수 N(1 ≤ N ≤ 1,000), 간선의 개수 M(1 ≤ M ≤ 10,000), 탐색을 시작할 정점의 번호 V가 주어진다. 다음 M개의 줄에는 간선이 연결하는 두 정점의 번호가 주어진다. 어떤 두 정점 사이에 여러 개의 간선이 있을 수 있다. 입력으로 주어지는 간선은 양방향이다.

출력
첫째 줄에 DFS를 수행한 결과를, 그 다음 줄에는 BFS를 수행한 결과를 출력한다. V부터 방문된 점을 순서대로 출력하면 된다.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// Graph 생성
class Graph_{
    class Node{
        int data;   // 정점(Node)의 값
        LinkedList<Node> adjacent;  // 인접한 정점을 담을 List
        boolean marked;     // 체크여부 확인을 위한 마커

        Node(int data){
            this.data = data;
            this.marked = false;
            adjacent = new LinkedList<Node>();
        }
    }

    Node[] nodes;

    // 생성자를 통해 입력받은 N의 개수만큼 노드를 생성해줌
    Graph_(int size){
        nodes = new Node[size];

        // 노드의 값은 1부터 생성
        for(int i=0; i<size; i++){
             nodes[i] = new Node(i+1);
        }
    }

    // 간선(Edge) 추가 함수
    void addEdge(int index_1, int index_2){
        // 입력받은 값의 -1을 하여 주소값을 찾음
        Node n1 = nodes[index_1-1];
        Node n2 = nodes[index_2-1];

        if(!n1.adjacent.contains(n2)){  // n2의 값이 없다면 n1 list에 담아줌
            n1.adjacent.add(n2);
        }
        if(!n2.adjacent.contains(n1)){  // n1의 값이 없다면 n2 list에 담아줌
            n2.adjacent.add(n1);
        }
    }

    // 시작점이 될 정점(Node)의 값을 받아옴
    void dfs(int index){
        Node root = nodes[index-1];     // 주소값은 -1
        Stack<Node> stack = new Stack<Node>();  // Node를 담고 출력할 stack 생성
        stack.push(root);   // 시작점을 넣어줌
        root.marked = true; // 다시 불러올 일이 없도록 체크

        while (!stack.empty()){
            Node r = stack.pop();   // 탐색을 위해 가장 마지막(하위노드)에 담은 값을 r에 담아줌
            for(Node n:r.adjacent){     // r에 연결된 list (즉, 연결된 노드들)를 차례로 불러옴
                if(n.marked == false){  // 이미 체크된 값일 경우 넘어감
                    n.marked = true;    // 다시 불러올 일이 없도록 체크값을 변경
                    stack.push(n);      // 하위노드를 stack에 담아줌
                }
            }
            System.out.print(r.data + " ");   // 기준이 된 탐색 노드는 출력하고 스택에 더이상 들어가지 않음. mark를 했기때문에 다시 들어갈 일없음
        }
    }

    // 시작점이 될 정점(Node)의 값을 받아옴
    void bfs(int index){

        Node root = nodes[index-1];     // 주소값은 -1
        Queue<Node> queue = new LinkedList<>();  // Node를 담고 출력할 queue 생성
        queue.add(root);   // 시작점을 넣어줌
        root.marked = true; // 다시 불러올 일이 없도록 체크

        while (!queue.isEmpty()){
            Node r = queue.poll();   // 탐색을 위해 가장 마지막(하위노드)에 담은 값을 r에 담아줌
            for(Node n:r.adjacent){     // r에 연결된 list (즉, 연결된 노드들)를 차례로 불러옴
                if(n.marked == false){  // 이미 체크된 값일 경우 넘어감
                    n.marked = true;    // 다시 불러올 일이 없도록 체크값을 변경
                    queue.add(n);      // 하위노드를 queue에 담아줌
                }
            }
            System.out.print(r.data + " ");   // 기준이 된 탐색 노드는 출력하고 스택에 더이상 들어가지 않음. mark를 했기때문에 다시 들어갈 일없음
        }
    }
}

public class Q_1260_DFS와_BFS_Stack_Queue {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input[] = br.readLine().split(" ");

        int N = Integer.parseInt(input[0]);     // 노드 수
        int M = Integer.parseInt(input[1]);     // 간선 수
        int V = Integer.parseInt(input[2]);     // 시작 노드

        // 입력받은 노드 수 만큼 노드 생성
        Graph_ graph = new Graph_(N);
        Graph_ graph2 = new Graph_(N);

        String edgeInput[];

        for(int i=0; i<M; i++){
            edgeInput = br.readLine().split(" ");
            graph.addEdge(Integer.parseInt(edgeInput[0]), Integer.parseInt(edgeInput[1]));
            graph2.addEdge(Integer.parseInt(edgeInput[0]), Integer.parseInt(edgeInput[1]));
        }

        graph.dfs(V);
        System.out.println();
        graph2.bfs(V);
    }
}
