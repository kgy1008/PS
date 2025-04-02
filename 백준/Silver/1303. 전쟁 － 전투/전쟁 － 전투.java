import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int M;
    static char[][] board;
    static boolean[][] visited;
    static HashMap<Character, Integer> map = new HashMap<>();
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static int count = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new char[M][N];
        visited = new boolean[M][N];

        for (int i = 0; i < M; i++) {
            String line = br.readLine();
            for (int j = 0; j < N; j++) {
                board[i][j] = line.charAt(j);
            }
        }
        map.put('W', 0);
        map.put('B', 0);

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j]) {
                    count = 1;
                    dfs(i, j, board[i][j]);
                    int tmp = map.get(board[i][j]);
                    tmp += count * count;
                    map.put(board[i][j], tmp);
                }
            }
        }

        int b = map.get('B');
        int w = map.get('W');

        System.out.println(w + " " + b);
    }

    private static void dfs(int x, int y, char target) {
        visited[x][y] = true;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || nx >= M || ny < 0 || ny >= N || visited[nx][ny]) {
                continue;
            }

            if (target == board[nx][ny]) {
                visited[nx][ny] = true;
                count++;
                dfs(nx, ny, target);
            }
        }
    }
}
