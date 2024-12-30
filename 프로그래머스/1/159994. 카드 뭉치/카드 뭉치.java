import java.util.*;

class Solution {
    public String solution(String[] cards1, String[] cards2, String[] goal) {
        Deque<String> card1 = new ArrayDeque<>();
        Deque<String> card2 = new ArrayDeque<>();
        
        for (String c1 : cards1) {
            card1.offer(c1);
        }
        for (String c2 : cards2) {
            card2.offer(c2);
        }
        
        for (String target : goal) {
            if (target.equals(card1.peek())) {
                card1.poll();
            }
            else if (target.equals(card2.peek())) {
                card2.poll();
            } else {
                return "No";
            }
        }
        
        return "Yes";
    }
}