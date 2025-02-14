import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, P;
    static int[][] cost;
    static int[] dp;
    static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        N = Integer.parseInt(br.readLine());

        cost = new int[N][N];
        StringTokenizer st;

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                cost[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        String stateStr = br.readLine();

        int initState = 0;
        int initActiveCnt = 0;

        for (int i=0; i<N; i++) {
            if (stateStr.charAt(i) == 'Y') {
                initState = initState | (1 << i);
                initActiveCnt++;
            }
        }

        P = Integer.parseInt(br.readLine());

        if(initActiveCnt >= P){
            System.out.println("0");
            return;
        }

        dp = new int[(1<<N)];
        Arrays.fill(dp, INF);

        int ans = INF;

        dp[initState] = 0;

        for(int state=0; state<(1<<N); state++){
            if(dp[state] == INF) continue;

            if(Integer.bitCount(state) >= P){
                ans = Math.min(ans, dp[state]);
                continue;
            }

            for(int i=0; i<N; i++){
                if((state & (1<<i)) != 0){
                    for(int j=0; j<N; j++){
                        int nextState = state | (1<<j);
                        dp[nextState] = Math.min(dp[nextState], dp[state] + cost[i][j]);
                    }
                }
            }
        }
        System.out.println(ans == INF ? -1 : ans);
    }
}
