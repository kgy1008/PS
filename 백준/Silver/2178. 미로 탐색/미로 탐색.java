import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    private static int[][] board;
    private static final int[] dx = {0, 1, 0, -1};
    private static final int[] dy = {-1, 0, 1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        board = new int[n][m];

        for (int i = 0; i < n; i++) {
            String input = br.readLine();
            for (int j = 0; j < m; j++) {
                board[i][j] = input.charAt(j) - '0';
            }
        }

        bfs(0, 0);
        System.out.println(board[n - 1][m - 1]);
    }

    private static void bfs(int k, int h) {
        Deque<int[]> deque = new ArrayDeque<>();
        deque.add(new int[]{k, h});
        board[k][h] = 1;

        while (!deque.isEmpty()) {
            int[] tmp = deque.poll();
            int x = tmp[0];
            int y = tmp[1];

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || nx >= board.length || ny < 0 || ny >= board[0].length || board[nx][ny] == 0) {
                    continue;
                }

                if (board[nx][ny] == 1) {
                    board[nx][ny] = board[x][y] + 1;
                    deque.add(new int[]{nx, ny});
                }
            }
        }
    }
}
