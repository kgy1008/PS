import java.util.*;

class Solution {
    public int solution(int[][] jobs) {
        // 1. 요청 시점(jobs[i][0]) 기준으로 jobs 배열 정렬
        Arrays.sort(jobs, (o1, o2) -> o1[0] - o2[0]);

        // 2. 소요 시간(l) 기준으로 오름차순 정렬하는 우선순위 큐
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);

        int answer = 0;
        int time = 0;
        int jobIndex = 0;
        int count = 0;

        // 3. 모든 작업을 처리할 때까지 반복
        while (count < jobs.length) {
            // 현재 시간(time) 이전에 요청된 모든 작업을 우선순위 큐에 추가
            while (jobIndex < jobs.length && jobs[jobIndex][0] <= time) {
                pq.offer(jobs[jobIndex]);
                jobIndex++;
            }

            // 우선순위 큐에 작업이 있으면 처리
            if (!pq.isEmpty()) {
                int[] job = pq.poll();
                int requestTime = job[0];
                int duration = job[1];

                time += duration; // 현재 시간 업데이트
                answer += (time - requestTime); // 총 대기 시간 누적
                count++;
            } else {
                // 우선순위 큐가 비어있고, 아직 처리할 작업이 남은 경우
                // CPU가 유휴 상태이므로, 다음 작업의 요청 시간으로 현재 시간을 이동
                if (jobIndex < jobs.length) {
                    time = jobs[jobIndex][0];
                }
            }
        }
        
        return answer / jobs.length;
    }
}