class Solution {
    public int solution(int[] queue1, int[] queue2) {
        long sum1 = 0;
        long sum2 = 0;
        int n = queue1.length;
        
        int[] mergedQueue = new int[n * 2];
        for (int i = 0; i < n; i++) {
            mergedQueue[i] = queue1[i];
            sum1 += queue1[i];
        }
        for (int i = 0; i < n; i++) {
            mergedQueue[i + n] = queue2[i];
            sum2 += queue2[i];
        }
        
        long totalSum = sum1 + sum2;
        if (totalSum % 2 != 0) {
            return -1;
        }
        
        long target = totalSum / 2;
        
        int left = 0;
        int right = n;
        long currentSum = sum1;
        int count = 0;
        
        while (right < n * 2 && left <= right) {
            if (currentSum == target) {
                return count;
            }
            
            if (currentSum < target) {
                currentSum += mergedQueue[right];
                right++;
            } else { // currentSum > target
                currentSum -= mergedQueue[left];
                left++;
            }
            count++;
        }
        
        return -1;
    }
}