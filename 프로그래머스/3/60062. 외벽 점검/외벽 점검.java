import java.util.*;

class Solution {
    static int answer = -1;
    static boolean canSolve = false; // 전부 점검 가능한지 여부
    static int[] weak;
    static int[] dist;
    
    public int solution(int n, int[] weak, int[] dist) {
        this.weak = new int[weak.length*2];
        this.dist = dist;
        int totalPerson = dist.length;
        
        for (int i=0; i<weak.length; i++) {
            this.weak[i] = weak[i];
            this.weak[weak.length + i] = n + weak[i];
        }
        
        for (int i=1; i<=totalPerson; i++) { // 만들 수 있는 친구 그룹 순열 생성
            boolean[] visited = new boolean[totalPerson];
            combination(i, new ArrayList<>(), visited);
            if (canSolve) {
                break;
            }
        }

        return answer;
    }
    
    private static void combination(int person, List<Integer> list, boolean[] visited) {
        if (person == 0) { // 조합 완료
            // 취약 지점 탐방
            solve(list);
            return;
        }
        
        for (int i=0; i<dist.length; i++) {
            if (!visited[i]) {
                list.add(dist[i]);
                visited[i] = true;
                
                combination(person-1, list, visited);
                
                list.remove(list.size()-1);
                visited[i] = false;
            }
        }
    }
    
    private static void solve(List<Integer> person) {
        // 취약 지점의 시작점을 모두 순회하며 확인
        for (int i = 0; i < weak.length / 2; i++) {
            // 현재 시작점부터 탐색
            int startPointIndex = i;
            int personIndex = 0;

            // 모든 취약 지점을 점검할 때까지 반복
            while (startPointIndex < i + weak.length / 2 && personIndex < person.size()) {
                int currentStartWeakPoint = weak[startPointIndex];
                int currentEndCoverage = currentStartWeakPoint + person.get(personIndex);

                // 현재 친구가 커버할 수 있는 취약 지점들을 모두 건너뜀
                int nextWeakPointIndex = startPointIndex;
                while (nextWeakPointIndex < i + weak.length / 2 && weak[nextWeakPointIndex] <= currentEndCoverage) {
                    nextWeakPointIndex++;
                }

                // 다음 시작 지점으로 이동
                startPointIndex = nextWeakPointIndex;
                personIndex++;
            }

            // 모든 취약 지점을 점검할 수 있다면
            if (startPointIndex >= i + weak.length / 2) {
                canSolve = true;
                answer = person.size();
                return;
            }
        }
    }
}