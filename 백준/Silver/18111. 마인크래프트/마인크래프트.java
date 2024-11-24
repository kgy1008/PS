import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        int[][] board = new int[n][m];
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                min = Math.min(min, board[i][j]);
                max = Math.max(max, board[i][j]);
            }
        }

        int height = 0;
        int answer = Integer.MAX_VALUE;

        for (int k = max; k >= min; k--) {
            int time = 0;
            int inventory = b;

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (board[i][j] > k) {
                        inventory += (board[i][j] - k);
                        time += (2 * (board[i][j] - k));
                    }

                    if (board[i][j] < k) {
                        inventory -= (k - board[i][j]);
                        time += (k - board[i][j]);
                    }
                }
            }

            if (inventory >= 0 && answer > time) {
                height = k;
                answer = time;
            }
        }
        System.out.println(answer + " " + height);
    }
}
