import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static final int INF = 16_000_000;
    static int[][] W, dp;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        W = new int[N+1][N+1];
        dp = new int[N+1][(1<<N)];

        for(int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=N; j++){
                W[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i=1; i<=N; i++){
            Arrays.fill(dp[i], -1);
        }

        System.out.println(dfs(1, 1));
        br.close();
    }

    static int dfs(int current, int visited){
        if(visited == (1<<N)-1){
            if(W[current][1] == 0) return INF;
            else return W[current][1];
        }

        if(dp[current][visited] != -1){
            return dp[current][visited];
        }

        dp[current][visited] = INF;

        for(int next=1; next<=N; next++){
            int nextIndex = 1<<(next-1);

            if((visited & nextIndex) == 0 && W[current][next] != 0){
                dp[current][visited] = 
	        		Math.min(
	    				dp[current][visited],
	    				W[current][next] + dfs(next, visited|nextIndex)
					);
            }
        }

        return dp[current][visited];
    }
}
