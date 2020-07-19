package Silver.Level_4;

/*
문제
정수를 저장하는 덱(Deque)를 구현한 다음, 입력으로 주어지는 명령을 처리하는 프로그램을 작성하시오.

명령은 총 여덟 가지이다.

push_front X: 정수 X를 덱의 앞에 넣는다.
push_back X: 정수 X를 덱의 뒤에 넣는다.
pop_front: 덱의 가장 앞에 있는 수를 빼고, 그 수를 출력한다. 만약, 덱에 들어있는 정수가 없는 경우에는 -1을 출력한다.
pop_back: 덱의 가장 뒤에 있는 수를 빼고, 그 수를 출력한다. 만약, 덱에 들어있는 정수가 없는 경우에는 -1을 출력한다.
size: 덱에 들어있는 정수의 개수를 출력한다.
empty: 덱이 비어있으면 1을, 아니면 0을 출력한다.
front: 덱의 가장 앞에 있는 정수를 출력한다. 만약 덱에 들어있는 정수가 없는 경우에는 -1을 출력한다.
back: 덱의 가장 뒤에 있는 정수를 출력한다. 만약 덱에 들어있는 정수가 없는 경우에는 -1을 출력한다.
입력
첫째 줄에 주어지는 명령의 수 N (1 ≤ N ≤ 10,000)이 주어진다. 둘째 줄부터 N개의 줄에는 명령이 하나씩 주어진다. 주어지는 정수는 1보다 크거나 같고, 100,000보다 작거나 같다. 문제에 나와있지 않은 명령이 주어지는 경우는 없다.

출력
출력해야하는 명령이 주어질 때마다, 한 줄에 하나씩 출력한다.
 */

import java.util.*;

public class Q_10866_덱 {

    public static void case_type(Deque deque, String answer){
        switch (answer){
            case "pop_front":
                try{
                    System.out.println(deque.removeFirst());     // 값이 없을 경우 에러를 유발 시키기 위해 poll이 아닌 remove 사용
                }catch (Exception e){
                    System.out.println(-1);
                }finally {
                    break;
                }
            case "pop_back":
                try{
                    System.out.println(deque.removeLast());     // 값이 없을 경우 에러를 유발 시키기 위해 poll이 아닌 remove 사용
                }catch (Exception e){
                    System.out.println(-1);
                }finally {
                    break;
                }
            case "size":
                System.out.println(deque.size());
                break;
            case "empty":
                if(deque.isEmpty()){
                    System.out.println(1);
                }else {
                    System.out.println(0);
                }
                break;
            case "front":
                try{
                    System.out.println(deque.getFirst());    // 값이 없을 경우 에러를 유발 시키기 위해 peekFirst이 아닌 getFirst 사용
                }catch (Exception e){
                    System.out.println(-1);
                }finally {
                    break;
                }
            case "back":
                try{
                    System.out.println(deque.getLast());    // 값이 없을 경우 에러를 유발 시키기 위해 peekLast이 아닌 getLast 사용
                }catch (Exception e){
                    System.out.println(-1);
                }finally {
                    break;
                }
            default:
                answer = answer.replace("push_","");
                String[] answer_array = answer.split(" ");
                if(answer_array[0].equals("front")){
                    deque.addFirst(Integer.parseInt(answer_array[1]));
                }else{
                    deque.addLast(Integer.parseInt(answer_array[1]));
                }
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        Deque<Integer> deque = new ArrayDeque<>();

        int N = 0;

        N = scan.nextInt();
        scan.nextLine();

        for(int i=0; i<N; i++){
            case_type(deque, scan.nextLine());
        }
    }
}
