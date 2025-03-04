import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] nums = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(nums);
        int answer = 200_000_001;
        int left = 0, right = n - 1;

        while (left < right) {
            int mix = nums[left] + nums[right];
            if (Math.abs(mix) < Math.abs(answer)) {
                answer = mix;
            }

            if (answer == 0) {
                break;
            }

            if (mix < 0) {
                left++;
            } else {
                right--;
            }
        }

        System.out.println(answer);
    }
}
