package Silver.Level_5;

/*
문제
알파벳 소문자로 이루어진 N개의 단어가 들어오면 아래와 같은 조건에 따라 정렬하는 프로그램을 작성하시오.

길이가 짧은 것부터
길이가 같으면 사전 순으로
입력
첫째 줄에 단어의 개수 N이 주어진다. (1≤N≤20,000) 둘째 줄부터 N개의 줄에 걸쳐 알파벳 소문자로 이루어진 단어가 한 줄에 하나씩 주어진다. 주어지는 문자열의 길이는 50을 넘지 않는다.

출력
조건에 따라 정렬하여 단어들을 출력한다. 단, 같은 단어가 여러 번 입력된 경우에는 한 번씩만 출력한다.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q_1181_단어_정렬 {
    private static void mergeSort(String[][] arr){
        String[][] temp = new String[arr.length][2];   // 같은 크기의 임시 배열을 생성
        mergeSort(arr, temp, 0, arr.length-1);  // 시작과 끝지점을 지정
    }

    private static void mergeSort(String[][] arr, String[][] temp, int start, int end){

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

    private static void merge(String[][] arr, String[][] temp, int start, int mid, int end) {

        // 같은 크기의 임시 배열 생성
        for(int i=start; i<=end; i++){
            temp[i][0] = arr[i][0];
            temp[i][1] = arr[i][1];
        }

        int part1 = start;  // 왼쪽 시작점
        int part2 = mid+1;  // 오른쪽 시작점
        int index = start;  // 값을 넣어줄 배열의 주소값

        while(part1 <= mid && part2 <= end){
            if(Integer.parseInt(temp[part1][1]) < Integer.parseInt(temp[part2][1])){
                arr[index][0] = temp[part1][0];
                arr[index][1] = temp[part1][1];
                part1++;
            }else if(Integer.parseInt(temp[part1][1]) == Integer.parseInt(temp[part2][1])){
                // 글자수가 같을 경우 사전적 비교. temp[part1][0] < temp[part2][0]. 같다면 0, 틀리다면 음, 맞으면 양수
                if(temp[part1][0].compareTo(temp[part2][0]) < 0){
                    arr[index][0] = temp[part1][0];
                    arr[index][1] = temp[part1][1];
                    part1++;
                }else{
                    arr[index][0] = temp[part2][0];
                    arr[index][1] = temp[part2][1];
                    part2++;
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

        // 2차원 배열을 사용하여 0번 행에는 값을 넣고 1번 행에는 글자의 수를 넣음
        String[][] word = new String[N][2];

        for(int i=0; i<N; i++){
            word[i][0] = br.readLine();
            word[i][1] = Integer.toString(word[i][0].length());
        }

        mergeSort(word);

        // 최초의 값은 중복체크 필요 없음
        sb.append(word[0][0] + "\n");

        for(int i=1; i<word.length; i++) {
            // 중복체크
            if (!word[i][0].equals(word[i-1][0])){
                sb.append("" + word[i][0] + "\n");
            }
        }

        System.out.println(sb);
    }
}
