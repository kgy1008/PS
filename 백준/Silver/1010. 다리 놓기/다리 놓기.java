import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int[][] memo;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            init(N, M);

            int answer = solve(N, M);
            sb.append(answer).append("\n");
        }
        br.close();

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static void init(int N, int M) {
        memo = new int[N + 1][M + 1];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }
    }

    static int solve(int N, int M) {
        if (N == 0 || N == M) {
            return 1;
        }
        if (memo[N][M] != -1) {
            return memo[N][M];
        }
        return memo[N][M] = solve(N - 1, M - 1) + solve(N, M - 1);
    }
}
