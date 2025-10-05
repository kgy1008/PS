import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        Stack<Person> stack = new Stack<>();
        long answer = 0L;

        for (int i = 0; i < N; i++) {
            int h = Integer.parseInt(br.readLine());
            int sameCount = 1;
            
            while (!stack.isEmpty() && stack.peek().height <= h) {
                Person top = stack.pop();
                answer += top.count;

                if (top.height == h) {
                    sameCount = top.count + 1;
                    break;
                }
            }

            if (!stack.isEmpty()) {
                answer++;
            }

            stack.push(new Person(h, sameCount));
        }

        System.out.println(answer);
    }

    static class Person {
        int height;
        int count;

        Person(int height, int count) {
            this.height = height;
            this.count = count;
        }
    }
}
