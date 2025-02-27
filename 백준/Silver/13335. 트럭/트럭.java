import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 트럭의 수
        int w = Integer.parseInt(st.nextToken()); // 다리 길이
        int l = Integer.parseInt(st.nextToken()); // 다리 최대 하중

        Deque<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < w; i++) {
            queue.offer(0);
        }

        int[] truck = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            truck[i] = Integer.parseInt(st.nextToken());
        }

        int time = 0;
        int idx = 0;
        int weight = 0;
        while (idx < n) {
            weight -= queue.poll();
            if (weight + truck[idx] <= l) {
                weight += truck[idx];
                queue.offer(truck[idx++]);
            } else {
                queue.offer(0);
            }
            time++;
        }

        System.out.println(time + queue.size());
    }
}
