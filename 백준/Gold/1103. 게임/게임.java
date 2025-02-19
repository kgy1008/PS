import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int n, m;
    static char[][] board;
    static int[][] dp;
    static boolean[][] visited;
    static int answer = 0;
    static boolean hasCycle = false;  // 무한 루프 감지

    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new char[n][m];
        dp = new int[n][m];
        visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            for (int j = 0; j < m; j++) {
                board[i][j] = s.charAt(j);
            }
        }

        answer = dfs(0, 0);

        if (hasCycle) {
            System.out.println(-1);
        } else {
            System.out.println(answer);
        }
    }

    static int dfs(int x, int y) {
        if (hasCycle) {
            return -1;
        }

        if (x < 0 || x >= n || y < 0 || y >= m || board[x][y] == 'H') {
            return 0;
        }

        if (visited[x][y]) {
            hasCycle = true;
            return -1;
        }
        
        if (dp[x][y] != 0) {
            return dp[x][y];
        }

        visited[x][y] = true;
        int move = board[x][y] - '0';
        int maxDepth = 0;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i] * move;
            int ny = y + dy[i] * move;
            int depth = dfs(nx, ny);
            if (hasCycle) {
                return -1; // 탐색 중 사이클이 발견되면 즉시 종료
            }
            maxDepth = Math.max(maxDepth, depth);
        }

        visited[x][y] = false;
        return dp[x][y] = maxDepth + 1;
    }
}
