import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        String[] target = input.split(" ");

        boolean flag = false;
        StringBuilder answer = new StringBuilder();
        for (String t : target) {
            StringBuilder sb = new StringBuilder();
            Deque<Character> stack = new ArrayDeque<>();

            for (char c : t.toCharArray()) {
                if (c == '<') {
                    flag = true;
                    while (!stack.isEmpty()) {
                        sb.append(stack.pop());
                    }
                }
                if (c == '>') {
                    flag = false;
                    sb.append(c);
                    continue;
                }

                if (flag) {
                    sb.append(c);
                } else {
                    stack.push(c);
                }
            }
            while (!stack.isEmpty()) {
                sb.append(stack.pop());
            }
            answer.append(sb).append(" ");
        }
        System.out.println(answer);
    }
}
