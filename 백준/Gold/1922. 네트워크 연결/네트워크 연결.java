import java.io.*;
import java.util.*;

public class Main {
	
	static int[] parent;
	static List<Edge> edges;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		int n = Integer.parseInt(br.readLine()); // 정점의 수
		int m = Integer.parseInt(br.readLine()); // 간선의 수
		
		parent = new int[n+1];
		for (int i=1; i<=n; i++) {
			parent[i] = i;
		}
		
		edges = new ArrayList<>();
		while (m--> 0) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			edges.add(new Edge(a, b, c));
		}
		
		edges.sort((o1,o2) -> Integer.compare(o1.cost, o2.cost));  //비용 기준으로 오름차순 정렬
		
		int answer = solve(n);
		
		bw.write(String.valueOf(answer));
		bw.flush();
		
		br.close();
		bw.close();
	}
	
	static int solve(int v) {
		int selectEdgeCount = 0;
		int answer = 0; 
		
		for (Edge edge : edges) {
			
			if (find(edge.from) == find(edge.to)) { // 같은 집합이라면 -> 건너뛰기
				continue;
			}
			
			answer += edge.cost;
			union(edge);
			selectEdgeCount++;
			
			if (selectEdgeCount == v-1) {
				return answer;
			}
		}
		return -1;
	}
	
	static void union(Edge edge) {
		int p1 = find(edge.from);
		int p2 = find(edge.to); 
	
		parent[p2] = p1; // 같은 집합으로 합치기
	}
	
	static int find(int n) {
		if (parent[n] == n) {
			return n;
		}
		return parent[n] = find(parent[n]);
	}
	
	static class Edge {
		int from;
		int to;
		int cost;
		
		Edge(int from, int to, int cost) {
			this.from = from;
			this.to = to;
			this.cost = cost;
		}
	}

}
