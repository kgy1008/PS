import java.util.*;

class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long distance = 0L;
        int end1 = n - 1, end2 = n - 1;

        // 배달과 수거의 끝 지점 찾기
        while (end1 >= 0 && deliveries[end1] == 0) {
            end1--;
        }
        while (end2 >= 0 && pickups[end2] == 0) {
            end2--;
        }

        while (end1 >= 0 || end2 >= 0) {
            // 가장 먼 곳까지 가야 함 (왕복이므로 *2를 해줌)
            distance += (Math.max(end1, end2) + 1) * 2;

            // 배달 물량 처리
            int give = cap;
            while (give >= 0 && end1 >= 0) {
                give -= deliveries[end1];
                if (give < 0) {
                    deliveries[end1] = give * -1;
                    break;
                } else {
                    end1--;
                }
            }

            // 수거 물량 처리
            int get = cap;
            while (get >= 0 && end2 >= 0) {
                get -= pickups[end2];
                if (get < 0) {
                    pickups[end2] = get * -1;
                    break;
                } else {
                    end2--;
                }
            }
        }

        return distance;
    }
}
