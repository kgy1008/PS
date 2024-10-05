import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[][] board;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());

        board = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int ans = 0;
        while (true) {
            visited = new boolean[n][n];
            boolean moved = false;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (!visited[i][j]) {
                        if (bfs(l, r, i, j)) {
                            moved = true;
                        }
                    }
                }
            }
            if (!moved) break;
            ans++;
        }
        System.out.println(ans);
    }

    private static boolean bfs(int l, int r, int startX, int startY) {
        ArrayDeque<int[]> queue = new ArrayDeque<>();
        Set<int[]> union = new HashSet<>();

        queue.add(new int[]{startX, startY});
        visited[startX][startY] = true;
        union.add(new int[]{startX, startY});

        int[] dx = new int[]{1, -1, 0, 0};
        int[] dy = new int[]{0, 0, 1, -1};

        int totalPeople = board[startX][startY];
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x = cur[0];
            int y = cur[1];

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || nx >= board.length || ny < 0 || ny >= board.length) {
                    continue;
                }

                if (!visited[nx][ny]) {
                    int diff = Math.abs(board[x][y] - board[nx][ny]);
                    if (diff >= l && diff <= r) {
                        queue.offer(new int[]{nx, ny});
                        visited[nx][ny] = true;
                        union.add(new int[]{nx, ny});
                        totalPeople += board[nx][ny];
                    }
                }
            }
        }

        if (union.size() > 1) {
            int newPopulation = totalPeople / union.size();
            for (int[] coordinate : union) {
                board[coordinate[0]][coordinate[1]] = newPopulation;
            }
            return true;
        }

        return false;
    }
}
