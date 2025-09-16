class Solution {
    public int solution(int[] stones, int k) {
        int min = 1; 
        int max = 200_000_000;
        int answer = 0;
        
        while (min <= max) {
            int mid = (min + max) / 2;
            int cnt = 0; 
            boolean canCross = true;
            
            for (int stone : stones) {
                if (stone - mid < 0) {
                    cnt++;
                } else {
                    cnt = 0;
                }
                
                if (cnt >= k) {
                    canCross = false;
                    break;
                }
            }
            
            if (canCross) {
                answer = mid;
                min = mid + 1;
            } else {
                max = mid - 1;
            }
        }
        return answer;
    }
}