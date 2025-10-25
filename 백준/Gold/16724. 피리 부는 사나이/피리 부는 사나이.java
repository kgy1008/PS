import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    static final HashMap<Character, int[]> directionMap = new HashMap<>() {{
        put('U', new int[]{-1, 0});
        put('D', new int[]{1, 0});
        put('L', new int[]{0, -1});
        put('R', new int[]{0, 1});
    }};

    static int n;
    static int m;

    static int[][] status;
    static char[][] grid;
    static int answer = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        status = new int[n][m];
        grid = new char[n][m];

        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            grid[i] = line.toCharArray();
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (status[i][j] == 0) {
                    dfs(i, j);
                }
            }
        }

        System.out.println(answer);
    }

    private static void dfs(int x, int y) {
        status[x][y] = 1;

        char direction = grid[x][y];
        int[] move = directionMap.get(direction);
        int nx = x + move[0];
        int ny = y + move[1];

        if (status[nx][ny] == 0) {
            dfs(nx, ny);
        } else if (status[nx][ny] == 1) {
            answer++;
        }
        status[x][y] = 2;
    }
}
