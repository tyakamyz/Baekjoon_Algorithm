package Silver.Level_4;

/*
문제
N개의 수가 주어졌을 때, 이를 오름차순으로 정렬하는 프로그램을 작성하시오.

입력
첫째 줄에 수의 개수 N(1 ≤ N ≤ 10,000,000)이 주어진다. 둘째 줄부터 N개의 줄에는 숫자가 주어진다. 이 수는 10,000보다 작거나 같은 자연수이다.

출력
첫째 줄부터 N개의 줄에 오름차순으로 정렬한 결과를 한 줄에 하나씩 출력한다.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q_10989_수_정렬하기3 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[10001]; // 최대 값이 10,000 이므로 10,000를 담을 수 있도록 10001로 생성

        for (int i = 0; i < N; i++) {
            arr[Integer.parseInt(br.readLine())]++; // 입력 받은 숫자를 위치값으로 찾고 해당 위치에 +1을 함
        }

        for (int i = 0; i < 10001; i++) {  // 최대 값이 10000 이므로 10000까지만 반복
            while (arr[i] != 0) {   // 해당 위치값이 0이라면 입력하지 않은 숫자
                sb.append("" + i + "\n");   // 해당 위치값에 값이 있다면 그 값을 버퍼에 담아 줌
                arr[i]--;   // 버퍼에 담았기 때문에 -1을 해줌. 값을 여러번 입력한 숫자일 경우 (arr[i] != 0) 조건을 통해 반복
            }

        }
        System.out.println(sb.toString());  // 버퍼에 담은 값 출력
    }
}