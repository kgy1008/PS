import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        Deque<Integer> remainDate = new ArrayDeque<>();
        
        for (int i=0; i<progresses.length; i++) {
            int remain = 100 - progresses[i];
            if (remain % speeds[i] == 0) {
                remainDate.offer(remain/speeds[i]);
            } else {
                remainDate.offer(remain/speeds[i] + 1);
            }   
        }
        
        List<Integer> answer = new ArrayList<>();
        while (!remainDate.isEmpty()) {
            int cnt = 1;
            int done = remainDate.poll();
            while (!remainDate.isEmpty() && done >= remainDate.peek()) {
                cnt++;
                remainDate.poll();
            }
            answer.add(cnt);
        }
        
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
}