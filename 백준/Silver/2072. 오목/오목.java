import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[][] board = new int[20][20];
    static int[] dr = {0, 1, 1, 1};
    static int[] dc = {1, 0, 1, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());

            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            int color = (i % 2 != 0) ? 1 : -1;

            board[r][c] = color;

            if (checkWin(r, c, color)) {
                System.out.println(i);
                return;
            }
        }

        System.out.println(-1);
    }

    static boolean checkWin(int r, int c, int color) {
        for (int d = 0; d < 4; d++) {
            int count = count(r, c, color, dr[d], dc[d]);
            count += count(r, c, color, -dr[d], -dc[d]) - 1;

            if (count == 5) {
                return true;
            }
        }

        return false;
    }

    static int count(int r, int c, int color, int dr, int dc) {
        int count = 0;
        int nr = r;
        int nc = c;

        while (nr >= 1 && nr <= 19 && nc >= 1 && nc <= 19 && board[nr][nc] == color) {
            count++;
            nr += dr;
            nc += dc;
        }
        return count;
    }
}
