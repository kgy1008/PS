import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        Queue<Integer> q1 = new LinkedList<>();
        Queue<Integer> q2 = new LinkedList<>();
        long sum1 = 0;
        long sum2 = 0;
        
        for (int num : queue1) {
            q1.add(num);
            sum1 += num;
        }
        
        for (int num : queue2) {
            q2.add(num);
            sum2 += num;
        }
        
        long totalSum = sum1 + sum2;
        if (totalSum % 2 != 0) {
            return -1;
        }
        
        long target = totalSum / 2;
        int count = 0;
        
        // 큐의 최대 길이 * 3을 반복 횟수 제한으로 설정
        // 한쪽 큐에 모든 원소를 옮기고 다시 반대쪽으로 옮기는 경우를 고려
        int limit = (queue1.length + queue2.length) * 2;
        
        while (sum1 != target) {
            if (count > limit) {
                return -1;
            }
            
            if (sum1 < target) {
                if (q2.isEmpty()) {
                    return -1;
                }
                int num = q2.poll();
                q1.add(num);
                sum1 += num;
            } else { // sum1 > target
                if (q1.isEmpty()) {
                    return -1;
                }
                int num = q1.poll();
                q2.add(num);
                sum1 -= num;
            }
            count++;
        }
        
        return count;
    }
}