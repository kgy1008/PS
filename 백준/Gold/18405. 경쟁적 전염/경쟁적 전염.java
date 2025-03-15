import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int[] dx = new int[]{0, 0, 1, -1};
    static int[] dy = new int[]{1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] board = new int[N][N];
        List<Node> list = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] != 0) {
                    list.add(new Node(board[i][j], i, j, 0));
                }
            }
        }

        list.sort(Comparator.comparingInt(o -> o.dest));
        ArrayDeque<Node> queue = new ArrayDeque<>(list);

        st = new StringTokenizer(br.readLine());
        int S = Integer.parseInt(st.nextToken());  // S초 뒤
        int X = Integer.parseInt(st.nextToken()) - 1;
        int Y = Integer.parseInt(st.nextToken()) - 1;

        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            if (cur.time == S) {
                break;  // S초가 지나면 종료
            }
            
            int x = cur.row;
            int y = cur.col;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx >= 0 && nx < N && ny >= 0 && ny < N && board[nx][ny] == 0) {
                    board[nx][ny] = cur.dest;
                    queue.offer(new Node(cur.dest, nx, ny, cur.time + 1));
                }
            }
        }
        
        System.out.println(board[X][Y]);
    }

    static class Node {
        int dest;
        int row;
        int col;
        int time;

        public Node(final int dest, final int row, final int col, final int time) {
            this.dest = dest;
            this.row = row;
            this.col = col;
            this.time = time;
        }
    }
}
