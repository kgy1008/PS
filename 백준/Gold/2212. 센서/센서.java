import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // 센서의 개수

        st = new StringTokenizer(br.readLine());
        int k = Integer.parseInt(st.nextToken()); // 집중국의 개수

        TreeSet<Integer> sensors = new TreeSet<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            sensors.add(Integer.parseInt(st.nextToken()));
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (int i = 0; i < n - 1; i++) {
            if (sensors.size() >= 2) {
                int first = sensors.pollFirst();
                int second = sensors.first();
                pq.offer(second - first);
            }
        }

        for (int i = 0; i < k - 1; i++) {
            if (!pq.isEmpty()) {
                pq.poll();
            }
        }

        int answer = 0;
        while (!pq.isEmpty()) {
            answer += pq.poll();
        }

        System.out.println(answer);
    }
}
