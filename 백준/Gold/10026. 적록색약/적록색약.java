import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    private static char[][] board;
    private static boolean[][] visited;
    private static int normal = 0;
    private static int damage = 0;
    private static StringBuilder sb = new StringBuilder();
    private static int[] nx = {1, -1, 0, 0};
    private static int[] ny = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        board = new char[n][n];
        visited = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            for (int j = 0; j < n; j++) {
                board[i][j] = s.charAt(j);
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j]) {
                    dfs1(i, j);
                    normal++;
                }
            }
        }
        sb.append(normal).append(" ");

        for (int i = 0; i < n; i++) {
            Arrays.fill(visited[i], false);
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j]) {
                    dfs2(i, j);
                    damage++;
                }
            }
        }

        sb.append(damage);
        System.out.println(sb);
    }

    private static void dfs1(int x, int y) {
        visited[x][y] = true;
        for (int i = 0; i < 4; i++) {
            int dx = x + nx[i];
            int dy = y + ny[i];

            if (dx < 0 || dx >= board.length || dy < 0 || dy >= board[0].length || board[dx][dy] != board[x][y]) {
                continue;
            }
            if (!visited[dx][dy]) {
                dfs1(dx, dy);
            }
        }
    }

    private static void dfs2(int x, int y) {
        visited[x][y] = true;
        for (int i = 0; i < 4; i++) {
            int dx = x + nx[i];
            int dy = y + ny[i];

            if (dx < 0 || dx >= board.length || dy < 0 || dy >= board[0].length) {
                continue;
            }
            if (!visited[dx][dy]) {
                if (board[x][y] == 'R' || board[x][y] == 'G') {
                    if (board[dx][dy] != 'B') {
                        dfs2(dx, dy);
                    }
                } else {
                    if (board[dx][dy] == 'B') {
                        dfs2(dx, dy);
                    }
                }
            }
        }
    }
}
