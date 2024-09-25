class Solution {
    int[] answer;
    int[] apeach;
    int max = 0;
    
    public int[] solution(int n, int[] info) {
        apeach = info;
        backtrack(n, 0, new int[11]);
        return max == 0 ? new int[]{-1} : answer;
    }
    
        
    private void backtrack(int n, int idx, int[] ryan) {
        if (n == 0) {
            getAnswer(ryan);
            return;
        }
        
        for (int i = idx; i <= 10; i++) {
            int cnt = Math.min(n, apeach[i] + 1);
            ryan[i] = cnt;
            backtrack(n-cnt, i+1, ryan);
            ryan[i] = 0;
        }
    }
    
    private int getScore(int[] ryan) {
        int score = 0;
        for (int i=0; i<=10; i++) {
            if (ryan[i] + apeach[i] > 0) {
                if (ryan[i] > apeach[i]) score += (10-i);
                else score -= (10-i);
            }
        }
        return score;
    }
    
    private void getAnswer(int[] ryan) {
        int score = getScore(ryan);
        
        if (max < score) {
            max = score;
            answer = ryan.clone();
        }
        
        if (max == score && max > 0) {
            for (int i = 10; i>= 0; i--) {
                if (answer[i] != ryan[i]) {
                    if (answer[i] < ryan[i]) {
                        answer = ryan.clone();
                    }
                    break;
                }
            }
        }
    }
}