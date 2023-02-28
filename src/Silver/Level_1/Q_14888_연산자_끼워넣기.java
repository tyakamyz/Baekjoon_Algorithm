package Silver.Level_1;

/*
문제
N개의 수로 이루어진 수열 A1, A2, ..., AN이 주어진다. 또, 수와 수 사이에 끼워넣을 수 있는 N-1개의 연산자가 주어진다. 연산자는 덧셈(+), 뺄셈(-), 곱셈(×), 나눗셈(÷)으로만 이루어져 있다.

우리는 수와 수 사이에 연산자를 하나씩 넣어서, 수식을 하나 만들 수 있다. 이때, 주어진 수의 순서를 바꾸면 안 된다.

예를 들어, 6개의 수로 이루어진 수열이 1, 2, 3, 4, 5, 6이고, 주어진 연산자가 덧셈(+) 2개, 뺄셈(-) 1개, 곱셈(×) 1개, 나눗셈(÷) 1개인 경우에는 총 60가지의 식을 만들 수 있다. 예를 들어, 아래와 같은 식을 만들 수 있다.

1+2+3-4×5÷6
1÷2+3+4-5×6
1+2÷3×4-5+6
1÷2×3-4+5+6
식의 계산은 연산자 우선 순위를 무시하고 앞에서부터 진행해야 한다. 또, 나눗셈은 정수 나눗셈으로 몫만 취한다. 음수를 양수로 나눌 때는 C++14의 기준을 따른다. 즉, 양수로 바꾼 뒤 몫을 취하고, 그 몫을 음수로 바꾼 것과 같다. 이에 따라서, 위의 식 4개의 결과를 계산해보면 아래와 같다.

1+2+3-4×5÷6 = 1
1÷2+3+4-5×6 = 12
1+2÷3×4-5+6 = 5
1÷2×3-4+5+6 = 7
N개의 수와 N-1개의 연산자가 주어졌을 때, 만들 수 있는 식의 결과가 최대인 것과 최소인 것을 구하는 프로그램을 작성하시오.

입력
첫째 줄에 수의 개수 N(2 ≤ N ≤ 11)가 주어진다. 둘째 줄에는 A1, A2, ..., AN이 주어진다. (1 ≤ Ai ≤ 100) 셋째 줄에는 합이 N-1인 4개의 정수가 주어지는데, 차례대로 덧셈(+)의 개수, 뺄셈(-)의 개수, 곱셈(×)의 개수, 나눗셈(÷)의 개수이다.

출력
첫째 줄에 만들 수 있는 식의 결과의 최댓값을, 둘째 줄에는 최솟값을 출력한다. 연산자를 어떻게 끼워넣어도 항상 -10억보다 크거나 같고, 10억보다 작거나 같은 결과가 나오는 입력만 주어진다. 또한, 앞에서부터 계산했을 때, 중간에 계산되는 식의 결과도 항상 -10억보다 크거나 같고, 10억보다 작거나 같다.

예제 입력 1
2
5 6
0 0 1 0
예제 출력 1
30
30
예제 입력 2
3
3 4 5
1 0 1 0
예제 출력 2
35
17
예제 입력 3
6
1 2 3 4 5 6
2 1 1 1
예제 출력 3
54
-24
힌트
세 번째 예제의 경우에 다음과 같은 식이 최댓값/최솟값이 나온다.

최댓값: 1-2÷3+4+5×6
최솟값: 1+2+3÷4-5×6
*/

import java.util.Arrays;
import java.util.Scanner;

public class Q_14888_연산자_끼워넣기 {

    static int N;
    static int[] OPERATOR_COUNT;
    static int[] NUM;
    static int MAX;
    static int MIN;

    public static void main(String[] args) {
        input();
        recFunction(0, NUM[0]);

        System.out.println(MAX);
        System.out.println(MIN);
    }

    private static void recFunction(int k, int value) {
        if(k == N-1) {
            // 최종 결과 MAX, MIN 값 비교
            MAX = Math.max(MAX, value);
            MIN = Math.min(MIN, value);
        } else {
            for(int i=0; i<4; i++) {
                if(OPERATOR_COUNT[i] > 0) {
                    OPERATOR_COUNT[i]--;
                    recFunction(k+1, calculation(value, i, NUM[k + 1]));
                    OPERATOR_COUNT[i]++;
                }
            }
       }
    }

    private static int calculation(int value1, int operator, int value2) {
        if(operator == 0) {
            return value1 + value2;
        }else if(operator == 1) {
            return value1 - value2;
        }else if(operator == 2) {
            return value1 * value2;
        }else {
            return value1 / value2;
        }
    }

    private static void input() {
        Scanner scan = new Scanner(System.in);

        N = Integer.parseInt(scan.nextLine());
        NUM = new int[N];

        NUM = Arrays.stream(scan.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        OPERATOR_COUNT = Arrays.stream(scan.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        MAX = Integer.MIN_VALUE;
        MIN = Integer.MAX_VALUE;
    }
}
