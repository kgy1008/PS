import java.util.*;

class Solution {
    public int solution(int[] priorities, int location) {
        int answer = 0;
        Deque<int[]> queue = new ArrayDeque<>();
        for (int i=0; i<priorities.length; i++) {
            queue.offer(new int[]{i, priorities[i]});
        }
        
        while (!queue.isEmpty()) {
            int[] out = queue.poll();
            if (!isMaxPriority(queue, out[1])) {
                queue.offer(out);
            } else {
                answer++;
                if (out[0] == location) {
                    break;
                }
            }
        }
        return answer;
    }
    
    private static boolean isMaxPriority(Deque<int[]> queue, int out) {
        for (int[] num : queue) {
            if (out < num[1]) {
                return false;
            }
        }
        return true;
    }
}