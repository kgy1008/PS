import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int[][] prefixSum;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tk;
        tk = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(tk.nextToken());
        int m = Integer.parseInt(tk.nextToken());

        int[][] arr = new int[n][m];
        for (int i = 0; i < n; i++) {
            tk = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(tk.nextToken());
            }
        }

        prefixSum = new int[n+1][m+1];
        for (int row = 1; row <= n; row++) {
            for (int col = 1; col <= m; col++) {
                prefixSum[row][col] = prefixSum[row][col-1] + prefixSum[row-1][col] - prefixSum[row-1][col-1] + arr[row-1][col-1];
            }
        }

        int k = Integer.parseInt(br.readLine());
        for (int r = 0; r < k; r++) {
            tk = new StringTokenizer(br.readLine());
            int i = Integer.parseInt(tk.nextToken());
            int j = Integer.parseInt(tk.nextToken());
            int x = Integer.parseInt(tk.nextToken());
            int y = Integer.parseInt(tk.nextToken());
            getAnswer(i, j, x, y);
        }
    }

    private static void getAnswer(int i, int j, int x, int y) {
        System.out.println(prefixSum[x][y] - prefixSum[x][j-1] - prefixSum[i-1][y] + prefixSum[i-1][j-1]);
    }
}
