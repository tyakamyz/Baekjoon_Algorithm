package Silver.Level_4;

/*
문제
숫자 카드는 정수 하나가 적혀져 있는 카드이다. 상근이는 숫자 카드 N개를 가지고 있다. 정수 M개가 주어졌을 때, 이 수가 적혀있는 숫자 카드를 상근이가 가지고 있는지 아닌지를 구하는 프로그램을 작성하시오.

입력
첫째 줄에 상근이가 가지고 있는 숫자 카드의 개수 N(1 ≤ N ≤ 500,000)이 주어진다. 둘째 줄에는 숫자 카드에 적혀있는 정수가 주어진다. 숫자 카드에 적혀있는 수는 -10,000,000보다 크거나 같고, 10,000,000보다 작거나 같다. 두 숫자 카드에 같은 수가 적혀있는 경우는 없다.

셋째 줄에는 M(1 ≤ M ≤ 500,000)이 주어진다. 넷째 줄에는 상근이가 가지고 있는 숫자 카드인지 아닌지를 구해야 할 M개의 정수가 주어지며, 이 수는 공백으로 구분되어져 있다. 이 수도 -10,000,000보다 크거나 같고, 10,000,000보다 작거나 같다

출력
첫째 줄에 입력으로 주어진 M개의 수에 대해서, 각 수가 적힌 숫자 카드를 상근이가 가지고 있으면 1을, 아니면 0을 공백으로 구분해 출력한다.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q_10815_숫자_카드 {
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

    public static void binrySearch(int[] arr1, int[] arr2){
        int result = 0;

        // 초기값 생성
        int start = 0;
        int mid = 0;
        int last = arr1.length-1;

        for(int m:arr2){
            while(start <= last){
                mid = (start + last) / 2; // 가운데 위치 찾기

                if(m == arr1[mid]){
                    result++;
                    break;
                }else if(m < arr1[mid]){
                    last = mid-1;   // 현재 위치한곳까지 제외하기위해 -1을 해줌
                }else{
                    start = mid+1;  // 현재 위치한곳까지 제외하기위해 +1을 해줌
                }
            }

            System.out.println(result);     // 결과값 출력

            // 초기화
            result = 0;
            start = 0;
            last = arr1.length-1;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int[] arr1 = new int[N];
        StringTokenizer arr1_st = new StringTokenizer(br.readLine());

        int i = 0;
        while(arr1_st.hasMoreTokens()){
            arr1[i] = Integer.parseInt(arr1_st.nextToken());
            i++;
        }

        int M = Integer.parseInt(br.readLine());
        int[] arr2 = new int[M];
        StringTokenizer arr2_st = new StringTokenizer(br.readLine());

        i = 0;
        while(arr2_st.hasMoreTokens()){
            arr2[i] = Integer.parseInt(arr2_st.nextToken());
            i++;
        }

        mergeSort(arr1);
        binrySearch(arr1, arr2);
    }
}
