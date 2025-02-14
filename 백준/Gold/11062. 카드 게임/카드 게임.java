import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    
    static int T, N;
    static int[] cards;
    static int[][] dp;

    public static void main(String args[]) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        T = Integer.parseInt(br.readLine());
        
        for(int tc=0; tc<T; tc++){
            N = Integer.parseInt(br.readLine());
            cards = new int[N+1];
            
            dp = new int[N+2][N+1];
            
            st = new StringTokenizer(br.readLine());
            for(int n=1; n<=N; n++){
                cards[n] = Integer.parseInt(st.nextToken());
            }

            boolean isKWturn = (N%2 == 1);

            
            for(int gap=0; gap<N; gap++){
                
                for(int i=1; i<=N-gap; i++){
                    int left = i;
                    int right = i+gap;
                    
                    if(isKWturn){
                        dp[left][right] = Math.max(
                            cards[left] + dp[left+1][right],
                            dp[left][right-1] + cards[right]
                        );
                    }else{
                        dp[left][right] = Math.min(
                            dp[left+1][right],
                            dp[left][right-1]
                        );
                    }
                }
                

                isKWturn = !isKWturn;
            }
            sb.append(dp[1][N]);
            sb.append("\n");
        }

        br.close();

        bw.write(sb.toString());
        bw.flush();
        bw.close();

    }

}