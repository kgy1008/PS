import java.io.*;
import java.util.StringTokenizer;

public class Main {
	static int leafPointer = 1;
	static long[] tree;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int q = Integer.parseInt(st.nextToken());
		
		while (leafPointer < n) {
			leafPointer <<= 1;
		}
		tree = new long[leafPointer * 2];
		
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<n; i++) {
			tree[leafPointer + i] = Long.parseLong(st.nextToken());
		}  // 리프 노드 초기화
		
		for (int i = leafPointer -1; i>0; i--) {
			tree[i] = tree[i*2] + tree[i*2+1];
		}
		
		StringBuilder sb = new StringBuilder();
		while (q --> 0) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			long b = Long.parseLong(st.nextToken());
			
			long answer = query(x,y);
			sb.append(answer).append("\n");
			update(a,b);
		}
		
		bw.write(sb.toString());
		bw.flush();
		
		bw.close();
		br.close();
	}
	
	static long query(int start, int end) {
		if (start > end) {  // x가 y보다 클 경우 swap
			int temp = start;
			start = end;
			end = temp;
		}
		
		int left = leafPointer + start - 1;
		int right = leafPointer + end - 1;
		long sum = 0;
		
		while (left <= right) {
			if (left % 2 == 1) {
				sum += tree[left++];
			}
			
			if (right % 2 == 0) {
				sum += tree[right--];
			}
			left /= 2;
			right /= 2;
		}
		return sum;
	}
	
	static void update(int a, long b) {
		int target = leafPointer + a - 1;
		tree[target] = b;
		target /= 2;
		
		while (target >= 1) {
			tree[target] = tree[target * 2] + tree[target * 2 + 1];
			target /= 2;
		}
	}
 
}
