class Solution {
    boolean[] width;
    boolean[] diagonal1;
    boolean[] diagonal2;
    
    public int solution(int n) {
        width = new boolean[n];
        diagonal1 = new boolean[n*2];
        diagonal2 = new boolean[n*2];
        return getAnswer(0, n);
    }
    
    private int getAnswer(int y, int n) {
        int ans = 0;
        if (y == n) ans++;
        else {
            for (int i = 0; i<n; i++) {
                if (width[i] || diagonal1[i+y] || diagonal2[i-y+n]) continue;
                width[i] = diagonal1[i+y] = diagonal2[i-y+n] = true;
                ans += getAnswer(y+1, n);
                width[i] = diagonal1[i+y] = diagonal2[i-y+n] = false;
            }
        }
        return ans;
    }
}