class Solution {
    public boolean solution(int[][] key, int[][] lock) {
        int M = key.length;
        int N = lock.length;
        
        // Step 1: lock을 확장된 맵에 배치
        int extendedSize = N + (M - 1) * 2;
        int[][] extendedLock = new int[extendedSize][extendedSize];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                extendedLock[i + M - 1][j + M - 1] = lock[i][j];
            }
        }
        
        // Step 2 & 3: key를 4번 회전시키고 모든 위치에 대해 이동시키며 확인
        for (int rotation = 0; rotation < 4; rotation++) {
            key = rotate(key); // 90도 회전
            
            for (int x = 0; x <= extendedSize - M; x++) {
                for (int y = 0; y <= extendedSize - M; y++) {
                    // key를 extendedLock에 끼워 넣기
                    int[][] currentExtendedLock = copyArray(extendedLock);
                    for (int i = 0; i < M; i++) {
                        for (int j = 0; j < M; j++) {
                            currentExtendedLock[x + i][y + j] += key[i][j];
                        }
                    }
                    
                    // Step 4: 자물쇠가 열리는지 확인
                    if (check(currentExtendedLock, M, N)) {
                        return true;
                    }
                }
            }
        }
        
        return false;
    }

    // 90도 회전 함수
    private int[][] rotate(int[][] arr) {
        int n = arr.length;
        int[][] rotated = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                rotated[i][j] = arr[n - 1 - j][i];
            }
        }
        return rotated;
    }
    
    // 배열 복사 함수
    private int[][] copyArray(int[][] arr) {
        int n = arr.length;
        int[][] copied = new int[n][n];
        for(int i = 0; i < n; i++) {
            System.arraycopy(arr[i], 0, copied[i], 0, n);
        }
        return copied;
    }
    
    // 자물쇠가 열렸는지 확인하는 함수
    private boolean check(int[][] arr, int M, int N) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (arr[i + M - 1][j + M - 1] != 1) {
                    return false;
                }
            }
        }
        return true;
    }
}