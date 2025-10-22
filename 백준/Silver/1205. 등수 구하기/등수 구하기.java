import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int p = Integer.parseInt(st.nextToken());

        if (n == 0) {
            System.out.println(1);
            return;
        }

        List<Integer> scores = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            scores.add(Integer.parseInt(st.nextToken()));
        }

        scores.sort(Collections.reverseOrder());

        if (n == p && k <= scores.get(n - 1)) {
            System.out.println(-1);
            return;
        }

        scores.add(k);
        scores.sort(Collections.reverseOrder());

        int rank = 1;
        for (int score : scores) {
            if (score > k) {
                rank++;
            } else {
                break;
            }
        }

        if (rank > p) {
            System.out.println(-1);
        } else {
            System.out.println(rank);
        }
    }
}
