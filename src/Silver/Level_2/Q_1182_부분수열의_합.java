package Silver.Level_2;

import java.util.Arrays;
import java.util.Scanner;

/*
문제
N개의 정수로 이루어진 수열이 있을 때, 크기가 양수인 부분수열 중에서 그 수열의 원소를 다 더한 값이 S가 되는 경우의 수를 구하는 프로그램을 작성하시오.

입력
첫째 줄에 정수의 개수를 나타내는 N과 정수 S가 주어진다. (1 ≤ N ≤ 20, |S| ≤ 1,000,000) 둘째 줄에 N개의 정수가 빈 칸을 사이에 두고 주어진다. 주어지는 정수의 절댓값은 100,000을 넘지 않는다.

출력
첫째 줄에 합이 S가 되는 부분수열의 개수를 출력한다.

예제 입력 1
5 0
-7 -3 -2 5 8
예제 출력 1
1
*/
public class Q_1182_부분수열의_합 {
    static int N;
    static int S;
    static int[] nums;
    static int result = 0;
    public static void main(String[] args) {
        input();
        recFunction(0, 0);

        if(S == 0) {
            result--;
        }
        System.out.println(result);
    }

    private static void recFunction(int k, int value) {
        if(k == N) {
            if(value == S) result++;
        }else {
            recFunction(k+1, value + nums[k]);
            recFunction(k+1, value);
        }
    }

    static void input() {
        Scanner scan = new Scanner(System.in);
        N = scan.nextInt();
        S = scan.nextInt();
        nums = new int[N];
        scan.nextLine();
        nums = Arrays.stream(scan.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }
}
