import java.util.*;

class Solution {
    public int solution(int coin, int[] cards) {
        int n = cards.length;
        int round = 1;
        
        Set<Integer> hand = new HashSet<>(); // 최초 부여 받은 카드
        Set<Integer> draw = new HashSet<>(); // 새로 뽑은 카드
        
        // 1. 카드 부여 -> n/3개
        for (int i = 0; i < n / 3; i++) {
            hand.add(cards[i]);
        }
        
        // 2. 라운드 진행
        for (int i = 0; i < (n - n / 3) / 2; i++) {
            // 매 라운드 2장의 카드를 새로 뽑음
            draw.add(cards[n / 3 + 2 * i]);
            draw.add(cards[n / 3 + 2 * i + 1]);
            
            boolean canContinue = false;
            
            // 3. 카드 조합 찾기 (그리디하게 동전 덜 쓰는 순서)
            
            // 3-1. 동전 0개: 최초로 부여받은 카드 2장으로 n+1 만들기
            if (findAndUsePair(hand, hand, n + 1)) {
                canContinue = true;
            }
            // 3-2. 동전 1개: 손패 1장 + 뽑은 카드 1장으로 n+1 만들기
            else if (coin >= 1 && findAndUsePair(hand, draw, n + 1)) {
                coin--;
                canContinue = true;
            }
            // 3-3. 동전 2개: 뽑은 카드 2장으로 n+1 만들기
            else if (coin >= 2 && findAndUsePair(draw, draw, n + 1)) {
                coin -= 2;
                canContinue = true;
            }
            
            // 4. 다음 라운드 진행 여부
            if (canContinue) {
                round++;
            } else {
                break; // 다음 라운드로 진행할 수 없으면 게임 종료
            }
        }
        
        return round; 
    }


    private boolean findAndUsePair(Set<Integer> set1, Set<Integer> set2, int target) {
        List<Integer> list1 = new ArrayList<>(set1);
        
        for (int card1 : list1) {
            int card2 = target - card1;
            
            // set1과 set2가 같은 경우, 같은 카드를 두 번 사용하면 안됨.
            if (set1 == set2 && card1 == card2) {
                continue;
            }
            
            if (set2.contains(card2)) {
                set1.remove(card1);
                set2.remove(card2);
                return true;
            }
        }
        return false;
    }
}