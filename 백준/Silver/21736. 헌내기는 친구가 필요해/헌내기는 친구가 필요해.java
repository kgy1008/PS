import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static char[][] board;
    private static boolean[][] visited;
    private static int ans = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        board = new char[n][m];
        visited = new boolean[n][m];

        for (int col = 0; col < n; col++) {
            String num = br.readLine();
            for (int row = 0; row < m; row++) {
                board[col][row] = num.charAt(row);
            }
        }

        outer:
        for (int col = 0; col < n; col++) {
            for (int row = 0; row < m; row++) {
                if (board[col][row] == 'I') {
                    dfs(col, row);
                    break outer;
                }
            }
        }

        System.out.println(ans == 0 ? "TT" : ans);
    }

    private static void dfs(int x, int y) {
        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};

        visited[x][y] = true;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx < 0 || ny < 0 || nx >= board.length || ny >= board[0].length) {
                continue;
            }
            if (!visited[nx][ny]) {
                if (board[nx][ny] != 'X') {
                    if (board[nx][ny] == 'P') {
                        ans++;
                    }
                    dfs(nx, ny);
                }
            }
        }
    }
}
