import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        int h = Integer.parseInt(st.nextToken());

        int[][][] tomato = new int[h][n][m];
        Deque<int[]> queue = new ArrayDeque<>();

        for (int z = 0; z < h; z++) {
            for (int x = 0; x < n; x++) {
                st = new StringTokenizer(br.readLine());
                for (int y = 0; y < m; y++) {
                    tomato[z][x][y] = Integer.parseInt(st.nextToken());
                    if (tomato[z][x][y] == 1) {
                        queue.add(new int[]{z, x, y});
                    }
                }
            }
        }

        int[] dx = {0, 0, 1, -1, 0, 0};
        int[] dy = {1, -1, 0, 0, 0, 0};
        int[] dz = {0, 0, 0, 0, 1, -1};

        while (!queue.isEmpty()) {
            int[] current = queue.pollFirst();
            int z = current[0];
            int x = current[1];
            int y = current[2];

            for (int i = 0; i < 6; i++) {
                int nz = z + dz[i];
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nz < 0 || nz >= h || nx < 0 || nx >= n || ny < 0 || ny >= m) {
                    continue;
                }

                if (tomato[nz][nx][ny] == 0) {
                    tomato[nz][nx][ny] = tomato[z][x][y] + 1;
                    queue.add(new int[]{nz, nx, ny});
                }
            }
        }

        int day = 0;
        for (int z = 0; z < h; z++) {
            for (int x = 0; x < n; x++) {
                for (int y = 0; y < m; y++) {
                    if (tomato[z][x][y] == 0) {
                        System.out.println(-1);
                        return;
                    }
                    day = Math.max(day, tomato[z][x][y]);
                }
            }
        }

        System.out.println(day - 1);
    }
}
