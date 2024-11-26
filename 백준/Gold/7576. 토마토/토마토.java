import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 6
        int m = Integer.parseInt(st.nextToken()); // 4
        int[][] tomato = new int[m][n];

        Deque<int[]> deque = new ArrayDeque<>();
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                tomato[i][j] = Integer.parseInt(st.nextToken());

                if (tomato[i][j] == 1) {
                    deque.offer(new int[]{i, j});
                }
            }
        }

        int[] nx = {1, -1, 0, 0};
        int[] ny = {0, 0, 1, -1};

        while (!deque.isEmpty()) {
            int[] current = deque.poll();
            int x = current[0];
            int y = current[1];

            for (int i = 0; i < 4; i++) {
                int dx = x + nx[i];
                int dy = y + ny[i];

                if (dx < 0 || dy < 0 || dx >= m || dy >= n || tomato[dx][dy] == -1 || tomato[dx][dy] > 0) {
                    continue;
                }

                if (tomato[dx][dy] == 0) {
                    tomato[dx][dy] = tomato[x][y] + 1;
                    deque.offer(new int[]{dx, dy});
                }
            }
        }

        int answer = -1;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (tomato[i][j] == 0) {
                    System.out.println(-1);
                    return;
                }
                answer = Math.max(answer, tomato[i][j]);
            }
        }
        System.out.println(answer - 1);
    }
}
