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
        int s = Integer.parseInt(st.nextToken());

        int[] num = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }

        int right = 0;
        int sum = 0;
        int answer = 100001;
        for (int left = 0; left < n; left++) {
            while (right < n && sum < s) {
                sum += num[right++];
            }

            if (sum >= s) {
                answer = Math.min(answer, right - left);
            } else {
                if (right == n) {
                    break;
                }
            }
            sum -= num[left];
        }

        System.out.println(answer == 100001 ? 0 : answer);
    }
}
