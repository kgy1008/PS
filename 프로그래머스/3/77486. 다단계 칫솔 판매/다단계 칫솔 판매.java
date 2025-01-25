import java.util.*;

class Solution {
    static Map<String, Integer> profit = new HashMap<>();
    static Map<String, String> parent = new HashMap<>();
    
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {        
        for (int i=0; i<enroll.length; i++) {
            parent.put(enroll[i], referral[i]); // enroll[i]의 부모는 referall[i]
            profit.put(enroll[i], 0);
        }
        
        for (int i=0; i<seller.length; i++) {
            int money = amount[i] * 100;
            backtrack(seller[i], money);
        }
        
        int[] answer = new int[enroll.length];
        for (int i=0; i<enroll.length; i++) {
            answer[i] = profit.get(enroll[i]);
        } 
        return answer;
    }
    
    private static void backtrack(String person, int money) {
        if (person.equals("-") || money < 10) {
            profit.put(person, profit.getOrDefault(person, 0) + money);
            return;
        }
        int minus = (int) Math.floor(money * 0.1);
        int total = money - minus;
        profit.put(person, profit.get(person) + total);
        backtrack(parent.get(person), minus);
    }
}