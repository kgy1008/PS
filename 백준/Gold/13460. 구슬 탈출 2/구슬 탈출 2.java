import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int m;
    static char[][] board;
    static boolean[][][][] visited;

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new char[n][m];
        visited = new boolean[n][m][n][m];

        int startRx = 0, startRy = 0;
        int startBx = 0, startBy = 0;

        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            board[i] = str.toCharArray();
            for (int j = 0; j < m; j++) {
                if (board[i][j] == 'R') {
                    startRx = i;
                    startRy = j;
                    board[i][j] = '.';
                }

                if (board[i][j] == 'B') {
                    startBx = i;
                    startBy = j;
                    board[i][j] = '.';
                }
            }
        }

        Position startPosition = new Position(startRx, startRy, startBx, startBy, 0);
        Deque<Position> queue = new ArrayDeque<>();
        queue.offer(startPosition);
        visited[startRx][startRy][startBx][startBy] = true;

        // BFS
        while (!queue.isEmpty()) {
            Position cur = queue.poll();

            if (cur.count >= 10) {
                continue;
            }

            for (int i = 0; i < 4; i++) {
                // 빨간공 이동
                int rnx = cur.rx;
                int rny = cur.ry;

                int[] newRed = move(rnx, rny, i);
                rnx = newRed[0];
                rny = newRed[1];
                boolean redArrived = (board[rnx][rny] == 'O');

                // 파란공 이동
                int bnx = cur.bx;
                int bny = cur.by;

                int[] newBlue = move(bnx, bny, i);
                bnx = newBlue[0];
                bny = newBlue[1];
                boolean blueArrived = (board[bnx][bny] == 'O');

                if (blueArrived) { // 파란공이 구멍에 빠지면 안됨
                    continue;
                }

                // 빨간 공 구멍에 빠짐 -> 정답
                if (redArrived) {
                    System.out.println(cur.count + 1);
                    return;
                }

                // 두 공이 같은 위치면 안됨
                if (rnx == bnx && rny == bny) {
                    if (i == 0) { // 상
                        if (cur.rx > cur.bx) {
                            rnx++;
                        } else {
                            bnx++;
                        }
                    } else if (i == 1) { // 하
                        if (cur.rx < cur.bx) {
                            rnx--;
                        } else {
                            bnx--;
                        }
                    } else if (i == 2) { // 좌 
                        if (cur.ry > cur.by) {
                            rny++;
                        } else {
                            bny++;
                        }
                    } else { // 우 
                        if (cur.ry < cur.by) {
                            rny--;
                        } else {
                            bny--;
                        }
                    }
                }

                if (!visited[rnx][rny][bnx][bny]) {
                    visited[rnx][rny][bnx][bny] = true;
                    Position nextPosition = new Position(rnx, rny, bnx, bny, cur.count + 1);
                    queue.offer(nextPosition);
                }
            }
        }

        System.out.println(-1);
    }

    static int[] move(int x, int y, int i) {
        while (board[x + dx[i]][y + dy[i]] != '#') {
            x += dx[i];
            y += dy[i];

            if (board[x][y] == 'O') {
                break;
            }
        }
        return new int[]{x, y};
    }

    static class Position {
        int rx, ry;
        int bx, by;
        int count;

        public Position(int rx, int ry, int bx, int by, int count) {
            this.rx = rx;
            this.ry = ry;
            this.bx = bx;
            this.by = by;
            this.count = count;
        }
    }
}
