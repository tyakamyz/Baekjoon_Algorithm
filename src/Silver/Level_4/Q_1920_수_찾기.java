package Silver.Level_4;

/*
문제
N개의 정수 A[1], A[2], …, A[N]이 주어져 있을 때, 이 안에 X라는 정수가 존재하는지 알아내는 프로그램을 작성하시오.

입력
첫째 줄에 자연수 N(1≤N≤100,000)이 주어진다. 다음 줄에는 N개의 정수 A[1], A[2], …, A[N]이 주어진다. 다음 줄에는 M(1≤M≤100,000)이 주어진다. 다음 줄에는 M개의 수들이 주어지는데, 이 수들이 A안에 존재하는지 알아내면 된다. 모든 정수의 범위는 -231 보다 크거나 같고 231보다 작다.

출력
M개의 줄에 답을 출력한다. 존재하면 1을, 존재하지 않으면 0을 출력한다.
 */

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Stream;

public class Q_1920_수_찾기 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String N;
        String M;

        int[] n_array;
        String[] n_array_temp;
        String[] m_array;

        scan.nextLine();
        N = scan.nextLine();
        scan.nextLine();
        M = scan.nextLine();

        n_array_temp = N.split(" ");    // 입력값 배열로 변경
        n_array = StringArrayToIntArray(n_array_temp);  // String[] to int[]
        m_array = M.split(" ");     // 입력값 배열로 변경

        Arrays.sort(n_array);   // 이진탐색을 위해 기준값 정렬

        int result = 0;

        // 초기값 생성
        int start = 0;
        int mid = 0;
        int last = n_array.length-1;

        int m = 0;  // 숫자로 변경된 값을 받기 위한 변수

        for(String string_m:m_array){
            m = Integer.parseInt(string_m);     // String to int
            while(start <= last){

                mid = (start + last) / 2; // 가운데 위치 찾기

                if(m == n_array[mid]){
                    result++;
                    break;
                }else if(m < n_array[mid]){
                    last = mid-1;   // 현재 위치한곳까지 제외하기위해 -1을 해줌
                }else{
                    start = mid+1;  // 현재 위치한곳까지 제외하기위해 +1을 해줌
                }
            }

            System.out.println(result);     // 결과값 출력

            // 초기화
            result = 0;
            start = 0;
            last = n_array.length-1;
        }
    }

    // String[] to int[] 변경 함수
    static int[] StringArrayToIntArray(String[] stringArray)
    {
        return Stream.of(stringArray).mapToInt(Integer::parseInt).toArray();
    }
}
