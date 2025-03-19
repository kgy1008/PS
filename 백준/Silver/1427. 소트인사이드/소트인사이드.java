import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        int n = s.length();
        int[] nums = new int[n];

        for (int i = 0; i < n; i++) {
            nums[i] = s.charAt(i) - '0';
        }

        Arrays.sort(nums);
        StringBuilder sb = new StringBuilder();
        for (int i = n - 1; i >= 0; i--) {
            sb.append(nums[i]);
        }
        String answer = sb.toString();
        if (answer.charAt(0) == 0) {
            System.out.println(0);
        } else {
            System.out.println(answer);
        }
    }
}
