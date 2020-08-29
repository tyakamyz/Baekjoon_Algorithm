package Silver.Level_1;

/*
문제
수빈이는 동생과 숨바꼭질을 하고 있다. 수빈이는 현재 점 N(0 ≤ N ≤ 100,000)에 있고, 동생은 점 K(0 ≤ K ≤ 100,000)에 있다. 수빈이는 걷거나 순간이동을 할 수 있다. 만약, 수빈이의 위치가 X일 때 걷는다면 1초 후에 X-1 또는 X+1로 이동하게 된다. 순간이동을 하는 경우에는 1초 후에 2*X의 위치로 이동하게 된다.

수빈이와 동생의 위치가 주어졌을 때, 수빈이가 동생을 찾을 수 있는 가장 빠른 시간이 몇 초 후인지 구하는 프로그램을 작성하시오.

입력
첫 번째 줄에 수빈이가 있는 위치 N과 동생이 있는 위치 K가 주어진다. N과 K는 정수이다.

출력
수빈이가 동생을 찾는 가장 빠른 시간을 출력한다.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Q_1697_숨바꼭질 {

    public static void bfs(int N, int K) {
        int[] check = new int[100001];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(N);
        check[N] = 1;

        while (!queue.isEmpty()) {
            int temp = queue.poll();

            // 이동방법 3종류별로 bfs 실행
            for (int i = 0; i < 3; i++) {
                int next;

                if (i == 0) {
                    next = temp + 1;
                } else if (i == 1) {
                    next = temp - 1;
                } else {
                    next = temp * 2;
                }

                if (next == K) {
                    System.out.println(check[temp]);
                    return;
                }

                // 범위안에 있을 경우 실행. 이미 갔던곳이면 먼저 실행된 루트가 더 좋은 루트이기 때문에 볼필요가 없음
                if (next >= 0 && next < 100001 && check[next] == 0) {
                    queue.add(next);
                    check[next] = check[temp] + 1;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input[] = br.readLine().split(" ");

        int N = Integer.parseInt(input[0]);     // 수빈의 위치
        int K = Integer.parseInt(input[1]);     // 동생의 위치

        // 같은 위치에 있을 경우 종료
        if(N == K){
            System.out.println(0);
        }else{
            bfs(N, K);
        }
    }
}