import java.util.*;

class Solution {
    public int solution(int [][]board) {
        for (int i=1; i<board.length; i++) {
            for (int j=1; j<board[0].length; j++) {
                if (board[i][j] != 0) {
                    if (board[i-1][j-1] != 0 && board[i][j-1] != 0 && board[i-1][j] != 0) {
                        board[i][j] = Math.min(board[i-1][j-1], Math.min(board[i][j-1], board[i-1][j])) + 1;
                    }
                }
            }
        }
        
        int max = 0;
        for (int[] b : board) {
            int tmp = Arrays.stream(b).max().getAsInt();
            if (tmp > max) {
                max = tmp;
            }
        }
        return max*max;
    }
}