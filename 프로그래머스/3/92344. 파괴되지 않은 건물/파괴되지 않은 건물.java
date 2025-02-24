class Solution {
    public int solution(int[][] board, int[][] skill) {
        int n = board.length;
        int m = board[0].length;
        
        int[][] diff = new int[n+1][m+1];
        
        for (int[] s : skill) {
            int type = s[0] == 1 ? -1 : 1; 
            int r1 = s[1], c1 = s[2], r2 = s[3], c2 = s[4];
            int degree = s[5] * type;
            
            diff[r1][c1] += degree;
            diff[r1][c2+1] -= degree;
            diff[r2+1][c1] -= degree;
            diff[r2+1][c2+1] += degree;
        }
        
        // 행 기준 누적 합
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < m; j++) {
                diff[i][j] += diff[i][j - 1];
            }
        }

        // 열 기준 누적 합
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < m; j++) {
                diff[i][j] += diff[i - 1][j];
            }
        }
        
        int answer = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                board[i][j] += diff[i][j];
                if (board[i][j] > 0) {
                    answer++;
                }
            }
        }
        return answer;
    }
}