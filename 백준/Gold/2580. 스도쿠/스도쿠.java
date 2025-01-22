import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Optional;
import java.util.StringTokenizer;

public class Main {

    static int[][] board = new int[9][9];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for (int i = 0; i < 9; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        solve();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sb.append(board[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    private static boolean solve() {
        Optional<Block> block = findEmpty();

        if (block.isEmpty()) {
            return true;
        }

        int row = block.get().x;
        int col = block.get().y;

        for (int num = 1; num <= 9; num++) {
            if (isValid(num, row, col)) {
                board[row][col] = num;

                if (solve()) {
                    return true;
                }
                board[row][col] = 0;
            }
        }
        return false;
    }

    private static Optional<Block> findEmpty() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == 0) {
                    return Optional.of(new Block(i, j));
                }
            }
        }
        return Optional.empty();
    }

    private static boolean isValid(int num, int row, int col) {
        return !(inRow(num, row) || inCol(num, col) || inBox(num, row, col));
    }

    private static boolean inBox(final int num, final int row, final int col) {
        int boxRow = (row / 3) * 3;
        int boxCol = (col / 3) * 3;

        for (int i = boxRow; i < boxRow + 3; i++) {
            for (int j = boxCol; j < boxCol + 3; j++) {
                if (board[i][j] == num) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean inCol(final int num, final int col) {
        for (int i = 0; i < 9; i++) {
            if (board[i][col] == num) {
                return true;
            }
        }
        return false;
    }

    private static boolean inRow(final int num, final int row) {
        return Arrays.stream(board[row]).anyMatch(x -> x == num);
    }

    static class Block {
        int x;
        int y;

        public Block(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
