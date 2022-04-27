package Silver.Level_4;

/*
문제
N개의 정수 A[1], A[2], …, A[N]이 주어져 있을 때, 이 안에 X라는 정수가 존재하는지 알아내는 프로그램을 작성하시오.

입력
첫째 줄에 자연수 N(1≤N≤100,000)이 주어진다. 다음 줄에는 N개의 정수 A[1], A[2], …, A[N]이 주어진다. 다음 줄에는 M(1≤M≤100,000)이 주어진다. 다음 줄에는 M개의 수들이 주어지는데, 이 수들이 A안에 존재하는지 알아내면 된다. 모든 정수의 범위는 -231 보다 크거나 같고 231보다 작다.

출력
M개의 줄에 답을 출력한다. 존재하면 1을, 존재하지 않으면 0을 출력한다.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Q_1920_수_찾기_복습 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String xStr = br.readLine();
        List<Integer> xList = stringToIntegerList(xStr);
        if(n != xList.size()) new IllegalArgumentException();

        int m = Integer.parseInt(br.readLine());
        String aStr = br.readLine();
        List<Integer> aList = stringToIntegerList(aStr);
        if(n != aList.size()) new IllegalArgumentException();

        xList.sort(Integer::compareTo);
        for(int number : aList){
            if(binarySearch(xList, number)){
                System.out.println(1);
            }else {
                System.out.println(0);
            }
        }
    }

    private static boolean binarySearch(List<Integer> integerList, int number) {
        if(integerList.size() == 0) return false;

        int mid = integerList.size()/2;
        int comparision = integerList.get(mid);
        if(comparision == number) return true;
        else if(integerList.size() == 1) return false;

        if(number > comparision) return binarySearch(integerList.subList(mid, integerList.size()), number);
        else return binarySearch(integerList.subList(0, mid), number);
    }

    private static List<Integer> stringToIntegerList(String str) {
        return Arrays.stream(str.split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }
}
