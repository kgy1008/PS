import java.util.*;

class Solution {
    // static 변수들을 사용하여 재귀 호출 간에 상태를 공유합니다.
    static int n; // 전체 화살 수
    static int[] apeachInfo; // 어피치의 화살 정보
    static int[] ryanInfo; // 라이언의 화살 정보를 기록할 배열
    static int[] answer = {-1}; // 최종 정답 배열 (라이언이 지면 [-1])
    static int maxDiff = -1; // 최대 점수 차이 (초기값 -1로 설정)

    public int[] solution(int n, int[] info) {
        this.n = n;
        this.apeachInfo = info;
        this.ryanInfo = new int[11];
        
        // 여러 테스트 케이스를 위해 변수를 초기화합니다.
        maxDiff = -1;
        answer = new int[]{-1};
        
        // DFS 탐색 시작: 10점 과녁 (인덱스 0)부터 시작, 사용한 화살 0개
        dfs(0, 0); 
        
        // 최종적으로 라이언이 이길 수 있는 방법이 없었다면 [-1]을 반환합니다.
        if (maxDiff <= 0) {
            return new int[]{-1};
        }
        
        return answer;
    }

    // DFS (깊이 우선 탐색) 함수
    private void dfs(int index, int arrowsUsed) {
        // 모든 과녁 (0점까지)에 대한 결정을 마친 경우
        if (index == 11) {
            // 남은 화살이 있다면 모두 0점 과녁에 쏩니다.
            if (arrowsUsed <= n) {
                ryanInfo[10] += (n - arrowsUsed);
                int currentDiff = calculateScoreDiff();

                // 현재 점수 차이가 최대 점수 차이보다 큰지 확인합니다.
                if (currentDiff > 0 && currentDiff >= maxDiff) {
                    if (currentDiff > maxDiff) {
                        maxDiff = currentDiff;
                        answer = ryanInfo.clone(); // 정답 갱신 (깊은 복사)
                    } else { // 점수 차이가 같을 경우, 동점 처리 규칙 적용
                        if (isBetter(answer, ryanInfo)) {
                            answer = ryanInfo.clone();
                        }
                    }
                }
                // 백트래킹: 0점 과녁에 추가했던 화살을 되돌립니다.
                ryanInfo[10] -= (n - arrowsUsed);
            }
            return;
        }

        // 현재 과녁에 화살을 쏘는 경우 (승리)
        int requiredArrows = apeachInfo[index] + 1;
        if (arrowsUsed + requiredArrows <= n) {
            ryanInfo[index] = requiredArrows;
            dfs(index + 1, arrowsUsed + requiredArrows);
            ryanInfo[index] = 0; // 백트래킹
        }

        // 현재 과녁에 화살을 쏘지 않는 경우 (포기)
        ryanInfo[index] = 0;
        dfs(index + 1, arrowsUsed);
    }
    
    // 점수 차이를 계산하는 헬퍼 함수
    private int calculateScoreDiff() {
        int ryanScore = 0;
        int apeachScore = 0;
        for (int i = 0; i < 11; i++) {
            int score = 10 - i;
            if (ryanInfo[i] > apeachInfo[i]) {
                ryanScore += score;
            } else if (apeachInfo[i] > 0) {
                apeachScore += score;
            }
        }
        return ryanScore - apeachScore;
    }
    
    // 동점일 경우, 낮은 점수 과녁에 화살이 더 많은지 확인하는 헬퍼 함수
    private boolean isBetter(int[] oldAnswer, int[] newAnswer) {
        for (int i = 10; i >= 0; i--) { // 0점부터 비교 시작
            if (newAnswer[i] > oldAnswer[i]) {
                return true;
            } else if (newAnswer[i] < oldAnswer[i]) {
                return false;
            }
        }
        return false;
    }
}