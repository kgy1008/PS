import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        
        for (int s : scoville) {
            queue.offer(s);
        }
        
        while (queue.size() != 1 && checkRepeat(queue, K)) {
            int first = queue.poll();
            int second = queue.poll();
            int newItem = first + (second * 2);
            queue.offer(newItem);
            answer++;
        }
        return (checkRepeat(queue, K)) ? -1 : answer;
    }
    
    private boolean checkRepeat(PriorityQueue<Integer> queue, int k) {
        for (int item : queue) {
            if (item < k) {
                return true;
            }
        }
        return false;
    }
}