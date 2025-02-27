import java.util.*;

class Solution {
    public String[] solution(String[] s) {
        String[] answer = new String[s.length];

        for (int j = 0; j < s.length; j++) {
            Deque<Character> stack = new ArrayDeque<>();
            int cnt = 0; // 110의 개수
            StringBuilder sb = new StringBuilder();
            String str = s[j];

            for (int i = 0; i < str.length(); i++) {
                char z = str.charAt(i);
                if (stack.size() >= 2) {
                    char x = stack.pop();
                    char y = stack.pop();

                    if (z == '0' && x == '1' && y == '1') {
                        cnt++;
                    } else {
                        stack.push(y);
                        stack.push(x);
                        stack.push(z);
                    }
                } else {
                    stack.push(z);
                }
            }

            while (!stack.isEmpty() && stack.peek() != '0') {
                sb.append(stack.pop());
            }

            for (int t = 0; t < cnt; t++) {
                sb.append(0).append(1).append(1);
            }

            while (!stack.isEmpty()) {
                sb.append(stack.pop());
            }
            String temp = sb.reverse().toString();
            answer[j] = temp;
        }

        return answer;
    }
}
