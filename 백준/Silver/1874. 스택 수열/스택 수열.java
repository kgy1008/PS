import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        StringBuilder sb = new StringBuilder();

        int index = 1;
        for (int i = 0; i < n; i++) {
            int number = Integer.parseInt(br.readLine());

            while (index <= number) {
                stack.push(index++);
                sb.append("+\n");
            }
            if (stack.peek() == number) {
                stack.pop();
                sb.append("-\n");
            }
            else {
                System.out.println("NO");
                return;
            }
        }
        System.out.print(sb);
    }
}
