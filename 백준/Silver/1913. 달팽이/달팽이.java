import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int[][] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        board = new int[N][N];
        fill(N);

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }

        int M = Integer.parseInt(br.readLine());
        int[] answer = find(M, N);
        System.out.println(answer[0] + " " + answer[1]);
    }

    private static void fill(int n) {
        int x = 0, y = 0;
        int width = n;
        int num = n * n;

        while (num > 1) {
            for (int i = x; i < x + width - 1; i++) {
                board[i][y] = num--;
            }
            x += width - 1;

            for (int i = y; i < y + width - 1; i++) {
                board[x][i] = num--;
            }
            y += width - 1;

            for (int i = x; i > x - width + 1; i--) {
                board[i][y] = num--;
            }
            x -= width - 1;

            for (int i = y; i > y - width + 1; i--) {
                board[x][i] = num--;
            }
            y -= width - 1;

            width -= 2;
            x++;
            y++;
        }

        int mid = n / 2;
        board[mid][mid] = num; // 1 채우기
    }

    private static int[] find(int m, int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == m) {
                    return new int[]{i + 1, j + 1};
                }
            }
        }
        return new int[]{-1, -1};
    }
}
