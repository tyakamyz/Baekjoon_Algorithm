package Silver.Level_5;

/*
문제
2차원 평면 위의 점 N개가 주어진다. 좌표를 x좌표가 증가하는 순으로, x좌표가 같으면 y좌표가 증가하는 순서로 정렬한 다음 출력하는 프로그램을 작성하시오.

입력
첫째 줄에 점의 개수 N (1 ≤ N ≤ 100,000)이 주어진다. 둘째 줄부터 N개의 줄에는 i번점의 위치 xi와 yi가 주어진다. (-100,000 ≤ xi, yi ≤ 100,000) 좌표는 항상 정수이고, 위치가 같은 두 점은 없다.

출력
첫째 줄부터 N개의 줄에 점을 정렬한 결과를 출력한다.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q_11650_좌표_정렬하기 {

    private static void mergeSort(int[][] arr){
        int[][] temp = new int[arr.length][2];   // 같은 크기의 임시 배열을 생성
        mergeSort(arr, temp, 0, arr.length-1);  // 시작과 끝지점을 지정
    }

    private static void mergeSort(int[][] arr, int[][] temp, int start, int end){

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

    private static void merge(int[][] arr, int[][] temp, int start, int mid, int end) {

        // 같은 크기의 임시 배열 생성
        for(int i=start; i<=end; i++){
            temp[i][0] = arr[i][0];
            temp[i][1] = arr[i][1];
        }

        int part1 = start;  // 왼쪽 시작점
        int part2 = mid+1;  // 오른쪽 시작점
        int index = start;  // 값을 넣어줄 배열의 주소값

        while(part1 <= mid && part2 <= end){
            if(temp[part1][0] < temp[part2][0]){
                arr[index][0] = temp[part1][0];
                arr[index][1] = temp[part1][1];
                part1++;
            }else if(temp[part1][0] == temp[part2][0]){
                // x가 같을 경우 y 비교
                if(temp[part1][1] > temp[part2][1]){
                    arr[index][0] = temp[part2][0];
                    arr[index][1] = temp[part2][1];
                    part2++;
                }else{
                    arr[index][0] = temp[part1][0];
                    arr[index][1] = temp[part1][1];
                    part1++;
                }
            }else{
                arr[index][0] = temp[part2][0];
                arr[index][1] = temp[part2][1];
                part2++;
            }
            index++;    // 값을 넣어준 뒤 한칸 앞으로 이동
        }

        // 왼쪽 배열보다 오른쪽 배열이 먼저 정렬이 끝날 경우 왼쪽의 수가 남아있을 수 있으므로 남은 값 확인 후 정렬
        // 오른쪽 배열은 이미 완성되어 있으므로 확인 필요 없음
        for(int i=0; i<=mid-part1; i++){
            arr[index+i][0] = temp[part1+i][0];
            arr[index+i][1] = temp[part1+i][1];
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        // 2차원 배열을 사용하여 0번 행에는 x값을 넣고 1번 행에는 y값을 넣음
        int[][] xy = new int[N][2];

        String temp = "";
        String temp_xy[] = null;

        for(int i=0; i<N; i++){
            temp = br.readLine();
            temp_xy = temp.split(" ");

            xy[i][0] = Integer.parseInt(temp_xy[0]);
            xy[i][1] = Integer.parseInt(temp_xy[1]);
        }

        mergeSort(xy);

        for(int i=0; i<xy.length; i++) {
            sb.append("" + xy[i][0] + " " + xy[i][1] + "\n");
        }

        System.out.println(sb);
    }
}
