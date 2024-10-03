import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        ArrayDeque<Integer> deque = new ArrayDeque<>();

        for (int i = 1; i <= n; i++) {
            deque.offer(i);
        }

        while(deque.size() > 1) {
            deque.poll();
            deque.offer(deque.poll());
        }

        System.out.println(deque.poll());
    }
}
