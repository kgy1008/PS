import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int[][] board;
    private static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            int answer = 0;
            st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            board = new int[n][m];
            visited = new boolean[n][m];
            while (k --> 0) {
                st = new StringTokenizer(br.readLine());
                int i = Integer.parseInt(st.nextToken());
                int j = Integer.parseInt(st.nextToken());
                board[j][i] = 1;
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (board[i][j] == 1 && !visited[i][j]) {
                        dfs(i,j);
                        answer++;
                    }
                }
            }
            System.out.println(answer);
        }
    }

    private static void dfs(int i, int j) {
        visited[i][j] = true;
        int[] nx = {1,-1,0,0};
        int[] ny = {0,0,1,-1};

        for (int k = 0; k < 4; k++) {
            int dx = i+nx[k];
            int dy = j+ny[k];

            if (dx <0 || dx>=board.length || dy <0 || dy>=board[0].length) {
                continue;
            }
            if (!visited[dx][dy] && board[dx][dy] == 1) {
                dfs(dx,dy);
            }
        }
    }
}
