import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int[] nums;
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // N까지의 숫자
        int m = Integer.parseInt(st.nextToken()); // 선택 횟수

        nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = i + 1;
        }

        combination(0, m);
    }

    private static void combination(int idx, int m) {
        if (sb.length() == m) {
            String answer = sb.toString().trim();
            StringBuilder tmp = new StringBuilder();
            for (int i = 0; i < answer.length(); i++) {
                tmp.append(answer.charAt(i)).append(" ");
            }
            System.out.println(tmp.toString());
            return;
        }

        for (int i = idx; i < nums.length; i++) {
            sb.append(nums[i]);
            combination(i + 1, m);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
