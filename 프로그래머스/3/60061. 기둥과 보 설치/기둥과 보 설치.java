import java.util.*;

class Solution {

    // 현재 구조물이 유효한지 검사하는 함수
    // 기둥, 보 각각의 규칙을 '모든' 설치된 구조물에 대해 검사합니다.
    private boolean isPossible(int n, boolean[][][] built) {
        for (int x = 0; x <= n; x++) {
            for (int y = 0; y <= n; y++) {
                // 기둥이 설치되어 있다면
                if (built[x][y][0]) {
                    // 기둥 설치 조건 확인
                    // 1. 바닥 위에 있거나
                    // 2. 아래에 기둥이 있거나
                    // 3. (x, y) 또는 (x-1, y)에 보가 있어야 함
                    boolean onFloor = y == 0;
                    boolean hasPillarBelow = (y > 0) && built[x][y - 1][0];
                    boolean hasBeamAtLeft = (x > 0) && built[x - 1][y][1];
                    boolean hasBeamAtRight = built[x][y][1];
                    
                    if (!onFloor && !hasPillarBelow && !hasBeamAtLeft && !hasBeamAtRight) {
                        return false;
                    }
                }
                
                // 보가 설치되어 있다면
                if (built[x][y][1]) {
                    // 보 설치 조건 확인
                    // 1. 아래에 기둥이 있거나 (왼쪽 or 오른쪽 끝)
                    // 2. 양쪽 끝이 다른 보와 연결되어야 함
                    boolean hasPillarBelow = (y > 0) && (built[x][y - 1][0] || (x < n && built[x + 1][y - 1][0]));
                    boolean hasBeamsOnBothSides = (x > 0) && (x < n) && built[x - 1][y][1] && built[x + 1][y][1];

                    if (!hasPillarBelow && !hasBeamsOnBothSides) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public int[][] solution(int n, int[][] build_frame) {
        // [x좌표][y좌표][구조물 타입(0:기둥, 1:보)]
        boolean[][][] built = new boolean[n + 1][n + 1][2];
        
        for (int[] frame : build_frame) {
            int x = frame[0];
            int y = frame[1];
            int a = frame[2]; // 0: 기둥, 1: 보
            int b = frame[3]; // 0: 삭제, 1: 설치
            
            if (b == 1) { // 설치
                built[x][y][a] = true; // 일단 설치
                if (!isPossible(n, built)) {
                    built[x][y][a] = false; // 불가능하면 되돌리기
                }
            } else { // 삭제
                built[x][y][a] = false; // 일단 삭제
                if (!isPossible(n, built)) {
                    built[x][y][a] = true; // 불가능하면 되돌리기
                }
            }
        }
        
        List<int[]> result = new ArrayList<>();
        for (int x = 0; x <= n; x++) {
            for (int y = 0; y <= n; y++) {
                if (built[x][y][0]) { // 기둥
                    result.add(new int[]{x, y, 0});
                }
                if (built[x][y][1]) { // 보
                    result.add(new int[]{x, y, 1});
                }
            }
        }
        
        // 정렬은 문제 조건에 따라 이미 이중 for문으로 처리됨
        // x 오름차순 -> y 오름차순 -> 종류 오름차순
        
        return result.toArray(new int[result.size()][]);
    }
}
