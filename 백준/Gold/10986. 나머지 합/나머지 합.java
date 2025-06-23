import java.io.BufferedReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new java.io.InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 숫자의 개수
        int m = Integer.parseInt(st.nextToken()); // 나누는 수

        HashMap<Integer, Integer> map = new HashMap<>();

        int[] nums = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(st.nextToken()); // 숫자 입력
            if (i == 0) {
                nums[i] = num % m; // 첫 번째 숫자는 바로 나머지로 저장
            } else {
                nums[i] = (nums[i - 1] + num) % m; // 이전 누적 합에 현재 숫자를 더한 후 나머지
            }
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1); // 나머지의 개수 저장
        }

        long result = map.getOrDefault(0, 0);

        for (int key : map.keySet()) {
            long v = map.get(key);

            if (v >= 2) {
                result += (v * (v - 1)) / 2; // 조합 계산
            }
        }

        System.out.println(result);
    }
}
