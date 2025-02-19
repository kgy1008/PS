import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // i에서 j로 이동하는데 필요한 비용 배열에 저장
        int[][] map = {{0, 2, 2, 2, 2}, {2, 1, 3, 4, 3}, {2, 3, 1, 3, 4}, {2, 4, 3, 1, 3}, {2, 3, 4, 3, 1}};

        int[][][] dp = new int[100001][5][5];  // (T,L,R)
        // 배열 초기화
        for (int k = 0; k < 100001; k++) {
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    dp[k][i][j] = 100001 * 4;  // 충분히 큰 값으로 초기화
                }
            }
        }
        dp[0][0][0] = 0; // 첫 시작점은 힘 0으로 초기화

        int turn = 1;

        StringTokenizer st = new StringTokenizer(br.readLine());
        while (true) {
            int n = Integer.parseInt(st.nextToken());

            if (n == 0) {  // 0이 들어오면 종료
                break;
            }

            // 오른발이 움직임
            for (int i = 0; i < 5; i++) {
                if (n == i) {  // 이미 움직이고자 하는 칸에 존재
                    continue;
                }
                for (int j = 0; j < 5; j++) {
                    dp[turn][i][n] = Math.min(dp[turn - 1][i][j] + map[j][n], dp[turn][i][n]);
                }
            }

            // 왼발이 움직임
            for (int j = 0; j < 5; j++) {
                for (int i = 0; i < 5; i++) {
                    if (i == j) {
                        continue;
                    }
                    dp[turn][n][j] = Math.min(dp[turn - 1][i][j] + map[i][n], dp[turn][n][j]);
                }
            }

            turn++;
        }

        int min = Integer.MAX_VALUE;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                min = Math.min(min, dp[turn - 1][i][j]);
            }
        }
        System.out.println(min);
    }
}
