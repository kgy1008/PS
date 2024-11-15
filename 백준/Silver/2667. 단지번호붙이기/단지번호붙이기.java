import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    private static int n;
    private static int[][] board;
    private static boolean[][] visited;
    private static int ans = 0;
    private static List<Integer> answer = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        board = new int[n][n];
        visited = new boolean[n][n];

        for (int col = 0; col < n; col++) {
            String num = br.readLine();
            for (int row = 0; row < n; row++) {
                board[col][row] = num.charAt(row) - '0';
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 1 && !visited[i][j]) {
                    ans = 0;
                    dfs(i, j);
                    answer.add(ans);
                }
            }
        }

        Collections.sort(answer);
        System.out.println(answer.size());
        for (int count : answer) {
            System.out.println(count);
        }
    }

    private static void dfs(int x, int y) {
        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};

        visited[x][y] = true;
        ans++;

        for (int k = 0; k < 4; k++) {
            int nx = x + dx[k];
            int ny = y + dy[k];
            if (nx < 0 || ny < 0 || nx >= n || ny >= n) {
                continue;
            }
            if (!visited[nx][ny] && board[nx][ny] == 1) {
                dfs(nx, ny);
            }
        }
    }
}
