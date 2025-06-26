import java.util.*;

class Solution {
    public int solution(int[][] jobs) {
        // 작업을 요청시간 순으로 정렬
        Arrays.sort(jobs, (a, b) -> a[0] - b[0]);
        
        // 소요시간이 짧은 순으로 우선순위 큐 생성
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        
        int jobIndex = 0;  // 다음에 처리할 작업의 인덱스
        int currentTime = 0;  // 현재 시간
        int totalTime = 0;  // 총 대기시간 + 소요시간
        int completedJobs = 0;  // 완료된 작업 수
        
        while (completedJobs < jobs.length) {
            // 현재 시간까지 요청된 모든 작업을 큐에 추가
            while (jobIndex < jobs.length && jobs[jobIndex][0] <= currentTime) {
                pq.offer(jobs[jobIndex]);
                jobIndex++;
            }
            
            if (pq.isEmpty()) {
                // 처리할 작업이 없으면 다음 작업의 요청시간으로 이동
                currentTime = jobs[jobIndex][0];
            } else {
                // 가장 소요시간이 짧은 작업 처리
                int[] job = pq.poll();
                currentTime += job[1];  // 작업 완료 시간
                totalTime += (currentTime - job[0]);  // 대기시간 + 소요시간
                completedJobs++;
            }
        }
        
        return totalTime / jobs.length;
    }
}