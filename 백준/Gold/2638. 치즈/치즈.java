import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static int n, m;
    private static int[][] board;
    private static boolean[][] visited;
    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int time = 0;

        while (true) {
            markOuterAir();

            // 녹을 치즈 찾기
            boolean[][] toMelt = new boolean[n][m];
            boolean hasCheese = false;

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (board[i][j] == 1) { // 치즈 존재
                        hasCheese = true;
                        int airCount = 0;

                        // 4방향 체크
                        for (int k = 0; k < 4; k++) {
                            int nx = i + dx[k];
                            int ny = j + dy[k];

                            if (nx >= 0 && nx < n && ny >= 0 && ny < m && board[nx][ny] == 2) {
                                airCount++;
                            }
                        }

                        // 2변 이상이 외부 공기와 접촉하면 녹음
                        if (airCount >= 2) {
                            toMelt[i][j] = true;
                        }
                    }
                }
            }

            // 치즈가 없으면 종료
            if (!hasCheese) {
                break;
            }

            // 치즈 녹이기
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (toMelt[i][j]) {
                        board[i][j] = 0;
                    }
                }
            }

            // 외부 공기 표시 초기화 (2 -> 0)
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (board[i][j] == 2) {
                        board[i][j] = 0;
                    }
                }
            }

            time++;
        }

        System.out.println(time);
    }

    // BFS로 외부 공기를 2로 마킹
    private static void markOuterAir() {
        visited = new boolean[n][m];
        Queue<Point> queue = new LinkedList<>();

        if (board[0][0] == 0) { // 가장 자리는 항상 외부 공기
            queue.offer(new Point(0, 0));
            visited[0][0] = true;
            board[0][0] = 2;
        }

        while (!queue.isEmpty()) {
            Point current = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = current.x + dx[i];
                int ny = current.y + dy[i];

                if (nx < 0 || nx >= n || ny < 0 || ny >= m || visited[nx][ny]) { // 범위를 넘거나 이미 방문했다면
                    continue;
                }

                if (board[nx][ny] == 0) {
                    visited[nx][ny] = true;
                    board[nx][ny] = 2;
                    queue.offer(new Point(nx, ny));
                }
            }
        }
    }

    private static class Point {
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
