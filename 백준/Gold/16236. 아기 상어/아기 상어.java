import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Deque;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    private static int n;
    private static int[] dx = {0, -1, 1, 0};
    private static int[] dy = {1, 0, 0, -1};
    private static int time = 0;
    private static int size = 2;
    private static int[][] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine()); // 공간의 크기

        board = new int[n][n];

        int startX = 0, startY = 0;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] == 9) {
                    startX = i;
                    startY = j;
                    board[i][j] = 0;
                }
            }
        }

        int eatCount = 0;
        while (true) {
            Position fish = bfs(startX, startY);
            if (fish == null) {
                break;
            }

            startX = fish.x;
            startY = fish.y;
            time += fish.distance;
            board[fish.x][fish.y] = 0;
            eatCount++;

            if (eatCount == size) {
                size++;
                eatCount = 0;
            }
        }
        System.out.println(time);
    }

    private static Position bfs(int startX, int startY) {
        Deque<Position> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[n][n];
        queue.offer(new Position(startX, startY, 0));
        visited[startX][startY] = true;

        PriorityQueue<Position> pq = new PriorityQueue<>(
                Comparator.comparingInt((Position o) -> o.distance).thenComparing(o -> o.x).thenComparing(o -> o.y));

        while (!queue.isEmpty()) {
            Position pos = queue.poll();

            for (int i = 0; i < 4; i++) {
                int x = pos.x + dx[i];
                int y = pos.y + dy[i];

                if (x < 0 || x >= n || y < 0 || y >= n || visited[x][y]) {
                    continue;
                }

                visited[x][y] = true;
                if (board[x][y] <= size) {
                    queue.offer(new Position(x, y, pos.distance + 1));

                    if (board[x][y] != 0 && board[x][y] < size) {
                        pq.offer(new Position(x, y, pos.distance + 1));
                    }
                }
            }
        }
        return pq.isEmpty() ? null : pq.poll();
    }

    static class Position {
        int x;
        int y;
        int distance;

        Position(int x, int y, int distance) {
            this.x = x;
            this.y = y;
            this.distance = distance;
        }
    }
}
