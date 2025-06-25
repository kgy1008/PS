import java.util.*;

class Solution {
    private static HashMap<Integer, HashMap<String, Integer>> map = new HashMap<>();

    public String[] solution(String[] orders, int[] course) {
        // course에 해당하는 길이별로 map 초기화
        for (int c : course) {
            map.put(c, new HashMap<>());
        }

        for (int c : course) {
            HashMap<String, Integer> combi = map.get(c);
            for (String order : orders) {
                if (order.length() >= c) {
                    char[] arr = order.toCharArray();
                    Arrays.sort(arr); // 사전순 조합 생성을 위해 정렬
                    getCombination(0, c, combi, arr, new StringBuilder());
                }
            }
        }

        List<String> answer = new ArrayList<>();
        for (int c : course) {
            HashMap<String, Integer> tmp = map.get(c);

            int max = 0;
            for (int value : tmp.values()) {
                max = Math.max(max, value);
            }

            for (String key : tmp.keySet()) {
                int v = tmp.get(key);
                if (max > 1 && v == max) {
                    answer.add(key);
                }
            }
        }

        Collections.sort(answer);
        return answer.toArray(new String[0]);
    }

    private void getCombination(int idx, int count, HashMap<String, Integer> combi, char[] order, StringBuilder sb) {
        if (sb.length() == count) {
            String target = sb.toString();
            combi.put(target, combi.getOrDefault(target, 0) + 1);
            return;
        }

        for (int i = idx; i < order.length; i++) {
            sb.append(order[i]);
            getCombination(i + 1, count, combi, order, sb); // 조합을 위해 i + 1 사용
            sb.deleteCharAt(sb.length() - 1); // 마지막 문자 제거
        }
    }
}
