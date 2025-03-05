import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static final int block = 3; // 세울 벽의 개수 : 3으로 고정
    static final List<int[]> list = new ArrayList<>(); // 벽을 세울 수 있는 후보들의 위치를 저장하는 리스트
    static int N;
    static int M;
    static int[][] board;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static int safeArea = 0;
    static int answer = 0;
    static List<int[]> virus = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] == 0) {  // 빈칸이면 새롭게 벽을 세울 수 있는 곳
                    list.add(new int[]{i, j});
                    safeArea++;  // 초기 안전 지역의 개수
                } else if (board[i][j] == 2) { // 바이러스들 위치 기록
                    virus.add(new int[]{i, j});
                }
            }
        }

        solve(0, new ArrayList<>());
        System.out.println(answer);
    }

    static void solve(int idx, List<int[]> newBlock) {
        if (newBlock.size() == block) {
            // 벽을 새로 새워 보드의 값을 수정
            int[][] newBoard = changeBoard(new ArrayList<>(newBlock));
            int safeAreaCount = calculateSafeBlockCount(newBoard);
            answer = Math.max(answer, safeAreaCount);
            return;
        }

        for (int i = idx; i < list.size(); i++) {
            int[] block = list.get(i);
            newBlock.add(block);
            solve(i + 1, newBlock);
            newBlock.remove(newBlock.size() - 1);
        }
    }

    private static int[][] changeBoard(List<int[]> newBlock) {
        int[][] newBoard = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                newBoard[i][j] = board[i][j];
            }
        }
        
        for (int[] block : newBlock) {
            newBoard[block[0]][block[1]] = 1; // 벽 세움
        }
        return newBoard;
    }

    private static int calculateSafeBlockCount(final int[][] newBoard) {
        int safeBlockCount = safeArea - block;  // 초기 안전 구역의 개수

        Deque<int[]> queue = new ArrayDeque<>();
        for (int[] v : virus) {
            queue.offer(v);
        }

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x = cur[0];
            int y = cur[1];

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                // 범위를 벗어나지 않고 비어있는 경우일 때
                if (nx >= 0 && nx < N && ny >= 0 && ny < M && newBoard[nx][ny] == 0) {
                    newBoard[nx][ny] = 2;
                    safeBlockCount--; // 바이러스가 퍼졌으므로 안전 구역 1만큼 감소
                    queue.offer(new int[]{nx, ny});
                }
            }
        }
        return safeBlockCount;
    }
}
