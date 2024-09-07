import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        Deque<Integer> remainDay = new ArrayDeque<>();
        
        for (int i=0; i<progresses.length; i++) {
            remainDay.offer((100 - progresses[i] + speeds[i] - 1) / speeds[i]);
        }
        
        List<Integer> ans = new ArrayList<>();
        
        while (!remainDay.isEmpty()) {
            int count = 0;
            int firstWork = remainDay.peekFirst();
            while (!remainDay.isEmpty() && firstWork >= remainDay.peekFirst()) {
                remainDay.poll();
                count++;
            }
            ans.add(count);
        }
        
        return ans.stream().mapToInt(Integer::intValue).toArray();
    }
}