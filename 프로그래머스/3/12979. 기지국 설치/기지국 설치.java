class Solution {
    public int solution(int n, int[] stations, int w) {
        double maxDistance = 2 * w + 1; // 한 기지국이 커버할 수 있는 최대 거리
        int start = 1; // 아파트 범위의 시작
        int answer = 0;

        for (int station : stations) {
            int end = station - w; // 현재 기지국의 커버리지 시작
            if (start < end) {
                int uncovered = end - start; // 커버되지 않는 구간 길이
                answer += (int) Math.ceil(uncovered / maxDistance); // 기지국 추가
            }
            start = station + w + 1; // 다음 구간 시작
        }

        // 마지막 기지국 이후 커버되지 않는 구간 처리
        if (start <= n) {
            int uncovered = n - start + 1;
            answer += (int) Math.ceil(uncovered / maxDistance);
        }

        return answer;
    }
}
