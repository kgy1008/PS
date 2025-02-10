import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {
	static int[] parent;
	static long[] weight;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		StringBuilder sb = new StringBuilder();
		
		 while (true) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());  
			int m = Integer.parseInt(st.nextToken()); 
			
			parent = new int[n+1];
			weight = new long[n+1];
			for (int i=1; i<=n; i++) {
				parent[i] = i;
			}
			
			if (n == 0 && m == 0) {
				break;
			}
			
			while (m --> 0) {
				String[] cmd = br.readLine().split(" ");
				
				if (cmd[0].equals("!")) { // 무게 측정 -> B가 A보다 W그램 더 무겁다.
					int a = Integer.parseInt(cmd[1]);
					int b = Integer.parseInt(cmd[2]);
					int differ = Integer.parseInt(cmd[3]);
					
					union(a, b, differ);
					
				} else {  // 교수님의 질문
					int a = Integer.parseInt(cmd[1]);
					int b = Integer.parseInt(cmd[2]);
		
					if (find(a) != find(b)) {
						sb.append("UNKNOWN\n");
					} else {
						sb.append((weight[b] - weight[a])).append("\n");
					}
				}
			}
		 }
		 
		bw.write(sb.toString());
		bw.flush();
		
		br.close();
		bw.close();
	}
	
	static void union(int a, int b, int w) {
		int p1 = find(a);
		int p2 = find(b);
		
		if (p1 != p2) {
			parent[p2] = p1;
			weight[p2] = weight[a] - weight[b] + w;
		}
	}
	
	static int find(int a) {
		if (parent[a] == a) {
			return a;
		}
		
		int p = find(parent[a]);
		weight[a] += weight[parent[a]];
		return parent[a] = p;
	}
}
