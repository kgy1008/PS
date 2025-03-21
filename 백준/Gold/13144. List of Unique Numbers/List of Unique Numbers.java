import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int[] nums = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        Set<Integer> set = new HashSet<>();

        int end = 0;
        long answer = 0L;
        for (int start = 0; start < n; start++) {
            while (end < n && !set.contains(nums[end])) {
                set.add(nums[end++]);
            }
            answer += (end - start);
            set.remove(nums[start]);
        }
        System.out.println(answer);
    }
}
