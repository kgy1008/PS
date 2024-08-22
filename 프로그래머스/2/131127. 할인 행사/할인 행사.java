import java.util.*;

class Solution {
    public int solution(String[] want, int[] number, String[] discount) {
        HashMap<String, Integer> product = new HashMap<>();
        int day = 0;
        
        for (int i = 0; i < want.length; i++) {
            product.put(want[i], number[i]);
        }
        
        for (int i = 0; i <= discount.length - 10; i++) {
            HashMap<String, Integer> dis = new HashMap<>();
            
            for (int j = i; j < i + 10; j++) {
                dis.put(discount[j], dis.getOrDefault(discount[j], 0) + 1);
            }

            if (dis.equals(product)) {
                day++;
            }
        }
        
        return day;
    }
}
