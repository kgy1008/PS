import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int answer = 0;
    static int n;
    static int s;
    static int[] arr;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());

        arr = new int[n];
        st = new StringTokenizer(br.readLine());
        visited = new boolean[n + 1];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            if (arr[i] != 0) {
                visited[arr[i]] = true;
            }
        }

        solve(0);
        System.out.println(answer);
    }

    private static void solve(int idx) {
        if (idx == n) {
            check();
            return;
        }
        
        if (arr[idx] == 0) {
            for (int num = 1; num <= n; num++) {
                if (!visited[num]) {
                    arr[idx] = num;
                    visited[num] = true;
                    solve(idx + 1); 
                    visited[num] = false; 
                    arr[idx] = 0; 
                }
            }
        } else { 
            solve(idx + 1); 
        }
    }

    private static void check() {
        int point = 0;
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (arr[i] < arr[j]) {
                    point++;
                }
            }
        }

        if (point == s) {
            answer++;
        }
    }
}
