import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> que = new PriorityQueue<>((o1,o2) -> Integer.compare(o2,o1));
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(br.readLine());

            if (num == 0) {
                sb.append(que.isEmpty() ? 0 : que.poll()).append('\n');
            } else {
                que.add(num);
            }
        }
        System.out.println(sb);
    }
}
