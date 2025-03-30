import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());  // 행 크기
        int M = Integer.parseInt(st.nextToken());  // 열 크기
        int K = Integer.parseInt(st.nextToken());  // 부분 체스판의 크기

        char[][] board = new char[N][M];
        for (int i = 0; i < N; i++) {
            String row = br.readLine();
            for (int j = 0; j < M; j++) {
                board[i][j] = row.charAt(j);
            }
        }

        int[][] whiteBoard = new int[N][M];
        int[][] blackBoard = new int[N][M];

        // 체스판의 첫 번째 색을 기준으로 변경 횟수 기록
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if ((i + j) % 2 == 0) {
                    whiteBoard[i][j] = (board[i][j] == 'W' ? 0 : 1);
                    blackBoard[i][j] = (board[i][j] == 'B' ? 0 : 1);
                } else {
                    whiteBoard[i][j] = (board[i][j] == 'B' ? 0 : 1);
                    blackBoard[i][j] = (board[i][j] == 'W' ? 0 : 1);
                }
            }
        }

        int[][] whitePrefixSum = new int[N + 1][M + 1];
        int[][] blackPrefixSum = new int[N + 1][M + 1];

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                whitePrefixSum[i][j] =
                        whitePrefixSum[i - 1][j] + whitePrefixSum[i][j - 1] - whitePrefixSum[i - 1][j - 1] + whiteBoard[
                                i - 1][j - 1];
                blackPrefixSum[i][j] =
                        blackPrefixSum[i - 1][j] + blackPrefixSum[i][j - 1] - blackPrefixSum[i - 1][j - 1] + blackBoard[
                                i - 1][j - 1];
            }
        }
        
        int minChanges = Integer.MAX_VALUE;
        for (int i = K; i <= N; i++) {
            for (int j = K; j <= M; j++) {
                int whiteCost =
                        whitePrefixSum[i][j] - whitePrefixSum[i - K][j] - whitePrefixSum[i][j - K] + whitePrefixSum[i
                                - K][j - K];
                int blackCost =
                        blackPrefixSum[i][j] - blackPrefixSum[i - K][j] - blackPrefixSum[i][j - K] + blackPrefixSum[i
                                - K][j - K];
                minChanges = Math.min(minChanges, Math.min(whiteCost, blackCost));
            }
        }

        System.out.println(minChanges);
    }
}
