import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken()); // 수의 개수
		int m = Integer.parseInt(st.nextToken()); // 변경 횟수
		int k = Integer.parseInt(st.nextToken()); // 구간의 합을 구하는 횟수
		
		int leafPointer = 1;
		while (n > leafPointer) {
			leafPointer <<= 1;
		}
		
		
		long[] tree = new long[leafPointer * 2];
		for (int i=leafPointer; i<n + leafPointer; i++) {  // 리프 노드 채우기
			long num = Long.parseLong(br.readLine());
			tree[i] = num;
		}
		
		for (int i=leafPointer-1; i>0; i--) {  // 구간 합 계산
			tree[i] = tree[i*2] + tree[i*2+1];
		}
	
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<m+k; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			long c = Long.parseLong(st.nextToken());
			
			if (a == 1) {  // 숫자 변경
				int targetIdx = leafPointer + b - 1;
				tree[targetIdx] = c;
				
				while (targetIdx != 1) {  // 구간 합 업데이트
					targetIdx /= 2;
					tree[targetIdx] = tree[targetIdx*2] + tree[targetIdx*2+1];
				}
				
			} else {  // 구간 합 계산
				long sum = 0L;
				int left = leafPointer + b - 1;
				int right = leafPointer + (int) c - 1;
				
				while (left <= right) {
					if (left % 2 == 1) { // 구간 시작이 오른쪽 자식일 때
						sum += tree[left];
						left++;
					} 
					if (right % 2 == 0) { // 구간 끝이 왼쪽 자식일 때
						sum += tree[right];
						right--;
					}
					left /= 2;
					right /= 2;
				}
				
				sb.append(sum).append("\n");
			}
		}
		
		bw.write(sb.toString());
		bw.flush();
		
		br.close();
		bw.close();
	}
}
