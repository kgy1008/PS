import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    static int len;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        String N = st.nextToken();
        int K = Integer.parseInt(st.nextToken());

        len = N.length();
        Deque<String> queue = new ArrayDeque<>();
        queue.offer(N);

        for (int i = 0; i < K; i++) {
            int size = queue.size();
            Set<String> visited = new HashSet<>();

            if (size == 0) {
                System.out.println(-1);
                return;
            }

            for (int j = 0; j < size; j++) {
                String current = queue.poll();
                char[] arr = current.toCharArray();
                findAllCase(arr, visited, queue);
            }
        }

        if (queue.isEmpty()) {
            System.out.println(-1);
        } else {
            int answer = -1;
            while (!queue.isEmpty()) {
                answer = Math.max(answer, Integer.parseInt(queue.poll()));
            }
            System.out.println(answer);
        }
    }

    static void findAllCase(char[] arr, Set<String> visited, Deque<String> queue) {
        // 모든 경우의 수 구하기
        for (int j = 0; j < len; j++) {
            for (int l = j + 1; l < len; l++) {
                char temp = arr[j];
                arr[j] = arr[l];
                arr[l] = temp;

                if (arr[0] == '0') {
                    // 원상 복구
                    temp = arr[j];
                    arr[j] = arr[l];
                    arr[l] = temp;
                    continue;
                }

                String next = new String(arr);

                if (!visited.contains(next)) {
                    queue.add(next);
                    visited.add(next);
                }

                // 원상 복구
                temp = arr[j];
                arr[j] = arr[l];
                arr[l] = temp;
            }
        }
    }
}
