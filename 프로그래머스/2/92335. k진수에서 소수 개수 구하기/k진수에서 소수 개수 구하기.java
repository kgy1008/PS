import java.util.*;

class Solution {
    static int answer = 0;
    
    public int solution(int n, int k) {
        String s = change(n,k);
        String[] nums = s.split("0");
        
        for (String num : nums) {
            if (!num.isBlank()) {
                validate(num);
            }
        }
        
        return answer;
    }
    
    private static void validate(String target) {
        long num = Long.parseLong(target);
        boolean isAnswer = true;
            
        if (num < 2) {
            return;
        }

        for (long i=2; i*i<=num; i++) {
            if (num % i == 0) { //나누어 떨어지면 
                isAnswer = false;
            }
        }

        if (isAnswer) {
            answer++;
        }
    }
    
    private static String change(int n, int k) {
        StringBuilder sb = new StringBuilder();

        while (n != 0) {
            int remain = n % k;
            sb.append(remain);  
            n /= k;
        }

        return sb.reverse().toString(); 
    }
}