import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // 전체 용액의 수

        long[] nums = new long[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(nums);

        long min = Long.MAX_VALUE;
        long[] answer = new long[3];

        for (int i = 0; i < n - 2; i++) {
            int fixed = i;
            int left = i + 1;
            int right = n - 1;

            while (left < right) {
                long sum = nums[fixed] + nums[left] + nums[right];

                if (Math.abs(sum) < min) {
                    min = Math.abs(sum);
                    answer[0] = nums[fixed];
                    answer[1] = nums[left];
                    answer[2] = nums[right];
                } else {
                    if (sum < 0) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
        }

        Arrays.sort(answer);
        System.out.println(answer[0] + " " + answer[1] + " " + answer[2]);
    }
}
