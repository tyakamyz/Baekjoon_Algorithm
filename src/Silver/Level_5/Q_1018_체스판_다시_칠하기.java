package Silver.Level_5;

/*
[문제]
지민이는 자신의 저택에서 MN개의 단위 정사각형으로 나누어져 있는 M*N 크기의 보드를 찾았다. 어떤 정사각형은 검은색으로 칠해져 있고, 나머지는 흰색으로 칠해져 있다. 지민이는 이 보드를 잘라서 8*8 크기의 체스판으로 만들려고 한다.

체스판은 검은색과 흰색이 번갈아서 칠해져 있어야 한다. 구체적으로, 각 칸이 검은색과 흰색 중 하나로 색칠되어 있고, 변을 공유하는 두 개의 사각형은 다른 색으로 칠해져 있어야 한다. 따라서 이 정의를 따르면 체스판을 색칠하는 경우는 두 가지뿐이다. 하나는 맨 왼쪽 위 칸이 흰색인 경우, 하나는 검은색인 경우이다.

보드가 체스판처럼 칠해져 있다는 보장이 없어서, 지민이는 8*8 크기의 체스판으로 잘라낸 후에 몇 개의 정사각형을 다시 칠해야겠다고 생각했다. 당연히 8*8 크기는 아무데서나 골라도 된다. 지민이가 다시 칠해야 하는 정사각형의 최소 개수를 구하는 프로그램을 작성하시오.

[입력]
첫째 줄에 N과 M이 주어진다. N과 M은 8보다 크거나 같고, 50보다 작거나 같은 자연수이다. 둘째 줄부터 N개의 줄에는 보드의 각 행의 상태가 주어진다. B는 검은색이며, W는 흰색이다.

[출력]
첫째 줄에 지민이가 다시 칠해야 하는 정사각형 개수의 최솟값을 출력한다.
 */

import java.util.Scanner;

public class Q_1018_체스판_다시_칠하기 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        String x_y;
        String[] x_y_split;
        int x=0;
        int y=0;
        int num_1 = 0;
        int num_2 = 0;
        int total = 0;

        String[] w_start = {
            "WBWBWBWB",
            "BWBWBWBW",
            "WBWBWBWB",
            "BWBWBWBW",
            "WBWBWBWB",
            "BWBWBWBW",
            "WBWBWBWB",
            "BWBWBWBW"
        };

        String[] b_start = {
            "BWBWBWBW",
            "WBWBWBWB",
            "BWBWBWBW",
            "WBWBWBWB",
            "BWBWBWBW",
            "WBWBWBWB",
            "BWBWBWBW",
            "WBWBWBWB"
        };

        x_y = scan.nextLine();
        x_y_split = x_y.split(" ");

        x = Integer.parseInt(x_y_split[0]);
        y = Integer.parseInt(x_y_split[1]);

        String[] board = new String[x];

        for(int i=0; i<x; i++){
            board[i] = scan.nextLine();
        }

        String[] w_start_array;
        String[] b_start_array;
        String[] board_array;

        int temp = 0;

        int x_num=0;
        int y_num=0;

        do{
            System.out.println("x_num : " + x_num);
            do{
                System.out.println("y_num : " + y_num);
                for(int i=0; i<8; i++){
                    w_start_array = w_start[i].split("");
                    b_start_array = b_start[i].split("");
                    board_array = board[i+x_num].split("");
                    for(int j=0; j<8; j++){
                        if(!w_start_array[j].equals(board_array[j+y_num])){
                            num_1++;
                        }
                        if(!b_start_array[j].equals(board_array[j+y_num])){
                            num_2++;
                        }

                        if(temp==0){
                            if(num_1<num_2){
                                temp = num_1;
                            }else{
                                temp = num_2;
                            }
                        }else{
                            if(temp>num_1){
                                temp = num_1;
                            }else if(temp>num_2){
                                temp = num_2;
                            }
                        }

                        System.out.println("temp : " + temp);
                        System.out.println("num_1 : " + num_1);
                        System.out.println("num_2 : " + num_2);
                    }
                }
                y_num++;
                num_1 = 0;
                num_2 = 0;

            }while(y_num<=y-8);
            x_num++;
            y_num = 0;

            if(y_num==0){
                total = temp;
            }else{
                if(total>temp){
                    total = temp;
                }
            }

            temp = 0;
        }while(x_num<=x-8);

        System.out.println(total);
    }
}
