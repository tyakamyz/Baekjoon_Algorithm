package Silver.Level_1;

/*
문제
<그림 1>과 같이 정사각형 모양의 지도가 있다. 1은 집이 있는 곳을, 0은 집이 없는 곳을 나타낸다. 철수는 이 지도를 가지고 연결된 집들의 모임인 단지를 정의하고, 단지에 번호를 붙이려 한다. 여기서 연결되었다는 것은 어떤 집이 좌우, 혹은 아래위로 다른 집이 있는 경우를 말한다. 대각선상에 집이 있는 경우는 연결된 것이 아니다. <그림 2>는 <그림 1>을 단지별로 번호를 붙인 것이다. 지도를 입력하여 단지수를 출력하고, 각 단지에 속하는 집의 수를 오름차순으로 정렬하여 출력하는 프로그램을 작성하시오.



입력
첫 번째 줄에는 지도의 크기 N(정사각형이므로 가로와 세로의 크기는 같으며 5≤N≤25)이 입력되고, 그 다음 N줄에는 각각 N개의 자료(0혹은 1)가 입력된다.

출력
첫 번째 줄에는 총 단지수를 출력하시오. 그리고 각 단지내 집의 수를 오름차순으로 정렬하여 한 줄에 하나씩 출력하시오.
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Q_2667_단지번호붙이기 {
	
	static int N;
	static int cnt;
	static int[][] board;
	static int board_x[] = {-1,1,0,0};
	static int board_y[] = {0,0,-1,1};
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
	    
	    ArrayList<Integer> result = new ArrayList<Integer>();
		
        N = scan.nextInt();
        
        board = new int[N][N];	// 단지를 그릴 지도
        
        // 입력받은 N줄 만큼 입력 받음
        for(int i=0; i<N; i++) 
        {
            String input = scan.next();
            for(int j=0;j<N;j++)
            {
            	board[i][j] = input.charAt(j)-'0';	// 정수로 치환
            }
        }
        
        // 1 값을 찾아서 bfs 탐색 시작
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++)        
                if(board[i][j] == 1)    
                {
                    cnt = 1;	// 1 값일 경우에만 if문을 타기때문에 이미 1개가 있으므로 시작값은 1
                    result.add(dfs(i, j));	// dfs 탐색 결과를 넣어줌
                }
        }
        
        Collections.sort(result);	// 오름차순 정렬
        
        
        // 출력
        System.out.println(result.size());
        for(int out : result) {
            System.out.println(out);
        }
        
	}
	
	static int dfs(int x, int y) 
    {        
		board[x][y] = 0;	// 중복 탐색 안하도록 0으로 변경
		
        for(int i=0;i<4;i++)
        {
        int next_x = x + board_x[i];
        int next_y = y + board_y[i];
        
        // 상하좌우 탐색
        if(next_x>=0 && next_y>=0 && next_x<N && next_y<N) {
                if(board[next_x][next_y] == 1)
                {
                	dfs(next_x, next_y);
                    cnt++;
                }
            }
        }
        
        return cnt;
    }
}
