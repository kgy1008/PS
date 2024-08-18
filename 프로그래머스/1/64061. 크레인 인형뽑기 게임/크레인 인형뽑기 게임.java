import java.util.*;

class Solution {
    public int solution(int[][] board, int[] moves) {
        int answer = 0;
        
        ArrayDeque<Integer>[] deques = new ArrayDeque[board[0].length];
        for (int i = 0; i < board[0].length; i++) {
            deques[i] = new ArrayDeque<Integer>();
            for (int j = board.length - 1; j >= 0; j--) {
                if (board[j][i] != 0) { 
                    deques[i].push(board[j][i]);
                }
            }
        }
        
        ArrayDeque<Integer> box = new ArrayDeque<>();
        
        for (int move : moves) {
            int index = move - 1; 
            if (!deques[index].isEmpty()) {
                int doll = deques[index].pop();
                if (!box.isEmpty() && box.peek() == doll) {
                    box.pop(); 
                    answer += 2;
                } else {
                    box.push(doll); 
                }
            }
        }
        
        return answer;
    }
}