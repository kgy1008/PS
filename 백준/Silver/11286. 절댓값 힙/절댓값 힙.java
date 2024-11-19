import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> que = new PriorityQueue<>((o1, o2) -> {
            int absCompare = Integer.compare(Math.abs(o1), Math.abs(o2));
            if (absCompare == 0) {
                return Integer.compare(o1, o2);
            }
            return absCompare;
        });
        StringBuilder sb = new StringBuilder();

        while (n-- > 0) {
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
