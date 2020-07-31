package Silver.Level_1;

/*
문제
언제나 최고만을 지향하는 굴지의 대기업 진영 주식회사가 신규 사원 채용을 실시한다. 인재 선발 시험은 1차 서류심사와 2차 면접시험으로 이루어진다. 최고만을 지향한다는 기업의 이념에 따라 그들은 최고의 인재들만을 사원으로 선발하고 싶어 한다.

그래서 진영 주식회사는, 다른 모든 지원자와 비교했을 때 서류심사 성적과 면접시험 성적 중 적어도 하나가 다른 지원자보다 떨어지지 않는 자만 선발한다는 원칙을 세웠다. 즉, 어떤 지원자 A의 성적이 다른 어떤 지원자 B의 성적에 비해 서류 심사 결과와 면접 성적이 모두 떨어진다면 A는 결코 선발되지 않는다.

이러한 조건을 만족시키면서, 진영 주식회사가 이번 신규 사원 채용에서 선발할 수 있는 신입사원의 최대 인원수를 구하는 프로그램을 작성하시오.

입력
첫째 줄에는 테스트 케이스의 개수 T(1 ≤ T ≤ 20)가 주어진다. 각 테스트 케이스의 첫째 줄에 지원자의 숫자 N(1 ≤ N ≤ 100,000)이 주어진다. 둘째 줄부터 N개 줄에는 각각의 지원자의 서류심사 성적, 면접 성적의 순위가 공백을 사이에 두고 한 줄에 주어진다. 두 성적 순위는 모두 1위부터 N위까지 동석차 없이 결정된다고 가정한다.

출력
각 테스트 케이스에 대해서 진영 주식회사가 선발할 수 있는 신입사원의 최대 인원수를 한 줄에 하나씩 출력한다.
 */

import java.util.Scanner;
import java.util.Stack;

public class Q_1946_신입사원_stack {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		String[] A = new String[2];
		String[] B = new String[2];
		
		int result = 0;
		
		int T = scan.nextInt();
		scan.nextLine();
		
		for(int i=0; i<T; i++) {
			Stack<String> stack = new Stack<>();
			Stack<String> temp_stack = new Stack<>();
			
			int N = scan.nextInt();
			scan.nextLine();
			
			// 지원자 수
			result = N;
			
			// 지원자 수 만큼 스택에 값을 넣어줌
			for(int j=0; j<N; j++) {
				stack.push(scan.nextLine());
			}
			
			// 첫번째 사원부터 차례로 비교
			while(!stack.empty()) {
				A = stack.pop().split(" ");	// A사원 
				// 모든 사원들과 비교 종료될때까지
				while(!stack.empty()) {
					B = stack.peek().split(" ");	// B사원 
					temp_stack.push(stack.pop());	// 종료 후 다시 다음사람 처음부터 비교하기 위해 temp_stack에 담아둠 
					
					if(Integer.parseInt(A[0]) < Integer.parseInt(B[0]) && Integer.parseInt(A[1]) < Integer.parseInt(B[1])) {
						temp_stack.pop();	// 탈락한 사원은 다음 비교대상에 넣을 필요가 없으므로 제외
						result--;			// 탈락한 사원 최종 합격수에서 제외
					}else if(Integer.parseInt(A[0]) > Integer.parseInt(B[0]) && Integer.parseInt(A[1]) > Integer.parseInt(B[1])){
						result--;			// 탈락한 사원 최종 합격수에서 제외
						break;
					}
				}
				
				//A[0] = B[0];	// 기준이 되는 사원 변경
				//A[1] = B[1];
				
				while(!temp_stack.empty()) {
					stack.push(temp_stack.pop());
				}
			}
			
			sb.append(result + "\n");
		}
		
		System.out.println(sb);
	}
}
