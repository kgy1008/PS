class Solution {
    
    static int zero = 0;
    static int one = 0;
    static int[][] board;
    
    public int[] solution(int[][] arr) {
        board = arr;
        solve(board.length, 0, 0);

        return new int[]{zero, one};
    }
    
    static void solve(int size, int row, int col) {
        if (isFull(size, row, col)) {
            count(row, col);
            return;
        }
        
        size /= 2;
        solve(size, row, col);
        solve(size, row, col + size);
        solve(size, row + size, col);
        solve(size, row + size, col + size);
    }
    
    static void count(int row, int col) {
        if (board[row][col] == 1) {
            one++;
        } else {
            zero++;
        }
    }
    
    static boolean isFull(int size, int row, int col) {
        int flag = board[row][col];
        
        for (int i=row; i<row+size; i++) {
            for (int j=col; j<col+size; j++) {
                if (flag != board[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
}