import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[] nums = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        int min = nums[0];
        int answer = 0;

        for (int i = 1; i < n; i++) {
            int current = nums[i];

            if (current < min) {
                min = current;
            }

            int currentProfit = current - min;
            answer = Math.max(answer, currentProfit);

        }
        System.out.println(answer);
    }
}
