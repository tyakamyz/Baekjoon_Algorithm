package Bronze.Level_1;

/*
[문제]
두 정수 A와 B를 입력받은 다음, A+B를 출력하는 프로그램을 작성하시오.

[입력]
첫째 줄에 테스트 케이스의 개수 T가 주어진다.

각 테스트 케이스는 한 줄로 이루어져 있으며, 각 줄에 A와 B가 주어진다. A와 B는 콤마(,)로 구분되어 있다. (0 < A, B < 10)

[출력]
각 테스트 케이스마다 A+B를 출력한다.
 */

import java.util.Scanner;

public class Q_10953_A_ADD_B_MINUS_6 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int A, B, T;
        String A_B_input;
        String[] A_B_temp;

        T = scan.nextInt();
        scan.nextLine();

        for(int i=0; i<T; i++){
            A_B_input = scan.nextLine();
            A_B_temp = A_B_input.split(",");

            A = Integer.parseInt(A_B_temp[0]);
            B = Integer.parseInt(A_B_temp[1]);

            System.out.println(A+B);
        }
    }
}
