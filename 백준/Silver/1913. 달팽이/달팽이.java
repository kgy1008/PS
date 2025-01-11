import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int target = Integer.parseInt(br.readLine());

        int[][] map = new int[n][n];
        int value = n * n;
        int x = 0, y = 0;
        int time = 0;
        int limit = n;
        while (value > 0) {
            x = time;
            for (int i = y; i < limit; i++) {
                map[i][x] = value--;
            }

            y = limit - 1;
            for (int i = x + 1; i < limit; i++) {
                map[y][i] = value--;
            }

            x = limit - 1;
            for (int i = y - 1; i >= time; i--) {
                map[i][x] = value--;
            }

            y = time;
            for (int i = x - 1; i > time; i--) {
                map[y][i] = value--;
            }
            time++;
            limit--;
            y = time;
        }

        int answerX = 0, answerY = 0;
        for (int i = 0; i < n; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < n; j++) {
                sb.append(map[i][j]).append(" ");
                if (map[i][j] == target) {
                    answerX = i + 1;
                    answerY = j + 1;
                }
            }
            System.out.println(sb.toString());
        }
        System.out.printf("%d %d", answerX, answerY);
    }
}
