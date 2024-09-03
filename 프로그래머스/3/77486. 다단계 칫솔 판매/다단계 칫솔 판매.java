import java.util.*;

class Solution {

    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        HashMap<String, String> user = new HashMap<>();
        HashMap<String, Integer> sell = new HashMap<>();
        int[] answer = new int[enroll.length];

        for (int i=0; i<enroll.length; i++) {
            user.put(enroll[i], referral[i]);
        }

        for (int i=0; i<seller.length; i++) {
            String s = seller[i];
            int money = amount[i] * 100;
            distributeProfit(s, money, user, sell);
        }

        for (int i=0; i<enroll.length; i++) {
            answer[i] = sell.getOrDefault(enroll[i], 0);
        }

        return answer;
    }

    private void distributeProfit(String s, int money, HashMap<String, String> user, HashMap<String, Integer> sell) {
        if (s.equals("-") || money == 0) return;

        int profitToKeep = money - (money / 10);
        sell.put(s, sell.getOrDefault(s, 0) + profitToKeep);

        distributeProfit(user.get(s), money / 10, user, sell);
    }
}