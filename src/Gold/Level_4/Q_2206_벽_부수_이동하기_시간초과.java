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

public class Q_2206_벽_부수_이동하기_시간초과 {

    static int N = 0;
    static int M = 0;
    static int[][] map;
    static int[][] map_crush;
    static boolean[][] check;
    /* 상, 하, 좌, 우 */
    static int[] moveX = {1, -1, 0, 0};
    static int[] moveY = {0, 0, -1, 1};
    static List result = new ArrayList();

    public static void bfs() {
        Queue<Integer> queueN = new LinkedList<>(); // N축
        Queue<Integer> queueM = new LinkedList<>(); // M축

        queueN.add(0);
        queueM.add(0);

        check[0][0] = true;

        while(!queueN.isEmpty()) {
            int nodeN = queueN.poll();
            int nodeM = queueM.poll();

            // 상하좌우 차례로 체크
            for(int i=0; i<4; i++){
                int x = nodeN + moveX[i];
                int y = nodeM + moveY[i];

                if(x >= 0 && y >= 0 && x < N && y < M) { // 미로 범위내에 있는 좌표인지 확인
                    if (map_crush[x][y] == 0 && check[x][y] == false) {   // 이미 왔던 길인지 체크
                        queueN.offer(x);
                        queueM.offer(y);

                        check[x][y] = true;

                        map_crush[x][y] = map_crush[nodeN][nodeM] +1;  // 이동 횟수를 넣어줌
                    }
                }
            }
        }

        //System.out.println(map_crush[N-1][M-1]+1);

        check = new boolean[N][M];

        if(map_crush[N-1][M-1] > 0){
            // 시작지점도 포함해야하기 때문에 +1
            result.add(map_crush[N-1][M-1]+1);
        }
    }

    // 2차원 배열 복사
    public static void copyArray(){
        for(int i=0; i<map_crush.length; i++){
            System.arraycopy(map[i], 0, map_crush[i], 0, map_crush[0].length);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input[] = br.readLine().split(" ");

        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);

        map = new int[N][M];
        map_crush = new int[N][M];

        String temp[];

        for(int i=0; i<N; i++){
            temp = br.readLine().split("");
            for(int j=0; j<M; j++){
                map[i][j] = Integer.parseInt(temp[j]);
            }
        }

        // 벽이 없거나 안부숴도 될 경우를 대비
        check = new boolean[N][M];
        //map_crush = map.clone();
        copyArray();
        bfs();

        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                //map_crush = map.clone();    // 초기화
                // 벽을 하나씩 부숨
                if(map_crush[i][j] == 1){
                    map_crush[i][j] = 0;
                    bfs();
                    copyArray();
                };
            }
        }
        if(result.size() > 0){
            Collections.sort(result);	// 오름차순 정렬
            System.out.println(result.get(0).toString());
        }else{
            System.out.println(-1);
        }

    }
}