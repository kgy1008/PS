import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] nums = new int[N];
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }
        br.close();

        int start = 0;
        int end = N - 1;
        int min = 2_000_000_001;

        int left = start;
        int right = end;

        while (start < end) {
            int mix = nums[start] + nums[end];
            if (mix == 0) {
                System.out.println(nums[start] + " " + nums[end]);
                return;
            }

            if (Math.min(Math.abs(min), Math.abs(mix)) == Math.abs(mix)) {
                min = Math.min(Math.abs(min), Math.abs(mix));
                left = start;
                right = end;
            }

            if (mix < 0) {
                start++;
            } else {
                end--;
            }
        }

        System.out.println(nums[left] + " " + nums[right]);
    }
}
