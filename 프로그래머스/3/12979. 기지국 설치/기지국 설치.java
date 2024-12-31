class Solution {
    public int solution(int n, int[] stations, int w) {
        double maxDistance = 1.0 + 2*w;
        int start = 1, end = 1;
        int answer = 0;
        
        for (int s : stations) {
            end = s - w;
            if (start < end) {
                int uncovered = end - start; 
                answer += (int) Math.ceil(uncovered / maxDistance); 
            }
            start = s + w + 1;
        }
        
        
        if (start <= n) {
            int uncovered = n - start + 1;
            answer += (int) Math.ceil(uncovered / maxDistance);
        }
        
        return answer;
    }
}