import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    // 상하좌우 및 자기 자신 (5방향)
    static final int[] DR = {0, 0, 0, 1, -1};
    static final int[] DC = {0, 1, -1, 0, 0};
    static final int SIZE = 10;
    static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력 배열 (O: 켜짐, #: 꺼짐)
        // 켜짐: true, 꺼짐: false로 변환하여 처리
        boolean[][] initialBoard = new boolean[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            String line = br.readLine();
            for (int j = 0; j < SIZE; j++) {
                initialBoard[i][j] = (line.charAt(j) == 'O');
            }
        }

        int minClicks = INF;

        // 1. 첫 번째 행 (R0)에 대한 완전 탐색 (2^10 = 1024가지)
        // 'i'는 R0의 스위치 조작 경우의 수를 나타냄 (비트 마스킹)
        for (int i = 0; i < (1 << SIZE); i++) {
            // 원본 배열 복사: 각 경우마다 새로운 시뮬레이션 시작
            boolean[][] currentBoard = new boolean[SIZE][SIZE];
            for (int r = 0; r < SIZE; r++) {
                currentBoard[r] = Arrays.copyOf(initialBoard[r], SIZE);
            }

            int currentClicks = 0;

            // 1-1. 비트 마스킹에 따라 R0의 스위치 조작
            for (int c = 0; c < SIZE; c++) {
                if ((i & (1 << c)) != 0) { // c번째 비트가 1이면 스위치를 누름
                    pushSwitch(currentBoard, 0, c);
                    currentClicks++;
                }
            }

            // 2. 나머지 행 (R1부터 R9)에 대한 그리디 적용
            // R[r] 행의 스위치는 R[r-1] 행의 전구를 끄기 위해 사용됨
            for (int r = 1; r < SIZE; r++) {
                for (int c = 0; c < SIZE; c++) {
                    // 바로 윗 행 (r-1)의 전구가 켜져 있다면
                    if (currentBoard[r - 1][c]) {
                        // 현재 행 (r)의 스위치를 눌러 끈다. (그리디 선택)
                        pushSwitch(currentBoard, r, c);
                        currentClicks++;
                    }
                }
            }

            // 3. 최종 확인: 마지막 행(R9)의 전구가 모두 꺼졌는지 확인
            boolean allOff = true;
            for (int c = 0; c < SIZE; c++) {
                if (currentBoard[SIZE - 1][c]) {
                    allOff = false; // 켜진 전구가 남아 있음
                    break;
                }
            }

            // 모든 전구가 꺼진 경우, 최소 횟수 갱신
            if (allOff) {
                minClicks = Math.min(minClicks, currentClicks);
            }
        }

        // 결과 출력 (INF면 불가능하므로 -1)
        if (minClicks == INF) {
            System.out.println(-1);
        } else {
            System.out.println(minClicks);
        }
    }

    /**
     * (r, c) 위치의 스위치를 누르고, 자신과 상하좌우 5개 전구의 상태를 토글
     *
     * @param board 현재 전구 상태 배열
     * @param r     행 인덱스
     * @param c     열 인덱스
     */
    static void pushSwitch(boolean[][] board, int r, int c) {
        for (int i = 0; i < 5; i++) {
            int nr = r + DR[i];
            int nc = c + DC[i];

            if (nr >= 0 && nr < SIZE && nc >= 0 && nc < SIZE) {
                board[nr][nc] = !board[nr][nc]; // 상태 토글 (켜짐 ↔ 꺼짐)
            }
        }
    }
}
