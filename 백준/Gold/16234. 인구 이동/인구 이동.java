import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    static int[][] board;
    static int[] dx = new int[]{0, 0, 1, -1};
    static int[] dy = new int[]{1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());

        board = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = 0;
        while (true) {
            boolean[][] visited = new boolean[N][N];
            boolean moved = false;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!visited[i][j]) {
                        ArrayDeque<Point> queue = new ArrayDeque<>();
                        Set<Point> set = new HashSet<>();

                        queue.add(new Point(i, j));
                        visited[i][j] = true;
                        set.add(new Point(i, j));

                        int sum = 0;
                        sum += board[i][j];

                        while (!queue.isEmpty()) {
                            Point cur = queue.poll();
                            int x = cur.x;
                            int y = cur.y;

                            for (int d = 0; d < 4; d++) {
                                int nx = x + dx[d];
                                int ny = y + dy[d];

                                if (nx >= 0 && nx < N && ny >= 0 && ny < N && !visited[nx][ny]) {
                                    int diff = Math.abs(board[nx][ny] - board[x][y]);

                                    if (diff >= L && diff <= R) {
                                        queue.add(new Point(nx, ny));
                                        visited[nx][ny] = true;
                                        set.add(new Point(nx, ny));
                                        sum += board[nx][ny];
                                    }
                                }
                            }
                        }

                        // 연합이 2개 이상인 경우 인구 이동 처리
                        if (set.size() > 1) {
                            moved = true;
                            int average = sum / set.size();
                            for (Point p : set) {
                                board[p.x][p.y] = average;
                            }
                        }
                    }
                }
            }

            if (!moved) {
                break;  // 더 이상 인구 이동이 없으면 종료
            }
            answer++;
        }
        System.out.println(answer);
    }

    static class Point {
        int x;
        int y;

        public Point(final int x, final int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(final Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Point point = (Point) o;
            return x == point.x && y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
}
