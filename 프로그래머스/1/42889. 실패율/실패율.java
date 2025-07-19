import java.util.*;

class Solution {
    public int[] solution(int N, int[] stages) {
        HashMap<Integer, Integer> map = new HashMap<>();
        Map<Double, List<Integer>> failure = new TreeMap<>((o1, o2) -> Double.compare(o2, o1)); // 내림차순 정렬
        int people = stages.length;

        // 각 스테이지에 도달한 사람 수 기록
        for (int stage : stages) {
            map.put(stage, map.getOrDefault(stage, 0) + 1);
        }

        // 실패율 계산
        for (int i = 1; i <= N; i++) {
            int value = map.getOrDefault(i, 0);
            double fail = 0.0;
            if (people > 0) {
                fail = (double) value / people;
            }
            List<Integer> list = failure.getOrDefault(fail, new ArrayList<>());
            list.add(i); 
            failure.put(fail, list); 
            people -= value;
        }

        // 실패율 내림차순 정렬 후 스테이지 번호 수집
        List<Integer> answer = new ArrayList<>();
        for (Map.Entry<Double, List<Integer>> entry : failure.entrySet()) {
            List<Integer> values = entry.getValue();
            Collections.sort(values); // 동일 실패율 시 오름차순 정렬
            for (int v : values) {
                answer.add(v);
            }
        }

        // List -> int[] 변환
        return answer.stream().mapToInt(i -> i).toArray();
    }
}
