import java.util.*;

class Solution {
    public int solution(int coin, int[] cards) {
        int n = cards.length;
        int round = 0;
        
        Set<Integer> hand = new HashSet<>(); // 손패
        Set<Integer> draw = new HashSet<>(); // 새로 뽑은 카드
        
        // 1. 초기 손패 설정: 첫 n/3장의 카드를 손에 넣습니다.
        for (int i = 0; i < n / 3; i++) {
            hand.add(cards[i]);
        }
        
        // 2. 라운드 진행
        for (int i = 0; i < (n - n / 3) / 2; i++) {
            // 매 라운드 시작 시, 2장의 카드를 새로 뽑습니다.
            draw.add(cards[n / 3 + 2 * i]);
            draw.add(cards[n / 3 + 2 * i + 1]);
            
            boolean canContinue = false;
            
            // 3. 카드 조합 찾기 (그리디하게 동전 덜 쓰는 순서)
            
            // 3-1. 동전 0개: 손패에 있는 카드 2장으로 n+1 만들기
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
        
        return round + 1; // 0부터 시작했으므로 +1
    }

    /**
     * 두 카드 셋에서 합이 target이 되는 조합을 찾고, 사용한 카드를 제거합니다.
     *
     * @param set1 첫 번째 카드 셋
     * @param set2 두 번째 카드 셋
     * @param target 목표 합 (n + 1)
     * @return 조합을 찾았으면 true, 아니면 false
     */
    private boolean findAndUsePair(Set<Integer> set1, Set<Integer> set2, int target) {
        List<Integer> list1 = new ArrayList<>(set1);
        
        for (int card1 : list1) {
            int card2 = target - card1;
            
            // set1과 set2가 같은 경우, 같은 카드를 두 번 사용하면 안 됩니다.
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