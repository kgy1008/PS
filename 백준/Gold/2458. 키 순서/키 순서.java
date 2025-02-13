import java.io.*;
import java.util.*;

public class Main {
	
	static int[][] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		arr = new int[n+1][n+1];
		
		while (m --> 0) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			arr[a][b] = 1;
			arr[b][a] = -1;
		}
		
		for (int k=1; k<=n; k++) {
			for (int i=1; i<=n; i++) {
				for (int j=1; j<=n; j++) {
					if (arr[i][k] == 1 && arr[k][j] == 1) {
						arr[i][j] = 1;
					}
					
					if (arr[i][k] == -1 && arr[k][j] == -1) {
						arr[i][j] = -1;
					}
				}
			}
		}
		
		int answer = 0;
		for (int i=1; i<=n; i++) {
			int out = 0;
			int in = 0;
			for (int j=1; j<=n; j++) {
				if (arr[i][j] == 1) {
					in++;
				} else if (arr[i][j] == -1) {
					out++;
				}
			}
			
			if (in + out == n-1) {
				answer++;
			}
		}
		
		System.out.println(answer);
	}

}
