import java.util.*;

class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        int answer = n - lost.length;
        
        Arrays.sort(reserve);
        Arrays.sort(lost);
        
        for (int j=0; j<lost.length; j++) {
            for (int i=0; i<reserve.length; i++) {
                if (reserve[i] == lost[j]) {
                    answer++;
                    reserve[i] = -1;
                    lost[j] = -1;
                    break;
                }
            }
        }
        
        for (int l : lost) {
            for (int i=0; i<reserve.length; i++) {
                if (l != -1) {
                    if (reserve[i] == l-1 || reserve[i] == l+1) {
                        reserve[i] = -1;
                        answer++;
                        break;
                    }
                }
            }
        }
        return answer;
    }
}