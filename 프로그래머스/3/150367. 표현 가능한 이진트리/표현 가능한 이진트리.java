import java.util.ArrayList;
import java.util.List;

class Solution {
    static int[] answer;
    
    public int[] solution(long[] numbers) {
        answer = new int[numbers.length];
        
        for (int i=0; i<numbers.length; i++) {
            long number = numbers[i];
            
            String binary = Long.toBinaryString(number);
            int len = binary.length();
            
            int h = 1;
            while ((1 << h) - 1 < len) {
                h++;
            }
            int treeSize = (1 << h) - 1;

            StringBuilder sb = new StringBuilder();
            for (int t = len; t < treeSize; t++ ) {
                sb.append('0');
            }
            sb.append(binary);
            
            binary = sb.toString();
            len = binary.length();
            
            if (solve(binary, 0, len - 1, false)) {
                answer[i] = 1;
            } else {
                answer[i] = 0;
            }
        } 
        
        return answer;
    }
    
    public boolean solve (String s, int start, int end, boolean isDummy) {
        if (start > end) {
            return true;
        }
        
        int mid = (start + end) / 2;
        char parent = s.charAt(mid);
        
        if (isDummy && parent == '1') {
            return false;
        }
        
        if (parent == '0') {
            isDummy = true;
        }
        
        return solve(s, start, mid - 1, isDummy) 
            && solve(s, mid + 1, end, isDummy);
    }
}