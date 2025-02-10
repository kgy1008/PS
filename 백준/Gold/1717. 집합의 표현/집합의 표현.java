import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {
	
	static int[] parent;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());  // 입력으로 주어지는 연산의 개수
		
		parent = new int[n+1];
		for (int i=0; i<=n; i++) {
			parent[i] = i; // 부모 초기화
		}
		
		StringBuilder sb = new StringBuilder();
		while (m --> 0) {
			st = new StringTokenizer(br.readLine());
			int cmd = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			if (cmd == 0) { // 합하는 연산 -> union
				union(a,b);	
			} else { // 부모를 찾는 연산 -> find
				int p1 = find(a);
				int p2 = find(b);
				if (p1 == p2) {
					sb.append("YES\n");
				} else {
					sb.append("NO\n");
				}
			}
		}
		
		bw.write(sb.toString());
		bw.flush();
		
		br.close();
		bw.close();
	}
	
	static void union(int a, int b) {
		int p1 = find(a);
		int p2 = find(b);
		
		if (p1 < p2) {
			parent[p2] = p1;
		} else {
			parent[p1] = p2;
		}
	}
	
	static int find(int target) {
		if (target == parent[target]) {
			return target;
		}
		return parent[target] = find(parent[target]);
	}
}
