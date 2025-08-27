import java.util.*;

class Solution {
    private static int maxPeople = 0;
    private static int maxProfit = 0;
    private static int[] discount = new int[]{10, 20, 30, 40};
    
    public int[] solution(int[][] users, int[] emoticons) {
        dfs(0, new int[emoticons.length], users, emoticons);
        return new int[]{maxPeople, maxProfit};
    }
    
    private void dfs(int emoticonIndex, int[] currentDiscounts, int[][] users, int[] emoticons) {
        // 모든 이모티콘에 대한 할인율을 정했을 때
        if (emoticonIndex == emoticons.length) {
            calculate(currentDiscounts, users, emoticons);
            return;
        }
        
        // 각 이모티콘에 대해 4가지 할인율을 모두 시도
        for (int d : discount) {
            currentDiscounts[emoticonIndex] = d;
            dfs(emoticonIndex + 1, currentDiscounts, users, emoticons);
        }
    }
    
    private void calculate(int[] currentDiscounts, int[][] users, int[] emoticons) {
        int people = 0;
        int profit = 0;
        
        for (int[] user : users) {
            int rate = user[0];
            int price = user[1];
            int pay = 0;
            
            for (int i = 0; i < emoticons.length; i++) {
                if (rate <= currentDiscounts[i]) {
                    pay += (emoticons[i] * (100 - currentDiscounts[i])) / 100;
                }
            }
            
            if (pay >= price) {
                people++;
            } else {
                profit += pay;
            }
        }
        
        if (people > maxPeople) {
            maxPeople = people;
            maxProfit = profit;
        } else if (people == maxPeople && profit > maxProfit) {
            maxProfit = profit;
        }
    }
}