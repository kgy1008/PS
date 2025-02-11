import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {
	static List<Integer>[] adj;
	static int[] degree;
	static int[] weight;
	static int answer = 0;
	static int[] time;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int n = Integer.parseInt(br.readLine()); // 건물의 종류 수
		 adj = new ArrayList[n+1];
		 degree = new int[n+1];
		 weight = new int[n+1];
		 time = new int[n+1];
		
		for (int i=1; i<=n; i++) {
			adj[i] = new ArrayList<>();
		}
		
		for (int i=1; i<=n; i++) {
			String[] element = br.readLine().split(" ");
			for (int j=0; j<element.length; j++) {
				int target = Integer.parseInt(element[j]);
				if (j==0) {
					weight[i] = target; // 건설하는데 걸리는 시간 저장
				} else {
					if (target != -1) {
						adj[target].add(i);
						degree[i]++;  // 차수 증가
					}
				}
			}
		}
		
		solve(n);

		StringBuilder sb = new StringBuilder();
		for (int t=1; t<=n; t++) {
			sb.append(time[t]).append("\n");
		}
		
		bw.write(sb.toString());
		bw.flush();
		
		br.close();
		bw.close();
	}
	
	static void solve(int n) {
		Deque<Integer> todo = new ArrayDeque<>();
		
		for (int i=1; i<=n; i++) {
			if (degree[i] == 0) {  // 가장 우선순위로 작업해야 하는 건물 큐에 집어넣기
				todo.offer(i);
			}
		}
		
		while(!todo.isEmpty()) {
			List<Integer> list = new ArrayList<>(todo);
			
			int min = list.stream().mapToInt(i -> weight[i]).min().getAsInt();
			
			for (int i : list) {
				if (weight[i] == min) {
					todo.remove(i);
					time[i] = answer + min;
					
					for (int next : adj[i]) {
						degree[next]--;
						if (degree[next] == 0) {
							todo.offer(next);
						}
					}
				} 
				else {
					weight[i] -= min;
				}
			}
			answer += min;
		}
	}
}
