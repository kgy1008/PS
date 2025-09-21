import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // 접시의 수
        int d = Integer.parseInt(st.nextToken()); // 초밥의 가짓수
        int k = Integer.parseInt(st.nextToken()); // 연속해서 먹는 접시의 수
        int c = Integer.parseInt(st.nextToken()); // 쿠폰 번호

        int[] food = new int[n];
        for (int i = 0; i < n; i++) {
            food[i] = Integer.parseInt(br.readLine());
        }

        // 초밥 종류별 개수를 저장하는 배열
        int[] counts = new int[d + 1];
        int distinctCount = 0;
        int maxCount = 0;

        // 1. 초기 윈도우 설정 (0부터 k-1까지)
        for (int i = 0; i < k; i++) {
            if (counts[food[i]] == 0) {
                distinctCount++;
            }
            counts[food[i]]++;
        }

        // 초기 윈도우에서 쿠폰 초밥을 포함한 종류 수 계산
        if (counts[c] == 0) {
            maxCount = distinctCount + 1;
        } else {
            maxCount = distinctCount;
        }

        // 2. 윈도우 이동 (슬라이딩)
        for (int i = 1; i < n; i++) {
            // 윈도우에서 맨 왼쪽 초밥 제거
            counts[food[i - 1]]--;
            if (counts[food[i - 1]] == 0) {
                distinctCount--;
            }

            // 윈도우에 맨 오른쪽 초밥 추가 (원형 배열이므로 % n 사용)
            int newFoodIndex = (i + k - 1) % n;
            if (counts[food[newFoodIndex]] == 0) {
                distinctCount++;
            }
            counts[food[newFoodIndex]]++;

            // 현재 윈도우에서 쿠폰 초밥을 포함한 종류 수 계산
            int currentCount;
            if (counts[c] == 0) {
                currentCount = distinctCount + 1;
            } else {
                currentCount = distinctCount;
            }

            // 최대값 갱신
            if (currentCount > maxCount) {
                maxCount = currentCount;
            }
        }

        System.out.println(maxCount);
    }
}
