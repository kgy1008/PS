import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine()); // 재료 개수
        int m = Integer.parseInt(br.readLine()); // 필요한 수

        int[] num = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(num);
        int end = n - 1;
        int answer = 0;
        for (int start = 0; start < end; start++) {
            int sum = num[start] + num[end];
            while (start < end && sum >= m) {
                if (sum == m) {
                    answer++;
                }
                end--;
                sum = num[start] + num[end];
            }
        }
        System.out.println(answer);
    }
}
