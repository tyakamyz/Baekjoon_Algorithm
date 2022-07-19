package Silver.Level_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 문제
 * 정사각형으로 이루어져 있는 섬과 바다 지도가 주어진다. 섬의 개수를 세는 프로그램을 작성하시오.
 * <p>
 * <p>
 * <p>
 * 한 정사각형과 가로, 세로 또는 대각선으로 연결되어 있는 사각형은 걸어갈 수 있는 사각형이다.
 * <p>
 * 두 정사각형이 같은 섬에 있으려면, 한 정사각형에서 다른 정사각형으로 걸어서 갈 수 있는 경로가 있어야 한다. 지도는 바다로 둘러싸여 있으며, 지도 밖으로 나갈 수 없다.
 * <p>
 * 입력
 * 입력은 여러 개의 테스트 케이스로 이루어져 있다. 각 테스트 케이스의 첫째 줄에는 지도의 너비 w와 높이 h가 주어진다. w와 h는 50보다 작거나 같은 양의 정수이다.
 * <p>
 * 둘째 줄부터 h개 줄에는 지도가 주어진다. 1은 땅, 0은 바다이다.
 * <p>
 * 입력의 마지막 줄에는 0이 두 개 주어진다.
 * <p>
 * 출력
 * 각 테스트 케이스에 대해서, 섬의 개수를 출력한다.
 * <p>
 * 예제 입력 1
 * 1 1
 * 0
 * 2 2
 * 0 1
 * 1 0
 * 3 2
 * 1 1 1
 * 1 1 1
 * 5 4
 * 1 0 1 0 0
 * 1 0 0 0 0
 * 1 0 1 0 1
 * 1 0 0 1 0
 * 5 4
 * 1 1 1 0 1
 * 1 0 1 0 1
 * 1 0 1 0 1
 * 1 0 1 1 1
 * 5 5
 * 1 0 1 0 1
 * 0 0 0 0 0
 * 1 0 1 0 1
 * 0 0 0 0 0
 * 1 0 1 0 1
 * 0 0
 */

public class Q_4963_섬의_개수 {
    static int[] move_x = {0, 0, -1, 1, -1, -1, 1, 1};
    static int[] move_y = {1, -1, 0, 0, 1, -1, 1, -1};
    static int count = 0;
    static int w;
    static int h;
    static int[][] map;
    static boolean[][] visit;
    static List<Integer> answers = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        while (true) {
            input();
            process();
        }
    }

    private static boolean input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] wh = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        w = wh[0];
        h = wh[1];
        if (w == 0 && h == 0) {
            answer();
            System.exit(0);
        }

        map = new int[h][w];
        visit = new boolean[h][w];

        for (int i = 0; i < h; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            if (map[i].length != w) throw new IllegalArgumentException();
        }

        return false;
    }

    private static void process() {
        count = 0;

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (map[i][j] == 1 && !visit[i][j]) {
                    count++;
                    dfs(i, j);
                }
            }
        }

        answers.add(count);
    }

    private static void dfs(int x, int y) {
        visit[x][y] = true;

        for (int i = 0; i < 8; i++) {
            int next_x = x + move_x[i];
            int next_y = y + move_y[i];

            if (next_x < 0 || next_y < 0 || next_x >= h || next_y >= w) continue;
            if (visit[next_x][next_y]) continue;
            if (map[next_x][next_y] != 1) continue;

            dfs(next_x, next_y);
        }
    }

    private static void answer() {
        for (int answer : answers) {
            System.out.println(answer);
        }
    }
}
