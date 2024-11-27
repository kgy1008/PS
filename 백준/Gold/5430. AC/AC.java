import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        StringBuilder answer = new StringBuilder();

        A: while (t-- > 0) {
            String p = br.readLine();
            int n = Integer.parseInt(br.readLine());
            String num = br.readLine();
            Deque<Integer> list = parsenum(num);

            boolean isReversed = false;
            for (char command : p.toCharArray()) {
                if (command == 'R') {
                    isReversed = !isReversed;
                }
                else {
                    if (list.isEmpty()) {
                        answer.append("error").append("\n");
                        continue A;
                    } if (isReversed) {
                        list.pollLast();
                    } else {
                        list.pollFirst();
                    }
                }
            }
            String an = print(list, isReversed);
            answer.append(an).append("\n");
        }

        System.out.println(answer);
    }

    private static ArrayDeque<Integer> parsenum(String num) {
        ArrayDeque<Integer> list = new ArrayDeque<>();
        num = num.replace("[", "").replace("]", "");
        if (num.isBlank()) {
            return list;
        }
        String[] nums = num.split(",");
        for (int i = 0; i < nums.length; i++) {
            list.add(Integer.parseInt(nums[i]));
        }
        return list;
    }

    private static String print(Deque<Integer> deque, boolean isReversed) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");

        if (isReversed) {
            while (!deque.isEmpty()) {
                sb.append(deque.pollLast());
                if (!deque.isEmpty()) {
                    sb.append(",");
                }
            }
        } else {
            while (!deque.isEmpty()) {
                sb.append(deque.pollFirst());
                if (!deque.isEmpty()) {
                    sb.append(",");
                }
            }
        }

        sb.append("]");
        return sb.toString();
    }
}
