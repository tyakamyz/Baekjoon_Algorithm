package Gold.Level_4;

import java.util.Scanner;

/*
문제
N-Queen 문제는 크기가 N × N인 체스판 위에 퀸 N개를 서로 공격할 수 없게 놓는 문제이다.

N이 주어졌을 때, 퀸을 놓는 방법의 수를 구하는 프로그램을 작성하시오.

입력
첫째 줄에 N이 주어진다. (1 ≤ N < 15)

출력
첫째 줄에 퀸 N개를 서로 공격할 수 없게 놓는 경우의 수를 출력한다.

예제 입력 1
8
예제 출력 1
92
*/
public class Q_9663_N_Queen {

    static int N;
    static int[] COL;
    static int RESULT;


    private static void recFunction(int row) {
        if(row == N+1) {
            RESULT++;
        }else {
            for(int c=1; c<=N; c++) {
                boolean isPossible = true;
                for(int i=1; i<=row-1; i++) {
                    if(isAttack(row, c, i, COL[i])) {
                        isPossible = false;
                        break;
                    }
                }

                if(isPossible) {
                    COL[row] = c;
                    recFunction(row+1);
                    COL[row] = 0;
                }
            }
        }
    }

    private static boolean isAttack(int row1, int col1, int row2, int col2) {
        if(row1 == row2) return true;
        if(col1 == col2) return true;
        if(row1+col1 == row2 + col2) return true;
        if(row1-col1 == row2 - col2) return true;

        return false;
    }

    public static void main(String[] args) {
        input();
        recFunction(1);

        System.out.println(RESULT);
    }

    static void input() {
        Scanner scan = new Scanner(System.in);
        N = scan.nextInt();
        COL = new int[N+1];
    }
}
