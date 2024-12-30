import java.util.*;

class Solution {
    public int solution(String[] want, int[] number, String[] discount) {
        Map<String, Integer> target = new HashMap<>();
        for (int i = 0; i < want.length; i++) {
            target.put(want[i], number[i]);
        }

        Map<String, Integer> basket = new HashMap<>();
        int answer = 0;

        // 첫 10일간 품목 계산
        for (int i = 0; i < 10; i++) {
            basket.put(discount[i], basket.getOrDefault(discount[i], 0) + 1);
        }
        if (basket.equals(target)) {
            answer++;
        }

        // 슬라이딩 윈도우로 한 칸씩 이동
        for (int i = 10; i < discount.length; i++) {
            // 이전 품목 제거
            String oldItem = discount[i - 10];
            basket.put(oldItem, basket.get(oldItem) - 1);
            if (basket.get(oldItem) == 0) {
                basket.remove(oldItem);
            }

            // 새로운 품목 추가
            String newItem = discount[i];
            basket.put(newItem, basket.getOrDefault(newItem, 0) + 1);

            // 현재 상태 비교
            if (basket.equals(target)) {
                answer++;
            }
        }

        return answer;
    }
}
