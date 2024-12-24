import java.util.*;

class Solution {
    public int solution(int[][] board, int[] moves) {
        Deque<Integer> stack = new ArrayDeque<>();
        List<Deque<Integer>> doll = new ArrayList<>();
        
        for (int i=0; i<board.length; i++) {
            doll.add(new ArrayDeque<Integer>());
        }
        
        for (int i=0; i<board.length; i++) {
            for (int j=0; j<board.length; j++) {
                Deque<Integer> tmp = doll.get(i);
                if (board[j][i] != 0) {
                    tmp.add(board[j][i]); 
                }
            }
        }
        
        int answer = 0;
        
        for (int move : moves) {
            int idx = move - 1;
            Deque<Integer> tmp = doll.get(idx);
            if (tmp.size() != 0) {
                int target = tmp.poll();
                if (!stack.isEmpty() && stack.peek() == target) {
                    stack.pop();
                    answer+=2;
                    continue;
                }
                stack.push(target);
            }
        }
        
        return answer;
    }
}