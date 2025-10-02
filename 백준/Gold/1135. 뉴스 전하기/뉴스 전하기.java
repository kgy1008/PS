import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    private static List<Integer>[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); // 직원의 수

        tree = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            tree[i] = new ArrayList<>();
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        st.nextToken();

        for (int i = 1; i < N; i++) {
            int manager = Integer.parseInt(st.nextToken());
            tree[manager].add(i);
        }

        int minTime = dfs(0);

        System.out.println(minTime);
    }

    private static int dfs(int current) {
        if (tree[current].isEmpty()) { // 직속 부하가 없음
            return 0;
        }

        List<Integer> childTimes = new ArrayList<>();
        for (int child : tree[current]) {
            childTimes.add(dfs(child));
        }

        childTimes.sort(Collections.reverseOrder());

        int minTime = 0;
        for (int i = 0; i < childTimes.size(); i++) {
            int childTime = childTimes.get(i);
            int orderTime = i + 1;

            int totalTime = childTime + orderTime;

            minTime = Math.max(minTime, totalTime);
        }

        return minTime;
    }
}
