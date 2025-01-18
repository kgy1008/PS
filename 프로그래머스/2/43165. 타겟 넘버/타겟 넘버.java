class Solution {
    static int answer = 0;
    static int[] nums;
    
    public int solution(int[] numbers, int target) {
        nums = numbers;
        dfs(0, target, 0);
        return answer;
    }
    
    static void dfs(int degree, int target, int sum) {
        if (degree == nums.length) {
            if (sum == target) {
                answer++; 
            }
            return;
        }
        
        dfs(degree+1, target, sum + nums[degree]);
        dfs(degree+1, target, sum - nums[degree]);
    }
}