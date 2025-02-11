import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {
	static int[][] board;
	static int n;
	static int m;
	static boolean[][] visited;
	static int[] dx = {0,0,1,-1};
	static int[] dy = {1,-1,0,0};
	static List<Edge> edges = new ArrayList<>();
	static int[] parent;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		board = new int[n][m];
		visited = new boolean[n][m];
		
		for (int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<m; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		

		int count = 0;
		for (int i=0; i<n; i++) {
			for (int j=0; j<m; j++) {
				if (board[i][j] == 1 && !visited[i][j]) {
					dfs(i,j, count+1); // count+1은 섬 번호를 나타냄
					count++;
				}
			}
		}  // 전체 섬의 개수 계산 (총 몇개의 노드가 있는지)
		
		parent = new int[count + 1];
		for (int i=1; i<count+1; i++) {
			parent[i] = i;
		}
	
		for (int i=0; i<n; i++) {
			for (int j=0; j<m; j++) {
				int city = board[i][j];
				if (city != 0) {
					bfs(i,j, city, count);  // 각 섬끼리의 거리 최소 계산 (간선 계산)
				}
			}
		}
		
		edges.sort((o1,o2) -> Integer.compare(o1.cost, o2.cost));
		int answer = solve(count);
		
		bw.write(String.valueOf(answer));
		bw.flush();
		
		br.close();
		bw.close();
	}
	
	static int solve(int v) {
		int cost = 0;
		int edgeCnt = 0;
		
		for (Edge edge : edges) {
			if (find(edge.from) != find(edge.to)) {
				union(edge.from, edge.to);
				edgeCnt++;
				cost += edge.cost;
			}
			
			if (edgeCnt == v - 1) {
				return cost;
			}
		}
		return -1;
	}
	
	static void bfs(int r, int c, int from, int count) {
		Deque<int[]> queue = new ArrayDeque<>();
		
		for (int city=from + 1; city<= count; city++) {
			for (int i=0; i<4; i++) {
				queue.offer(new int[] {r,c, 0});
				int distance = Integer.MAX_VALUE;
				
				while (!queue.isEmpty()) {
					int[] cur = queue.poll(); 
					int nx = cur[0] + dx[i];
					int ny = cur[1] + dy[i];
					
					if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
						continue;
					}
					
					if (board[nx][ny] == city) {
						if (cur[2] >= 2) {
							distance = Math.min(distance, cur[2]);
							break;
						}
					}
					
					if (board[nx][ny] == 0) {
						queue.offer(new int[] {nx, ny, cur[2] + 1});
					}
				}
				
				if (distance != Integer.MAX_VALUE) {
					edges.add(new Edge(from,city,distance));
				}
				queue.clear();
			}
		}
	}
	
	static int find(int v) {
		if (parent[v] == v) {
			return v;
		}
		return parent[v] = find(parent[v]);
	}
	
	static void union(int x, int y) {
		int p1 = find(x);
		int p2 = find(y);
		parent[p2] = p1;
	}
	
	static void dfs(int r, int c, int city) {
		visited[r][c] = true;
		board[r][c] = city;
		
		for (int i=0; i<4; i++) {
			int nx = r + dx[i];
			int ny = c + dy[i];
			
			if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
				continue;
			}
			
			if (!visited[nx][ny] && board[nx][ny] == 1) {
				visited[nx][ny] = true;
				board[nx][ny] = city;
				dfs(nx, ny, city);
			}
		}
	}
	
	static class Edge {
		final int from;
		final int to;
		final int cost;
		
		Edge(int from, int to, int cost) {
			this.from = from;
			this.to = to;
			this.cost = cost;
		}
	}
}
