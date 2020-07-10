package Silver.Level_5;

/*
문제
N개의 수가 주어졌을 때, 이를 오름차순으로 정렬하는 프로그램을 작성하시오.

입력
첫째 줄에 수의 개수 N(1 ≤ N ≤ 1,000,000)이 주어진다. 둘째 줄부터 N개의 줄에는 숫자가 주어진다. 이 수는 절댓값이 1,000,000보다 작거나 같은 정수이다. 수는 중복되지 않는다.

출력
첫째 줄부터 N개의 줄에 오름차순으로 정렬한 결과를 한 줄에 하나씩 출력한다.
 */

import java.util.Scanner;

public class Q_2751_수_정렬하기2_퀵정렬 {

    public static void quickSort(int[] arr){
        quickSort(arr, 0, arr.length-1);    // 시작과 끝지점을 계산
    }

    public static void quickSort(int[] arr, int start, int end){
        int part = partition(arr, start, end);  // 가운데 위치를 중심으로 큰값과 작은값으로 구역을 나눔

        // 나눠진 구역의 작은값(왼쪽)을 정렬
        if(start < part-1){
            quickSort(arr, start, part-1);  // 재귀함수를 통해 위치를 변경하고 반복 실행
        }

        // 나눠진 구역의 큰값(오른쪽)을 정렬
        if(part < end){
            quickSort(arr, part, end);  // 재귀함수를 통해 위치를 변경하고 반복 실행
        }
    }

    private static int partition(int[] arr, int start, int end) {
        int pivot = arr[(start + end) / 2]; // 가운데 위치 찾기

        // 위치가 겹치기 전까지 값을 찾음
        while(start <= end){

            // pivot값 보다 큰값을 만나면 오른쪽으로 보내기위해 멈추고 빠져나옴. 큰값을 만날때까지 start 지점 +
            while (arr[start] < pivot){
                start++;
            };

            // pivot값 보다 작은값을 만나면 왼쪽으로 보내기위해 멈추고 빠져나옴. 작값을 만날때까지 end 지점 -
            while (arr[end] > pivot){
                end--;
            };

            // 멈춘 지점을 확인하고 위치를 변경. 이때 pivot의 위치를 넘어갈 수 있으므로 확인해줌
            if(start <= end){
                swap(arr, start, end);
                start++;
                end--;
            }
        }

        return start;   // 왼쪽부분은 정렬이 완료되었기 때문에 start지점을 앞으로 이동
    }

    private static void swap(int[] arr, int start, int end) {
        int temp = arr[start];
        arr[start] = arr[end];
        arr[end] = temp;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int N=0;
        N = scan.nextInt();

        int[] array = new int[N];

        for(int i=0; i<N; i++){
            array[i] = scan.nextInt();
        }

        quickSort(array);

        for(int result : array){
            System.out.println(result);
        }
    }
}
