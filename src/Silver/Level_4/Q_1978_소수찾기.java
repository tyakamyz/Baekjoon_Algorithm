package Silver.Level_4;

/*
문제
주어진 수 N개 중에서 소수가 몇 개인지 찾아서 출력하는 프로그램을 작성하시오.

입력
첫 줄에 수의 개수 N이 주어진다. N은 100이하이다. 다음으로 N개의 수가 주어지는데 수는 1,000 이하의 자연수이다.

출력
주어진 수들 중 소수의 개수를 출력한다.
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Q_1978_소수찾기 {

    private static List<Integer> stringToIntegerList(String inputNumbers) {
        return Arrays.stream(inputNumbers.split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    /* 에라토스테네스의 체 */
    public static boolean eratosthenes(int n){
        // 0과 1은 소수가 아니므로 바로 종료.
        if(n == 0) return false;
        if(n == 1) return false;

        // 2~n의 제곱근까지 돌면서 나누어 떨어지면 소수가 아니므로 메소드 종료.
        for(int i=2; i <= Math.sqrt(n); i++){
            if(n % i == 0) return false;
        }

        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int size = Integer.parseInt(br.readLine());
        String inputNumbers = br.readLine();

        List<Integer> integerList = stringToIntegerList(inputNumbers);
        if(integerList.size() != size) throw new IllegalArgumentException();

        int resultCount = 0;
        for(int n : integerList){
            if(eratosthenes(n)) resultCount++;
        }

        System.out.println(resultCount);
    }
}
