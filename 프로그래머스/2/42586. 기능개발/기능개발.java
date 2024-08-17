import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        ArrayList<Integer> answer = new ArrayList<>();
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        
        for (int i = 0; i < speeds.length; i++) {
            int day = (100 - progresses[i] + speeds[i] - 1) / speeds[i];
            queue.addLast(day);
        }

        while (!queue.isEmpty()) {
            int currentDay = queue.pollFirst();
            int cnt = 1;

            while (!queue.isEmpty() && queue.peekFirst() <= currentDay) {
                queue.pollFirst();
                cnt++;
            }

            answer.add(cnt);
        }

        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
}
