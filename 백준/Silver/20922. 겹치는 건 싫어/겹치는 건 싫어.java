import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] nums = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        int start= 0, end = 0;
        int answer = Integer.MIN_VALUE;
        int[] dp = new int[100001];
        while (end < n) {
            while (end < n && dp[nums[end]] + 1 <= k) {
                dp[nums[end]]++;
                end++;
            }
            int len = end - start;
            answer = Math.max(answer, len);
            dp[nums[start]]--;
            start++;
        }
        System.out.println(answer);
        br.close();
    }
}
