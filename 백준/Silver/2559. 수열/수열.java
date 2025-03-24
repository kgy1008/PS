import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] nums = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        int max = 0;
        for (int i = 0; i < k; i++) {
            max += nums[i];
        }

        int end = k;
        int sum = max;
        for (int i = 0; i < n - k; i++) {
            sum -= nums[i];
            sum += nums[end++];
            max = Math.max(max, sum);
        }
        System.out.println(max);
    }
}
