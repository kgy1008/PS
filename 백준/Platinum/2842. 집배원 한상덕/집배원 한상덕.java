import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	
	static int[][] move = {{1,0},{1,1},{0,1},{-1,1},{-1,0},{-1,-1},{0,-1},{1,-1}};
	static Node[][] map;
	static Node POST;
	static int targetCnt = 0;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int n = Integer.parseInt(br.readLine());
		map = new Node[n][n];
		for (int r=0; r<n; r++ ) {
			String line = br.readLine();
			for (int c=0; c<n; c++) {
				map[r][c] = new Node(r,c,line.charAt(c));
				if (map[r][c].point == 'P') {
					POST = map[r][c];
				} 
				if (map[r][c].point == 'K') {
					targetCnt++;
				}
			}
		}
		
		Set<Integer> sortedHeight = new TreeSet<>();
		for (int r=0; r<n; r++ ) {
			st = new StringTokenizer(br.readLine());
			for (int c=0; c<n; c++) {
				int h = Integer.parseInt(st.nextToken());
				sortedHeight.add(h);
				map[r][c].height = h;
			}
		}
		
		List<Integer> heights = new ArrayList<>(sortedHeight);
		int start = 0, end = 0;
		int answer = Integer.MAX_VALUE;
		while (start <= end && end < heights.size()) {
			if (POST.height >= heights.get(start) && POST.height <= heights.get(end)) {
				boolean[][] visited = new boolean[n][n];
				int complete = dfs(POST, heights.get(start), heights.get(end), visited, n); // 방문한 K의 개수
				if (complete == targetCnt) { // 다 찾음
					answer = Math.min(answer, heights.get(end) - heights.get(start));
					start++; // 최솟값을 최대한 크게 만들기
				} else {  // 다 못찾음 -> 탐색 범위 늘리기
					end++;
				}
			} else { // 시작 지점 미포함일 경우 -> 탐색 범위 늘리기
				end++; 
			}
		}
		System.out.println(answer);
	}
	
	static int dfs(Node node, int min, int max, boolean[][] visited, int n) {
		visited[node.x][node.y] = true;
		int cnt = 0;
		
		for (int i=0; i < 8; i++) {
			int nx = node.x + move[i][0];
			int ny = node.y + move[i][1];
			
			if (nx >= 0 && nx < n && ny >= 0 && ny < n) {
				if (!visited[nx][ny] && map[nx][ny].height >= min && map[nx][ny].height <= max) {
					if (map[nx][ny].point == 'K') {
						cnt++;
					}
					visited[nx][ny] = true;
					cnt += dfs(map[nx][ny], min, max, visited, n);
				}
			}
		}
		return cnt;
	}
	
	static class Node {
		final int x;
		final int y;
		int height;
		char point;
		
		Node(int x, int y, char point) {
			this.x = x;
			this.y = y;
			this.point = point;
		}
	}
}
