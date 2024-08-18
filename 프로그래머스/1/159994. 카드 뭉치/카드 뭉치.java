import java.util.*;

class Solution {
    public String solution(String[] cards1, String[] cards2, String[] goal) {
        String answer = "Yes";
        ArrayDeque<String> queue1 = new ArrayDeque<>();
        ArrayDeque<String> queue2 = new ArrayDeque<>();
        
        for (String card : cards1) {
            queue1.add(card);
        }
        
        for (String card : cards2) {
            queue2.add(card);
        }
        
        for (int i = 0; i<goal.length; i++) {
            if (goal[i].equals(queue1.peekFirst())) {
                queue1.pollFirst();
            }
            else if (goal[i].equals(queue2.peekFirst())) {
                queue2.pollFirst();
            }
            else {
                answer = "No";
                break;
            }
        }
        
        return answer;
    }
}