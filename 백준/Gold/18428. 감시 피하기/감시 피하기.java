import static java.lang.System.exit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static final int block = 3; // 세울 장애물의 개수 : 3으로 고정
    static final List<int[]> list = new ArrayList<>(); // 장애물을 세울 수 있는 후보들의 위치를 저장하는 리스트
    static int N;
    static char[][] board;
    static List<int[]> teachers = new ArrayList<>();

    // 상하좌우 방향
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        board = new char[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = st.nextToken().charAt(0);
                if (board[i][j] == 'X') {  // 빈칸이면 새롭게 장애물을 세울 수 있는 곳
                    list.add(new int[]{i, j});
                } else if (board[i][j] == 'T') { // 선생님들 위치 기록
                    teachers.add(new int[]{i, j});
                }
            }
        }

        solve(0, new ArrayList<>());
        System.out.println("NO"); // 모든 경우를 시도했지만 안전 구역을 확보하지 못한 경우
    }

    static void solve(int idx, List<int[]> newBlock) {
        if (newBlock.size() == block) {
            // 벽을 새로 세워 보드의 값을 수정
            char[][] newBoard = changeBoard(new ArrayList<>(newBlock));
            if (isSafe(newBoard)) {
                System.out.println("YES");
                exit(0); // 안전 구역이 확보되면 프로그램 종료
            }
            return;
        }

        for (int i = idx; i < list.size(); i++) {
            int[] obstacle = list.get(i);
            newBlock.add(obstacle);
            solve(i + 1, newBlock);
            newBlock.remove(newBlock.size() - 1);
        }
    }

    private static char[][] changeBoard(List<int[]> newBlock) {
        char[][] newBoard = new char[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                newBoard[i][j] = board[i][j];
            }
        }

        for (int[] obstacle : newBlock) {
            newBoard[obstacle[0]][obstacle[1]] = 'O'; // 벽 세움
        }
        return newBoard;
    }

    private static boolean isSafe(final char[][] newBoard) {
        for (int[] teacher : teachers) {
            int x = teacher[0];
            int y = teacher[1];

            // 상하좌우 4방향으로 감시
            for (int dir = 0; dir < 4; dir++) {
                int nx = x + dx[dir];
                int ny = y + dy[dir];

                // 해당 방향으로 계속 진행하면서 체크
                while (nx >= 0 && nx < N && ny >= 0 && ny < N) {
                    if (newBoard[nx][ny] == 'O') {
                        // 장애물을 만나면 이 방향의 감시 중단
                        break;
                    }
                    if (newBoard[nx][ny] == 'S') {
                        // 학생을 발견하면 안전하지 않음
                        return false;
                    }
                    nx += dx[dir];
                    ny += dy[dir];
                }
            }
        }
        return true;
    }
}
