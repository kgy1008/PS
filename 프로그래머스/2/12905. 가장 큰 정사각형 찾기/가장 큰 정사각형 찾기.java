import java.util.*;

class Solution {
    public int solution(int[][] board) {
        int n = board.length;
        int m = board[0].length;
        
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (board[i][j] == 1) {
                    board[i][j] = Math.min(Math.min(board[i-1][j], board[i][j-1]), board[i-1][j-1]) + 1;
                }
            }
        }
        
        int maxSize = 0;
        for (int[] arr : board) {
            for (int num : arr) {
                if (maxSize < num) maxSize = num;
            }
        }        
        
        return maxSize * maxSize; 
    }

}