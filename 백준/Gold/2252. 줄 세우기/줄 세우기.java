import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {
	static List<Integer>[] list;
	static boolean[] visited;
	static Deque<Integer> answer = new ArrayDeque<>();
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());  // 전체 학생 수
		int m = Integer.parseInt(st.nextToken());  // 키를 비교한 횟수
		
		 list = new ArrayList[n+1];
		 visited = new boolean[n+1];
		
		for (int i=1; i<=n; i++) {
			list[i] = new ArrayList<>();
		}
		
		while (m --> 0) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			list[a].add(b);
		}
		
		for (int i=1; i<=n; i++) {
			if (!visited[i]) {
				dfs(i);
			}
		}
		
		StringBuilder sb = new StringBuilder();
		
		while(!answer.isEmpty()) {
			sb.append(answer.pop()).append(" ");
		}
		
		bw.write(sb.toString());
		bw.flush();
		
		br.close();
		bw.close();
	}
	
	static void dfs(int n) {
		visited[n] = true;

		for (int next : list[n]) {
			if (!visited[next]) {
				dfs(next);
			}
		}
		
		answer.push(n);
	}
}
