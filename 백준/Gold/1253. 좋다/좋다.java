import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int[] nums;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        nums = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }
        br.close();

        Arrays.sort(nums);
        int answer = 0;

        for (int i = 0; i < N; i++) {
            if (isGoodNumber(i)) {
                answer++;
            }
        }
        System.out.println(answer);
    }

    private static boolean isGoodNumber(int index) {
        int left = 0, right = nums.length - 1;
        int target = nums[index];

        while (left < right) {
            if (left == index) {
                left++;
            }
            if (right == index) {
                right--;
            }
            
            if (left >= right) {
                break;
            }

            int sum = nums[left] + nums[right];

            if (sum == target) {
                return true;
            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }
        return false;
    }
}
