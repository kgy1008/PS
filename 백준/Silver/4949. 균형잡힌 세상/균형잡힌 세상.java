import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        HashMap<Character, Character> map = new HashMap<>();
        map.put('(', ')');
        map.put('[', ']');
        ArrayDeque<Character> stack = new ArrayDeque<>();

        StringBuilder sb = new StringBuilder();

        while (true) {
            boolean flag = true;
            String cmd = br.readLine();
            if (cmd.equals(".")) {
                break;
            }

            for (char i : cmd.toCharArray()) {
                if (map.keySet().contains(i)) {
                    stack.push(i);
                }
                else if (map.values().contains(i)) {
                    if (stack.size() > 0 && map.get(stack.peek()) == i) {
                        stack.pop();
                    }
                    else {
                        flag = false;
                        break;
                    }
                }
            }

            if (!stack.isEmpty()) flag = false;
            if (flag) sb.append("yes\n");
            else sb.append("no\n");
            stack.clear();
        }
        System.out.println(sb);
    }
}
