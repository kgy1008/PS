import java.util.*;

class Solution {
    public int solution(int coin, int[] cards) {
        int n = cards.length;

        Set<Integer> hand = new HashSet<>(); 
        Set<Integer> draw = new HashSet<>(); 
        
        for (int i = 0; i < n / 3; i++) {
            hand.add(cards[i]);
        }
        
        int round = n / 3;
        
        while (true) {
            if (round >= n / 3 + (n - n / 3) / 2) {
                break;
            }
            
            draw.add(cards[2 * (round - n / 3) + n / 3]);
            draw.add(cards[2 * (round - n / 3) + n / 3 + 1]);
            
            boolean nextRound = false;
            
            // Case 1: 동전 0개 사용
            if (canProceed(hand, hand, n + 1)) {
                nextRound = true;
            } 
            
            // Case 2: 동전 1개 사용
            else if (coin >= 1 && canProceed(hand, draw, n + 1)) {
                coin--;
                nextRound = true;
            }
            
            // Case 3: 동전 2개 사용
            else if (coin >= 2 && canProceed(draw, draw, n + 1)) {
                coin -= 2;
                nextRound = true;
            }
            
            if (nextRound) {
                round++;
            } else {
                break; 
            }
        }
        
        return round - n / 3 + 1;
    }
    
    private boolean canProceed(Set<Integer> set1, Set<Integer> set2, int target) {
        for (int card : set1) {
            if (set2.contains(target - card)) {
                if (set1 == set2 && card == target - card) {
                    continue;
                }
                set1.remove(card);
                set2.remove(target - card);
                return true;
            }
        }
        return false;
    }
}