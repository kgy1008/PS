class Solution {
    static boolean[] dialog1;
    static boolean[] dialog2;
    static boolean[] rkfh;
    static int answer = 0;
    
    public int solution(int n) {
        dialog1 = new boolean[2*n];
        dialog2 = new boolean[2*n];
        rkfh = new boolean[n];
        
        solve(0, n);
        return answer;
    }
    
    private static void solve(int row, int n) {
        if (row == n) {
            answer++;
            return;
        }
        for (int i=0; i<n; i++) {
            if (isValid(row, i, n)) {
                dialog1[row+i] = true;
                dialog2[row-i+n] = true;
                rkfh[i] = true;
                solve(row+1, n);
                dialog1[row+i] = false;
                dialog2[row-i+n] = false;
                rkfh[i] = false;
            }
        }
    }
    
    private static boolean isValid(int row, int col, int n) {
        if (dialog1[row+col]) {
            return false;
        }
        if (dialog2[row-col+n]) {
            return false;
        }
        if (rkfh[col]) {
            return false;
        }
        return true;
    }
}