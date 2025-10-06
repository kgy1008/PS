import java.util.*;

class Solution {
    public int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];
        
        for (int i=0; i<numbers.length; i++) {
            long number = numbers[i];
            String binary = Long.toBinaryString(number);
            int len = binary.length();
            
            int targetLen = 1;
            while (targetLen < len) {
                targetLen = targetLen * 2 + 1;
            }
            
            int leftMove = targetLen - len;
            
            for (int m=0; m<leftMove; m++) {
                binary = "0" + binary;
            }
            
            if (canMake(binary)) {
                answer[i] = 1;
            } else {
                answer[i] = 0;
            }
        }
        
        return answer;
    }
    
    private static boolean canMake(String str) {
        if (str.length() == 1) {
            return true;
        }
        
        int len = str.length();
        int mid = len / 2;

        String left = str.substring(0, mid);
        String right = str.substring(mid+1);
        
        if (str.charAt(mid) == '0') { // 부모 노드 존재 X -> 자식 노드도 존재하면 안됨
            if (left.charAt(mid/2) == '1' || right.charAt(mid/2) == '1') {
                return false;
            }
        }
        
        return canMake(left) && canMake(right);
    }
}