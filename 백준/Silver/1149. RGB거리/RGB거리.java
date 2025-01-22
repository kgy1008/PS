import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[][] board = new int[n][3];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = findMin(board[i - 1], j) + board[i][j];
            }
        }

        int answer = Arrays.stream(board[n - 1]).min().getAsInt();
        System.out.println(answer);
    }

    private static int findMin(int[] target, int j) {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < 3; i++) {
            if (i != j) {
                min = Math.min(min, target[i]);
            }
        }
        return min;
    }
}
