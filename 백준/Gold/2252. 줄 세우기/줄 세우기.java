import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {
	static List<Integer>[] list;
	static int[] degree;
	static final StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());  // 전체 학생 수
		int m = Integer.parseInt(st.nextToken());  // 키를 비교한 횟수
		
		 list = new ArrayList[n+1];
		 degree = new int[n+1];
		
		for (int i=1; i<=n; i++) {
			list[i] = new ArrayList<>();
		}
		
		while (m --> 0) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			list[a].add(b);
			degree[b]++;
		}
		
		solve(n);

		bw.write(sb.toString());
		bw.flush();
		
		br.close();
		bw.close();
	}
	
	static void solve(int n) {
		Deque<Integer> queue = new ArrayDeque<>();
		
		for (int i=1; i<=n; i++) {
			if (degree[i] == 0) {
				queue.offer(i);
			}
		}
		
		while (!queue.isEmpty()) {
			int cur = queue.poll();
			sb.append(cur).append(" ");
			
			for (int next : list[cur]) {
				degree[next]--;
				
				if (degree[next] == 0) {
					queue.offer(next);
				}
			}
		}
	}
}
