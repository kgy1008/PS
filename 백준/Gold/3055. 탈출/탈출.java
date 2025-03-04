import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static Deque<int[]> waterQueue = new ArrayDeque<>();
    static Deque<int[]> moveQueue = new ArrayDeque<>();
    static int R;
    static int C;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        char[][] board = new char[R][C];
        int[][] times = new int[R][C];

        for (int i = 0; i < R; i++) {
            String line = br.readLine();
            for (int j = 0; j < C; j++) {
                board[i][j] = line.charAt(j);
                if (board[i][j] == 'S') {
                    moveQueue.offer(new int[]{i, j});
                } else if (board[i][j] == '*') {
                    waterQueue.offer(new int[]{i, j});
                }
            }
        }

        int answer = bfs(board, times);
        if (answer == -1) {
            System.out.println("KAKTUS");
        } else {
            System.out.println(answer);
        }

    }

    static int bfs(char[][] board, int[][] times) {
        while (!moveQueue.isEmpty()) {
            int waterSize = waterQueue.size();
            for (int w = 0; w < waterSize; w++) { // 물 퍼짐
                int[] cur = waterQueue.poll();
                for (int i = 0; i < 4; i++) {
                    int nx = cur[0] + dx[i];
                    int ny = cur[1] + dy[i];

                    if (nx >= 0 && nx < R && ny >= 0 && ny < C) {
                        if (board[nx][ny] == '.') {
                            board[nx][ny] = '*';
                            waterQueue.offer(new int[]{nx, ny});
                        }
                    }
                }
            }

            int moveSize = moveQueue.size();
            for (int m = 0; m < moveSize; m++) { // 비버 이동
                int[] cur = moveQueue.poll();
                int x = cur[0];
                int y = cur[1];
                for (int i = 0; i < 4; i++) {
                    int nx = x + dx[i];
                    int ny = y + dy[i];

                    if (nx >= 0 && nx < R && ny >= 0 && ny < C) {
                        if (board[nx][ny] == 'D') {
                            return times[cur[0]][cur[1]] + 1;
                        } else if (board[nx][ny] == '.') {
                            times[nx][ny] = times[x][y] + 1;
                            board[nx][ny] = 'S';
                            moveQueue.offer(new int[]{nx, ny});
                        }
                    }
                }
            }
        }
        return -1;
    }
}
