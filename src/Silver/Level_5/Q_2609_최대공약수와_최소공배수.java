package Silver.Level_5;

/*
문제
두 개의 자연수를 입력받아 최대 공약수와 최소 공배수를 출력하는 프로그램을 작성하시오.

입력
첫째 줄에는 두 개의 자연수가 주어진다. 이 둘은 10,000이하의 자연수이며 사이에 한 칸의 공백이 주어진다.

출력
첫째 줄에는 입력으로 주어진 두 수의 최대공약수를, 둘째 줄에는 입력으로 주어진 두 수의 최소 공배수를 출력한다.

예제 입력 1
24 18

예제 출력 1
6
72
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Q_2609_최대공약수와_최소공배수 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] inputArray = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int inputA = inputArray[0];
        int inputB = inputArray[1];

        int gcd = greatestCommonDivisor(inputA, inputB);
        int lcm = leastCommonMultiple(inputA, inputB, gcd);

        System.out.println(gcd);
        System.out.println(lcm);
    }

    /* 최대공약수 */
    private static int greatestCommonDivisor(int a, int b){
        if(a%b == 0) return b;
        else return greatestCommonDivisor(b, a%b);
    }

    /* 최소공배수 */
    private static int leastCommonMultiple(int a, int b, int gcd){
        return (a*b)/gcd;
    }
}
