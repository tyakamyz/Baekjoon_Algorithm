package Silver.Level_1;

/*
N×M크기의 배열로 표현되는 미로가 있다.

1	0	1	1	1	1
1	0	1	0	1	0
1	0	1	0	1	1
1	1	1	0	1	1
미로에서 1은 이동할 수 있는 칸을 나타내고, 0은 이동할 수 없는 칸을 나타낸다. 이러한 미로가 주어졌을 때, (1, 1)에서 출발하여 (N, M)의 위치로 이동할 때 지나야 하는 최소의 칸 수를 구하는 프로그램을 작성하시오. 한 칸에서 다른 칸으로 이동할 때, 서로 인접한 칸으로만 이동할 수 있다.

위의 예에서는 15칸을 지나야 (N, M)의 위치로 이동할 수 있다. 칸을 셀 때에는 시작 위치와 도착 위치도 포함한다.

입력
첫째 줄에 두 정수 N, M(2 ≤ N, M ≤ 100)이 주어진다. 다음 N개의 줄에는 M개의 정수로 미로가 주어진다. 각각의 수들은 붙어서 입력으로 주어진다.

출력
첫째 줄에 지나야 하는 최소의 칸 수를 출력한다. 항상 도착위치로 이동할 수 있는 경우만 입력으로 주어진다.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q_2178_미로찾기 {

    static int N = 0;
    static int M = 0;
    static int[][] maze;
    static boolean[][] check;
                   /* 상, 하, 좌, 우 */
    static int[] moveX = {1, -1, 0, 0};
    static int[] moveY = {0, 0, -1, 1};

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
                   if (maze[x][y] == 1 && check[x][y] == false) {   // 이미 왔던 길인지 체크
                       queueN.offer(x);
                       queueM.offer(y);

                       check[x][y] = true;

                       maze[x][y] = maze[nodeN][nodeM] +1;  // 이동 횟수를 넣어줌
                   }
               }
            }
        }

        System.out.println(maze[N-1][M-1]);

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        String input[] = br.readLine().split(" ");

        N = Integer.parseInt(input[0]);
        M = Integer.parseInt(input[1]);

        maze = new int[N][M];
        check = new boolean[N][M];

        String temp[];

        for(int i=0; i<N; i++){
            temp = br.readLine().split("");
            for(int j=0; j<M; j++){
                maze[i][j] = Integer.parseInt(temp[j]);
            }
        }

        bfs();
    }
}