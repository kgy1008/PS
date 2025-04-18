import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String target = br.readLine();
        String bomb = br.readLine();

        int size = bomb.length();

        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < target.length(); i++) {
            char c = target.charAt(i);
            stack.push(c);

            int s = stack.size();
            boolean flag = true;

            if (s >= size) {
                for (int j = 0; j < size; j++) {
                    if (stack.get(s - j - 1) != bomb.charAt(size - j - 1)) {
                        flag = false;
                        break;
                    }
                }
            }

            if (s >= size && flag) { // 모두 일치한다면
                for (int j = 0; j < size; j++) {
                    stack.pop();
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        sb.reverse();
        String answer = sb.toString();
        System.out.println(answer.isEmpty() ? "FRULA" : answer);
    }
}
