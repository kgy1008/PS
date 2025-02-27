import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

    static Set<List<Integer>> list = new LinkedHashSet<>();
    static int[] nums;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        nums = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(nums);

        combination(m, 0, new ArrayList<>());

        StringBuilder sb = new StringBuilder();
        for (List<Integer> numbers : list) {

            for (int num : numbers) {
                sb.append(num).append(" ");
            }
            sb.append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
    }

    static void combination(int m, int idx, List<Integer> number) {
        if (m == 0) {
            list.add(new ArrayList<>(number));
            return;
        }

        for (int i = idx; i < nums.length; i++) {
            number.add(nums[i]);
            combination(m - 1, i, number);
            number.remove(number.size() - 1);
        }
    }
}
