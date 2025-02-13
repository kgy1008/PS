import java.util.*;
import java.io.*;

public class Main {
	static final int INF = 100_000 * 100 + 1;
	static int[][] dist;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		int n = Integer.parseInt(br.readLine());  // 도시의 개수
		int m = Integer.parseInt(br.readLine());  // 버스의 개수
		// 거리 초기화
		dist = new int[n+1][n+1];
		for (int i=1; i<=n; i++) {
			for (int j=1; j<=n; j++) {
				if (i == j) {
					dist[i][j] = 0;
				} else {
					dist[i][j] = INF;
				}
			}
		}
		
		while (m--> 0) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			dist[a][b] = Math.min(dist[a][b], c);
		}
		
		for (int k=1; k<=n; k++) {  // 경유지
			for (int i=1; i<=n; i++) {
				for (int j=1; j<=n; j++) {
					if (dist[i][k] == INF || dist[k][j] == INF) {
						continue;
					}
					dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i=1; i<=n; i++) {
			for (int j=1; j<=n; j++) {
				if (dist[i][j] >= INF) {
					sb.append(0).append(" ");
				} else {
					sb.append(dist[i][j]).append(" ");
				}
			}
			sb.append("\n");
		}
		
		bw.write(sb.toString());
		bw.flush();
	}

}
