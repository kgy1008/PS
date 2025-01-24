import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        
        for (int s : scoville) {
            queue.offer(s);
        }
        
        while (queue.size() > 1) {
            int first = queue.poll();
            if (first >= K) {
                break;
            }
            int second = queue.poll();
            int newItem = first + (second * 2);
            queue.offer(newItem);
            answer++;
        }
        
        return (queue.peek() >= K) ? answer : -1;
    }
}
