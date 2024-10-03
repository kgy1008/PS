import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    private static ArrayList<Integer>[] person;
    private static boolean[] visited;
    private static int answer = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        int person1 = Integer.parseInt(st.nextToken());
        int person2 = Integer.parseInt(st.nextToken());

        int m = Integer.parseInt(br.readLine());
        person = new ArrayList[n+1];
        visited = new boolean[n+1];

        for (int i = 1; i <= n; i++) {
            person[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int parent = Integer.parseInt(st.nextToken());
            int child = Integer.parseInt(st.nextToken());
            person[parent].add(child);
            person[child].add(parent);
        }

        dfs(person1, person2, 0);
        System.out.println(answer);
        br.close();
    }

    private static void dfs(int start, int end, int cnt) {
        if (start == end) {
            answer = cnt;
            return;
        }
        visited[start] = true;
        for (int p : person[start]) {
            if (!visited[p]) {
                dfs(p, end, cnt+1);
            }
        }
    }
}
