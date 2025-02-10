import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {
	
	static final StringBuilder sb = new StringBuilder();
	static long[][] dp;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		long k = Long.parseLong(st.nextToken());
		
		dp = new long[n+m+1][n+m+1];
		for (int i=0; i<n+m+1; i++) {
			Arrays.fill(dp[i],  -1);
		}
        
		long total = getTotalCount(n+m, m);
		
		if (total < k) {
			System.out.println(-1);
			return;
		}
		
		solve(m, n, k);
		System.out.println(sb.toString());
	}
	
	static void solve(int m, int n, long k) {
		if (n == 0) {
			for (int j=0; j< m; j++) {
				sb.append('z');
			}
			return;
		}
		
		if (m == 0) {
			for (int j=0; j< n; j++) {
				sb.append('a');
			}
			return;
		}

		long temp = getTotalCount(n+m-1, n - 1);
		
		if (temp >= k) {
			sb.append('a');
			solve(m, n-1,  k);
		} else {
			sb.append('z');
			solve(m-1, n,  k - temp);
		}
	}

	static long getTotalCount(int entire, int pick) { // 전체 개수 구하기 -> (n+m) C m
		if (pick == 0 || entire == pick) {
			return 1L;
		}
		
		if (dp[entire][pick] > 1000000000) {
			dp[entire][pick] = 1000000001;
		}
		
		if (dp[entire][pick] != -1) {
			return dp[entire][pick];
		}
		
		return dp[entire][pick] = getTotalCount(entire-1, pick-1) + getTotalCount(entire-1, pick);
				
	}
}
