class Solution {
    static int answer = 0;
    static int sum = 0;
    static boolean[] visited;
    static int[] nums;
    
    public int solution(int[] numbers, int target) {
        visited = new boolean[numbers.length];
        nums = numbers;
        dfs(0, target);
        return answer;
    }
    
    static void dfs(int degree, int target) {
        if (degree == nums.length) {
            if (sum == target) {
                answer++; 
            }
            return;
        }
        
        for (int i=0; i<2; i++) {
            if (i == 0) {
                sum += nums[degree];
                dfs(degree+1, target);
                sum -= nums[degree];
            } else {
                sum += nums[degree] * -1;
                dfs(degree+1, target);
                sum -= nums[degree] * -1;
            }
        }
    }
}