package Silver.Level_5;
/*
문제
N개의 정수가 주어진다. 이때, N개의 정수를 오름차순으로 정렬하는 프로그램을 작성하시오. 같은 정수는 한 번만 출력한다.

입력
첫째 줄에 수의 개수 N (1 ≤ N ≤ 100,000)이 주어진다. 둘째에는 숫자가 주어진다. 이 수는 절댓값이 1,000보다 작거나 같은 정수이다.

출력
첫째 줄에 수를 오름차순으로 정렬한 결과를 출력한다. 이때, 같은 수는 한 번만 출력한다.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q_10867_중복_빼고_정렬하기 {
    public static void mergeSort(int[] arr){
        int[] temp = new int[arr.length];   // 같은 크기의 임시 배열을 생성
        mergeSort(arr, temp, 0, arr.length-1);  // 시작과 끝지점을 지정
    }

    public static void mergeSort(int[] arr, int[] temp, int start, int end){

        // 병합정렬 = 분열과 정복
        if(start < end){
            int mid = (start + end) / 2;    // 파티션을 나누기 위한 중간 위치값 찾기

            // 왼쪽배열 분열 시작. 최소단위로 분열. 더 이상 분열이 안될때 까지 계속 재귀함수 호출
            mergeSort(arr, temp, start, mid);

            // 오른쪽배열 분열 시작. 최소단위로 분열. 더 이상 분열이 안될때 까지 계속 재귀함수 호출
            mergeSort(arr, temp, mid+1, end);

            // 최소단위로 분열된 왼쪽과 오른쪽 배열을 병합(정복)
            merge(arr, temp, start, mid, end);
        }
    }

    private static void merge(int[] arr, int[] temp, int start, int mid, int end) {

        // 같은 크기의 임시 배열 생성
        for(int i=start; i<=end; i++){
            temp[i] = arr[i];
        }

        int part1 = start;  // 왼쪽 시작점
        int part2 = mid+1;  // 오른쪽 시작점
        int index = start;  // 값을 넣어줄 배열의 주소값

        while(part1 <= mid && part2 <= end){
            if(temp[part1] <= temp[part2]){
                arr[index] = temp[part1];
                part1++;
            }else{
                arr[index] = temp[part2];
                part2++;
            }
            index++;    // 값을 넣어준 뒤 한칸 앞으로 이동
        }

        // 왼쪽 배열보다 오른쪽 배열이 먼저 정렬이 끝날 경우 왼쪽의 수가 남아있을 수 있으므로 남은 값 확인 후 정렬
        // 오른쪽 배열은 이미 완성되어 있으므로 확인 필요 없음
        for(int i=0; i<=mid-part1; i++){
            arr[index+i] = temp[part1+i];
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        String temp = br.readLine();
        String[] temp_arr = temp.split(" ");
        int[] arr = new int[N];

        int i=0;
        for(String num : temp_arr){
            arr[i] = Integer.parseInt(num);
            i++;
        }

        mergeSort(arr);

        // 최초의 값은 중복체크 필요 없음
        sb.append(arr[0]);

        for(i=1; i<arr.length; i++) {
            // 중복체크
            if (!(arr[i] == arr[i-1])){
                sb.append(" " + arr[i]);
            }
        }

        System.out.println(sb.toString());
    }
}
