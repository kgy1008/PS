import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    // 결과를 100000으로 나눈 나머지로 출력해야 하므로 MOD 값 정의
    static final int MOD = 100000;

    // DP 배열: dp[x][y][direction][is_turned]
    // x: 남북 방향 도로 (W), y: 동서 방향 도로 (H)
    // direction: 현재 (x, y)에 도착한 방향 (0: 동쪽(x축), 1: 북쪽(y축))
    // is_turned: 바로 직전 교차로에서 방향을 '바꿨는지' 여부 (0: 안 바꿈, 1: 바꿈)
    static int[][][][] dp;
    static int W, H;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        // 도로 번호가 1부터 시작하므로 크기는 W+1, H+1
        dp = new int[W + 1][H + 1][2][2];

        // 1. 초기값 설정
        // (1, 1)에서 시작하여 동쪽 또는 북쪽으로 이동

        // (1, j)까지 가는 경로 (계속 북쪽으로만 이동)
        for (int j = 2; j <= H; j++) {
            // 북쪽(1)에서 왔고, 당연히 꺾지 않음(0)
            dp[1][j][1][0] = 1;
        }

        // (i, 1)까지 가는 경로 (계속 동쪽으로만 이동)
        for (int i = 2; i <= W; i++) {
            // 동쪽(0)에서 왔고, 당연히 꺾지 않음(0)
            dp[i][1][0][0] = 1;
        }

        // 2. DP 테이블 채우기
        for (int i = 2; i <= W; i++) {
            for (int j = 2; j <= H; j++) {

                // (i, j)에 동쪽(0)에서 도착하는 경우 (직전 좌표: (i-1, j))

                // A. 직전 교차로에서 '안 꺾고(0)' 도착
                // (i-1, j)에 동쪽(0)으로 도착한 모든 경우의 수 합
                // 동쪽으로 계속 이동했으므로 방향을 안 바꾼 경우
                dp[i][j][0][0] = (dp[i - 1][j][0][0] + dp[i - 1][j][0][1]) % MOD;

                // B. 직전 교차로에서 '꺾고(1)' 도착
                // (i-1, j)에 북쪽(1)으로 도착했고, 그곳에서 동쪽으로 방향을 '바꾼' 경우
                // "방향을 바꾼 후, 1 블록만 이동한 후 다시 방향을 바꿀 수 없다"
                // -> (i-1, j)에 북쪽으로 도착했을 때는 이전에 꺾지 않았어야만 여기서 동쪽으로 꺾을 수 있음.
                dp[i][j][0][1] = dp[i - 1][j][1][0];

                // (i, j)에 북쪽(1)에서 도착하는 경우 (직전 좌표: (i, j-1))

                // C. 직전 교차로에서 '안 꺾고(0)' 도착
                // (i, j-1)에 북쪽(1)으로 도착한 모든 경우의 수 합
                // 북쪽으로 계속 이동했으므로 방향을 안 바꾼 경우
                dp[i][j][1][0] = (dp[i][j - 1][1][0] + dp[i][j - 1][1][1]) % MOD;

                // D. 직전 교차로에서 '꺾고(1)' 도착
                // (i, j-1)에 동쪽(0)으로 도착했고, 그곳에서 북쪽으로 방향을 '바꾼' 경우
                // -> (i, j-1)에 동쪽으로 도착했을 때는 이전에 꺾지 않았어야만 여기서 북쪽으로 꺾을 수 있음.
                dp[i][j][1][1] = dp[i][j - 1][0][0];
            }
        }

        // 3. 최종 결과: (W, H)에 모든 경로를 합산
        int result = 0;

        // 동쪽에서 도착 (꺾음 O/X) + 북쪽에서 도착 (꺾음 O/X)
        result = (dp[W][H][0][0] + dp[W][H][0][1]) % MOD;
        result = (result + dp[W][H][1][0]) % MOD;
        result = (result + dp[W][H][1][1]) % MOD;

        System.out.println(result);
    }
}
