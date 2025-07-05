import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    private static int[] dx = {1, 0, -1, 0};
    private static int[] dy = {0, 1, 0, -1};
    private static char[][] board;
    private static int r, c;
    private static int answer = 0;
    private static Set<Character> set = new HashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        board = new char[r][c];
        for (int i = 0; i < r; i++) {
            String line = br.readLine();
            for (int j = 0; j < c; j++) {
                board[i][j] = line.charAt(j);
            }
        }

        set.add(board[0][0]);
        solve(0, 0, 1);
        System.out.println(answer);
    }

    private static void solve(int x, int y, int count) {
        answer = Math.max(answer, count);

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || nx >= r || ny < 0 || ny >= c) {
                continue;
            }

            if (!set.contains(board[nx][ny])) {
                set.add(board[nx][ny]);
                solve(nx, ny, count + 1);
                set.remove(board[nx][ny]);
            }
        }
    }
}
