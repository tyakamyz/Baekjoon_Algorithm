package Gold.Level_4;

/*
문제
N×M의 행렬로 표현되는 맵이 있다. 맵에서 0은 이동할 수 있는 곳을 나타내고, 1은 이동할 수 없는 벽이 있는 곳을 나타낸다. 당신은 (1, 1)에서 (N, M)의 위치까지 이동하려 하는데, 이때 최단 경로로 이동하려 한다. 최단경로는 맵에서 가장 적은 개수의 칸을 지나는 경로를 말하는데, 이때 시작하는 칸과 끝나는 칸도 포함해서 센다.

만약에 이동하는 도중에 한 개의 벽을 부수고 이동하는 것이 좀 더 경로가 짧아진다면, 벽을 한 개 까지 부수고 이동하여도 된다.

맵이 주어졌을 때, 최단 경로를 구해 내는 프로그램을 작성하시오.

입력
첫째 줄에 N(1 ≤ N ≤ 1,000), M(1 ≤ M ≤ 1,000)이 주어진다. 다음 N개의 줄에 M개의 숫자로 맵이 주어진다. (1, 1)과 (N, M)은 항상 0이라고 가정하자.

출력
첫째 줄에 최단 거리를 출력한다. 불가능할 때는 -1을 출력한다.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Q_2206_벽_부수_이동하기 {

    static int N = 0;
    static int M = 0;
    static int[][] map;
    // 3차원 배열을 사용한 이유는 벽 뚫은 여부확인을 위해 사용함
    static boolean[][][] check;
    /* 상, 하, 좌, 우 */
    static int[] moveX = {1, -1, 0, 0};
    static int[] moveY = {0, 0, -1, 1};
    static List result = new ArrayList();
    static int count = 0;
    static boolean finish = false;

    public static void bfs() {
        Queue<Integer> queueN = new LinkedList<>(); // N축
        Queue<Integer> queueM = new LinkedList<>(); // M축
        Queue<Integer> queueDrillCnt = new LinkedList<>(); // 드릴 개수

        queueN.add(0);
        queueM.add(0);
        queueDrillCnt.add(1);

        check[0][0][1] = true;

        loop:while(!queueN.isEmpty()) {
            int size = queueN.size();

            for (int s = 0; s < size; s++) {
                int nodeN = queueN.poll();
                int nodeM = queueM.poll();
                int nodeDrillCnt = queueDrillCnt.poll();

                if(nodeN == N-1 && nodeM == M-1) {
                    count++;    // 도착지점 +1
                    finish = true;  // 도착여부
                    break loop; // loop 종료
                }

                // 상하좌우 차례로 체크
                for(int i=0; i<4; i++){
                    int x = nodeN + moveX[i];
                    int y = nodeM + moveY[i];

                    if(x >= 0 && y >= 0 && x < N && y < M) { // 미로 범위내에 있는 좌표인지 확인
                        if (map[x][y] == 1){ // 벽이 있다면
                            // 왔던길 인지 체크하고 벽을 부순적이 없는지 체크 (드릴 개수가 1인지 확인)
                            if (check[x][y][0] == false && nodeDrillCnt > 0) {
                                queueN.offer(x);
                                queueM.offer(y);
                                queueDrillCnt.offer(0);

                                check[x][y][0] = true;  // 길 체크
                            }
                        }else{  // 벽이 없다면
                            if (check[x][y][nodeDrillCnt]) continue;    // 왔던적이 있다면 이번 queue는 건너띔

                                // 벽을 부수고 왔던길과 벽을 부수지 않고 왔던길을 모두 확인함
                                check[x][y][nodeDrillCnt] = true;

                                queueN.offer(x);
                                queueM.offer(y);
                                queueDrillCnt.offer(nodeDrillCnt);
                        }
                    }
                }
            }
            count++;
        }

        if(finish){
            System.out.println(count);
        }else{
            System.out.println(-1);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input[] = br.readLine().split(" ");

        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);

        map = new int[N][M];

        String temp[];

        for(int i=0; i<N; i++){
            temp = br.readLine().split("");
            for(int j=0; j<M; j++){
                map[i][j] = Integer.parseInt(temp[j]);
            }
        }

        // 벽이 없거나 안부숴도 될 경우를 대비
        check = new boolean[N][M][2];
        bfs();

    }
}