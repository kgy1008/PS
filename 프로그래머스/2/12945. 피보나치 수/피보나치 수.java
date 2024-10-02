class Solution {
    public int solution(int n) {
        int[] answer = new int[100001];
        answer[1] = 1;
        answer[2] = 1;
        
        for (int i=2; i<n+1; i++) {
            answer[i] = (answer[i-1] + answer[i-2]) % 1234567;
        }
        
        
        return answer[n];
    }
}