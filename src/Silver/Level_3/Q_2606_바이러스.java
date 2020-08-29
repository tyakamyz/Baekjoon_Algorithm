package Silver.Level_3;

/*
문제
신종 바이러스인 웜 바이러스는 네트워크를 통해 전파된다. 한 컴퓨터가 웜 바이러스에 걸리면 그 컴퓨터와 네트워크 상에서 연결되어 있는 모든 컴퓨터는 웜 바이러스에 걸리게 된다.

예를 들어 7대의 컴퓨터가 <그림 1>과 같이 네트워크 상에서 연결되어 있다고 하자. 1번 컴퓨터가 웜 바이러스에 걸리면 웜 바이러스는 2번과 5번 컴퓨터를 거쳐 3번과 6번 컴퓨터까지 전파되어 2, 3, 5, 6 네 대의 컴퓨터는 웜 바이러스에 걸리게 된다. 하지만 4번과 7번 컴퓨터는 1번 컴퓨터와 네트워크상에서 연결되어 있지 않기 때문에 영향을 받지 않는다.



어느 날 1번 컴퓨터가 웜 바이러스에 걸렸다. 컴퓨터의 수와 네트워크 상에서 서로 연결되어 있는 정보가 주어질 때, 1번 컴퓨터를 통해 웜 바이러스에 걸리게 되는 컴퓨터의 수를 출력하는 프로그램을 작성하시오.

입력
첫째 줄에는 컴퓨터의 수가 주어진다. 컴퓨터의 수는 100 이하이고 각 컴퓨터에는 1번 부터 차례대로 번호가 매겨진다. 둘째 줄에는 네트워크 상에서 직접 연결되어 있는 컴퓨터 쌍의 수가 주어진다. 이어서 그 수만큼 한 줄에 한 쌍씩 네트워크 상에서 직접 연결되어 있는 컴퓨터의 번호 쌍이 주어진다.

출력
1번 컴퓨터가 웜 바이러스에 걸렸을 때, 1번 컴퓨터를 통해 웜 바이러스에 걸리게 되는 컴퓨터의 수를 첫째 줄에 출력한다.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Q_2606_바이러스 {
    static int N;
    static LinkedList<Integer>[] graph;
    static int cnt = 0;

    public static void bfs(LinkedList<Integer>[] graph, boolean[] check) {
        Queue<Integer> queue = new LinkedList<Integer>();

        // 1번 컴퓨터 시작
        queue.add(1);

        int node;   // 탐색할 정점
        while(!queue.isEmpty()) {
            node = queue.poll();

            if(check[node] == false){
                check[node] = true;
                cnt++;

                for(int nextNode:graph[node]) {
                    queue.add(nextNode);
                }
            }
        }

        // 1번 컴퓨터 제외하고 출력
        System.out.println(cnt-1);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int computer = Integer.parseInt(br.readLine());
        N = Integer.parseInt(br.readLine());     // 노드 수

        graph = new LinkedList[computer+1];   // 컴퓨터 수만큼 생성 (배열은 0부터 시작이기때문에 +1을 잊으면 안됌)

        for(int i=0; i < computer+1; i++) {
            graph[i] = new LinkedList<Integer>();
        }

        String input[];
        int[][] network = new int[N][2];

        for(int i=0; i < N; i++) {
            input = br.readLine().split(" ");
            network[i][0] = Integer.parseInt(input[0]);
            network[i][1] = Integer.parseInt(input[1]);
        }

        int tempComputerNo = 0;

        for(int i=0; i < N; i++) {
            tempComputerNo = network[i][0];
            graph[tempComputerNo].add(network[i][1]);
        }

        // 반대의 경우로도 연결 (linkList 형식은 Node*2라는걸 잊으면 안됌)
        for(int i=0; i < N; i++) {
            tempComputerNo = network[i][1];
            graph[tempComputerNo].add(network[i][0]);
        }

        boolean check[] = new boolean[computer+1];

        bfs(graph, check);
    }
}