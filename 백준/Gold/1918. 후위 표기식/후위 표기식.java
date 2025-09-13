import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String infix = br.readLine();
        StringBuilder answer = new StringBuilder();
        Stack<Character> stack = new Stack<>();

        for (char ch : infix.toCharArray()) {
            if (Character.isLetter(ch)) {
                answer.append(ch);
            } else {
                if (ch == '(') {
                    stack.push(ch);
                } else if (ch == ')') {
                    while (!stack.isEmpty() && stack.peek() != '(') {
                        answer.append(stack.pop());
                    }
                    if (!stack.isEmpty()) {
                        stack.pop(); // '('제거
                    }
                } else { // (+,-,*,/)
                    while (!stack.isEmpty() && importancy(stack.peek()) >= importancy(ch)) {
                        answer.append(stack.pop());
                    }
                    stack.push(ch);
                }
            }
        }

        while (!stack.isEmpty()) {
            answer.append(stack.pop());
        }

        System.out.println(answer.toString());
    }

    private static int importancy(char op) {
        if (op == '*' || op == '/') {
            return 2;
        } else if (op == '+' || op == '-') {
            return 1;
        } else {
            return 0;
        }
    }
}
