import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            if (arr[i] == 1) {
                list.add(i);
            }
        }

        int answer = n + 1;
        if (list.size() < k) {
            System.out.println(-1);
            return;
        }

        int left = 0;
        int right = k - 1;

        while (right < list.size()) {
            int size = list.get(right) - list.get(left) + 1;
            answer = Math.min(answer, size);
            left++;
            right++;
        }

        System.out.println(answer);
    }
}
