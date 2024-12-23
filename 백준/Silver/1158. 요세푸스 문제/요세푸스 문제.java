import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringJoiner;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            list.add(i);
        }

        Deque<Integer> queue = new ArrayDeque<>(list);
        StringJoiner sb = new StringJoiner(", ");
        while (!queue.isEmpty()) {
            int sequence = 0;
            while (sequence < k - 1) {
                queue.offer(queue.poll());
                sequence++;
            }
            int out = queue.poll();
            sb.add(String.valueOf(out));
        }
        String target = sb.toString();
        System.out.println("<" + target + ">");
    }
}
