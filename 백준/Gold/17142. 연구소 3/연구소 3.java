import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static final int[] dx = {0, 0, 1, -1};
    static final int[] dy = {1, -1, 0, 0};

    static int n;
    static int m;
    static int[][] board;
    static List<Virus> virusList = new ArrayList<>();
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken()); // 연구소의 크기
        m = Integer.parseInt(st.nextToken()); // 바이러스의 개수

        board = new int[n][n];
        int emptyCount = 0;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                // 0: 빈칸, 1: 벽, 2: 바이러스 위치
                board[i][j] = Integer.parseInt(st.nextToken());

                if (board[i][j] == 2) { // 비활성 상태 바이러스 위치들 저장
                    virusList.add(new Virus(i, j));
                }

                if (board[i][j] == 0) {
                    emptyCount++;
                }
            }
        }

        solve(0, 0, new ArrayList<>(), emptyCount);

        int result = (answer == Integer.MAX_VALUE ? -1 : answer);
        System.out.println(result);
    }

    private static void solve(int idx, int cnt, List<Virus> list, int emptyCnt) {
        if (cnt == m) {
            spread(new ArrayList<>(list), emptyCnt);
            return;
        }

        // 활성 바이러스 목록 조합 구하기
        for (int i = idx; i < virusList.size(); i++) {
            list.add(virusList.get(i));
            solve(i + 1, cnt + 1, list, emptyCnt);
            list.remove(list.size() - 1);
        }
    }

    private static void spread(List<Virus> list, int emptyCount) { // 인자 변경
        boolean[][] visited = new boolean[n][n];

        Deque<Virus> queue = new ArrayDeque<>();
        for (Virus v : list) {
            queue.offer(v);
            visited[v.x][v.y] = true;
        }

        int maxTime = 0;
        int spreadCount = 0;

        while (!queue.isEmpty()) {
            Virus cur = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (nx < 0 || nx >= n || ny < 0 || ny >= n || visited[nx][ny] || board[nx][ny] == 1) {
                    continue;
                }

                visited[nx][ny] = true;

                if (board[nx][ny] == 0) {
                    maxTime = Math.max(maxTime, cur.time + 1);
                    spreadCount++;
                }

                queue.offer(new Virus(nx, ny, cur.time + 1));
            }
        }

        if (spreadCount == emptyCount) {
            answer = Math.min(answer, maxTime);
        }
    }

    static class Virus {
        int x, y;
        int time;

        public Virus(final int x, final int y) {
            this.x = x;
            this.y = y;
            this.time = 0;
        }

        public Virus(final int x, final int y, final int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }
}
