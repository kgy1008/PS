import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] tree = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            tree[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(tree);

        int start = 0;
        int end = tree[n-1];


        while (start < end) {
            long sum = 0;
            int mid = (start + end)/2;

            for (int t : tree) {
                if (t > mid) {
                    sum += t - mid;
                }
            }

            if (sum >= m) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }

        System.out.println(end - 1);
    }
}
