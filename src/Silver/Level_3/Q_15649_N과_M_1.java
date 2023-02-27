package Silver.Level_3;

/*
문제
자연수 N과 M이 주어졌을 때, 아래 조건을 만족하는 길이가 M인 수열을 모두 구하는 프로그램을 작성하시오.

1부터 N까지 자연수 중에서 중복 없이 M개를 고른 수열
입력
첫째 줄에 자연수 N과 M이 주어진다. (1 ≤ M ≤ N ≤ 8)

출력
한 줄에 하나씩 문제의 조건을 만족하는 수열을 출력한다. 중복되는 수열을 여러 번 출력하면 안되며, 각 수열은 공백으로 구분해서 출력해야 한다.

수열은 사전 순으로 증가하는 순서로 출력해야 한다.

예제 입력 1
3 1
예제 출력 1
1
2
3
예제 입력 2
4 2
예제 출력 2
1 2
1 3
1 4
2 1
2 3
2 4
3 1
3 2
3 4
4 1
4 2
4 3
예제 입력 3
4 4
예제 출력 3
1 2 3 4
1 2 4 3
1 3 2 4
1 3 4 2
1 4 2 3
1 4 3 2
2 1 3 4
2 1 4 3
2 3 1 4
2 3 4 1
2 4 1 3
2 4 3 1
3 1 2 4
3 1 4 2
3 2 1 4
3 2 4 1
3 4 1 2
3 4 2 1
4 1 2 3
4 1 3 2
4 2 1 3
4 2 3 1
4 3 1 2
4 3 2 1
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Q_15649_N과_M_1 {
    static int N, M;
    static int[] answer, used;
    static StringBuffer sb = new StringBuffer();

    public static void main(String[] args) throws IOException {
        input();

        recFunction(1);

        System.out.println(sb.toString());
    }

    private static void recFunction(int k) {
        if(k == M+1) {
            for(int i=1; i<=M; i++) {
                sb.append(answer[i]);
                sb.append(" ");
            }
            sb.append("\n");
        } else {
            for(int i=1; i<=N; i++) {
                if(used[i] == 1) continue;

                answer[k] = i;
                used[i] = 1;
                recFunction(k+1);

                used[i] = 0;
            }
        }
    }

    private static void input() throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = input[0];
        M = input[1];

        answer = new int[M+1];
        used = new int[M+1];
    }
}
