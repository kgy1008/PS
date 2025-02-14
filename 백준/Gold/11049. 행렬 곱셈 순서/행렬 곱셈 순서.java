import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 사선 DP
public class Main {
    static int N;

    // matrix[n][0]: 행, matrix[n][1]: 열
    static int[][] matrix;

    // dp[i][j]: i번째 행렬 ~ j번쨰 행렬 곱셈 연산의 최소값
    static int[][] dp;

    public static void main(String args[]) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        matrix = new int[N][2];
        dp = new int[N][N];

        StringTokenizer st;
        int r, c;
        for(int n=0; n<N; n++){
            st = new StringTokenizer(br.readLine());
            r = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            matrix[n][0] = r;
            matrix[n][1] = c;
        }

        // init DP table (i<->j gap = 1)
        for(int i=0; i<N-1; i++){
            // (NxM) * (MxK) 연산량: N * M * K
            dp[i][i+1] = matrix[i][0] * matrix[i][1] * matrix[i+1][1];
        }

        // i<->j gap = 2 ~ N-1
        for(int gap=2; gap<N; gap++){
            for(int i=0; i<N-gap; i++){
                int j = i + gap;
                dp[i][j] = Integer.MAX_VALUE;

                for(int k=i; k<j; k++){
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k+1][j] + (matrix[i][0] * matrix[k][1] * matrix[j][1]));
                }
            }

        }

        // 0 ~ (N-1) 번쨰 행렬 곱셈 연산의 최소값
        System.out.println(dp[0][N-1]);
    }
}
