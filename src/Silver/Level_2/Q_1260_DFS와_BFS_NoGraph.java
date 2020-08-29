package Silver.Level_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

/*
문제
그래프를 DFS로 탐색한 결과와 BFS로 탐색한 결과를 출력하는 프로그램을 작성하시오. 단, 방문할 수 있는 정점이 여러 개인 경우에는 정점 번호가 작은 것을 먼저 방문하고, 더 이상 방문할 수 있는 점이 없는 경우 종료한다. 정점 번호는 1번부터 N번까지이다.

입력
첫째 줄에 정점의 개수 N(1 ≤ N ≤ 1,000), 간선의 개수 M(1 ≤ M ≤ 10,000), 탐색을 시작할 정점의 번호 V가 주어진다. 다음 M개의 줄에는 간선이 연결하는 두 정점의 번호가 주어진다. 어떤 두 정점 사이에 여러 개의 간선이 있을 수 있다. 입력으로 주어지는 간선은 양방향이다.

출력
첫째 줄에 DFS를 수행한 결과를, 그 다음 줄에는 BFS를 수행한 결과를 출력한다. V부터 방문된 점을 순서대로 출력하면 된다.
 */
public class Q_1260_DFS와_BFS_NoGraph {

    static int N;
    static LinkedList<Integer>[] graph; // 인접리스트 형식의 그래프 생성

    // 재귀함수 dfs
    static void dfs(int node, boolean[] marked) {
        if(marked[node-1]) return;  // 이미 체크한 노드라면 종료하여 이전 재귀함수로 돌아감

        marked[node-1] = true;  // 체크 처리
        System.out.print(node + " ");   // 출력

        for(int nextNode:graph[node-1]) {   // 연결된 다음 리스트 값을 가져옴
            dfs(nextNode, marked);  // 재귀함수 호출
        }
    }

    // Queue bfs
    public static void bfs(int V, boolean[] marked) {
        Queue<Integer> queue = new LinkedList<Integer>();

        // 입력받은 값을 큐에 넣어줌
        queue.add(V);

        int node;   // 탐색할 정점
        while(!queue.isEmpty()) {
            node = queue.poll();    // 값을 꺼내서 탐색 시작 (최초에는 Queue에 V값만 들어있기 때문에 V부터 시작)

            if(marked[node-1] == false){
                marked[node-1] = true;
                System.out.print(node + " ");

                for(int nextNode:graph[node-1]) {
                    queue.add(nextNode);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input[] = br.readLine().split(" ");

        N = Integer.parseInt(input[0]);     // 노드 수
        int M = Integer.parseInt(input[1]);     // 간선 수
        int V = Integer.parseInt(input[2]);     // 시작 노드

        graph = new LinkedList[N];

        for(int i=0; i < N; i++) {
            graph[i] = new LinkedList<Integer>();
        }

        String edgeInput[];

        for(int i=0; i<M; i++){
            edgeInput = br.readLine().split(" ");

            int node1 = Integer.parseInt(edgeInput[0]);
            int node2 = Integer.parseInt(edgeInput[1]);

            // 배열은 0부터 시작임. 문제는 1부터 시작이기때문에 배열의 주소값은 항상 -1 해줌.
            graph[node1-1].add(node2);
            graph[node2-1].add(node1);

            // 문제에서 조건이 작은 수 부터 탐색이기 때문에 정렬을 해줘야함
            Collections.sort(graph[node1-1]);
            Collections.sort(graph[node2-1]);
        }

        // 탐색한 값인지 체크 할 배열을 만들어줌
        boolean[] dfsMarked = new boolean[N];
        boolean[] bfsMarked = new boolean[N];

        dfs(V, dfsMarked);
        System.out.println();
        bfs(V, bfsMarked);
    }
}
