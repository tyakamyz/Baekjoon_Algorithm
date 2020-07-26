package Silver.Level_4;

/*
문제
옛날 옛적에 수학이 항상 큰 골칫거리였던 나라가 있었다. 이 나라의 국왕 김지민은 다음과 같은 문제를 내고 큰 상금을 걸었다.

길이가 N인 정수 배열 A와 B가 있다. 다음과 같이 함수 S를 정의하자.

S = A[0]*B[0] + ... + A[N-1]*B[N-1]

S의 값을 가장 작게 만들기 위해 A의 수를 재배열하자. 단, B에 있는 수는 재배열하면 안 된다.

S의 최솟값을 출력하는 프로그램을 작성하시오.

입력
첫째 줄에 N이 주어진다. 둘째 줄에는 A에 있는 N개의 수가 순서대로 주어지고, 셋째 줄에는 B에 있는 수가 순서대로 주어진다. N은 50보다 작거나 같은 자연수이고, A와 B의 각 원소는 100보다 작거나 같은 음이 아닌 정수이다.

출력
첫째 줄에 S의 최솟값을 출력한다.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q_1026_보물 {
    public static void mergeSort(int[] arr, String orderBy){
        int[] temp = new int[arr.length];   // 같은 크기의 임시 배열을 생성
        mergeSort(arr, temp, 0, arr.length-1, orderBy);  // 시작과 끝지점을 지정
    }

    public static void mergeSort(int[] arr, int[] temp, int start, int end, String orderBy){

        // 병합정렬 = 분열과 정복
        if(start < end){
            int mid = (start + end) / 2;    // 파티션을 나누기 위한 중간 위치값 찾기

            // 왼쪽배열 분열 시작. 최소단위로 분열. 더 이상 분열이 안될때 까지 계속 재귀함수 호출
            mergeSort(arr, temp, start, mid, orderBy);

            // 오른쪽배열 분열 시작. 최소단위로 분열. 더 이상 분열이 안될때 까지 계속 재귀함수 호출
            mergeSort(arr, temp, mid+1, end, orderBy);

            // 최소단위로 분열된 왼쪽과 오른쪽 배열을 병합(정복)
            merge(arr, temp, start, mid, end, orderBy);
        }
    }

    private static void merge(int[] arr, int[] temp, int start, int mid, int end, String orderBy) {

        // 같은 크기의 임시 배열 생성
        for(int i=start; i<=end; i++){
            temp[i] = arr[i];
        }

        int part1 = start;  // 왼쪽 시작점
        int part2 = mid+1;  // 오른쪽 시작점
        int index = start;  // 값을 넣어줄 배열의 주소값

        while(part1 <= mid && part2 <= end){
            if(temp[part1] <= temp[part2]){
                if(orderBy.equals("ASC")){
                    arr[index] = temp[part1];
                    part1++;
                }else if(orderBy.equals("DESC")){
                    arr[index] = temp[part2];
                    part2++;
                }
            }else{
                if(orderBy.equals("ASC")){
                    arr[index] = temp[part2];
                    part2++;
                }else if(orderBy.equals("DESC")){
                    arr[index] = temp[part1];
                    part1++;
                }
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

        int N = Integer.parseInt(br.readLine());
        int[] A = new int[N];
        int[] B = new int[N];
        long S = 0;
        StringTokenizer A_st = new StringTokenizer(br.readLine());
        StringTokenizer B_st = new StringTokenizer(br.readLine());

        int i = 0;
        while(A_st.hasMoreTokens()){
            A[i] = Integer.parseInt(A_st.nextToken());
            i++;
        }

        i = 0;
        while(B_st.hasMoreTokens()){
            B[i] = Integer.parseInt(B_st.nextToken());
            i++;
        }

        // 정렬
        mergeSort(A, "ASC");

        // 역순정렬
        mergeSort(B, "DESC");

        for(i=0; i<N; i++){
            S = S + (A[i] * B[i]);
        }

        System.out.println(S);

    }
}