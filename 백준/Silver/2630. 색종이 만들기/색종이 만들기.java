import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int[][] board;
    private static int white;
    private static int blue;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        board = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        solve(0, 0, n);
        System.out.println(white);
        System.out.println(blue);
    }

    private static void solve(int col, int row, int n) {
        if (n == 1) {
            if (board[col][row] == 1) {
                blue++;
            } else {
                white++;
            }
            return;
        }

        if (checkUniform(col, row, n)) {
            if (board[col][row] == 1) {
                blue++;
            } else {
                white++;
            }
        } else {
            int half = n / 2;
            solve(col, row, half);
            solve(col, row + half, half);
            solve(col + half, row, half);
            solve(col + half, row + half, half);
        }
    }

    private static boolean checkUniform(int col, int row, int n) {
        int value = board[col][row];
        for (int i = col; i < col + n; i++) {
            for (int j = row; j < row + n; j++) {
                if (board[i][j] != value) {
                    return false;
                }
            }
        }
        return true;
    }
}
