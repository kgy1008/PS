import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        StringBuilder sb = new StringBuilder();
        for (int t = 0; t < 3; t++) {
            int n = Integer.parseInt(br.readLine()); // 동전의 개수

            Coin[] coins = new Coin[n];
            int sum = 0;
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                int v = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                coins[i] = new Coin(v, c);

                sum += v * c;
            }

            if (sum % 2 != 0) { // 합이 홀수면 불가능
                sb.append(0).append("\n");
                continue;
            }

            int target = sum / 2;
            boolean[] dp = new boolean[target + 1];
            dp[0] = true;
            for (int i = 0; i < n; i++) {
                Coin coin = coins[i];
                int v = coin.value;
                int c = coin.count;

                for (int j = target; j >= v; j--) {
                    for (int k = 1; k <= c; k++) {
                        if (j - v * k < 0) {
                            break;
                        }
                        if (dp[j - v * k]) {
                            dp[j] = true;
                            break;
                        }
                    }
                }
            }

            if (dp[target]) {
                sb.append(1).append("\n");
            } else {
                sb.append(0).append("\n");
            }
        }
        br.close();

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    static class Coin {
        int value;
        int count;

        Coin(int value, int count) {
            this.value = value;
            this.count = count;
        }
    }
}
