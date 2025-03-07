import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        int start = 0;
        int end = 0;
        long answer = 0L;

        Set<Integer> set = new HashSet<>();
        while (start < n) {
            while (end < n && !set.contains(nums[end])) {
                set.add(nums[end++]);
            }
            answer += (end - start);
            set.remove(nums[start++]);
        }

        System.out.println(answer);
    }
}
