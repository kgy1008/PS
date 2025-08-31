import java.util.Stack;

class Solution {

    public String solution(String p) {
        if (p.isEmpty()) {
            return "";
        }

        // 2. 문자열 p를 두 균형잡힌 괄호 문자열 u와 v로 분리
        int balance = 0;
        int splitPoint = 0;
        for (int i = 0; i < p.length(); i++) {
            if (p.charAt(i) == '(') {
                balance++;
            } else {
                balance--;
            }
            if (balance == 0) {
                splitPoint = i + 1;
                break;
            }
        }
        
        String u = p.substring(0, splitPoint);
        String v = p.substring(splitPoint);

        // 3. u가 "올바른 괄호 문자열"인지 확인
        if (isCorrect(u)) {
            // 3-1. 올바른 경우
            return u + solution(v);
        } else {
            // 4. u가 "올바른 괄호 문자열"이 아닌 경우
            StringBuilder sb = new StringBuilder();
            sb.append("(");
            sb.append(solution(v));
            sb.append(")");

            // u의 첫 번째와 마지막 문자 제거 후, 나머지 괄호 방향 뒤집기
            String reversedU = reverseAndTrim(u);
            sb.append(reversedU);

            return sb.toString();
        }
    }

    // "올바른 괄호 문자열"인지 판별
    // 스택을 사용해 (의 개수가 )의 개수보다 많거나 같은 상태를 유지해야 함
    private boolean isCorrect(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                stack.pop();
            }
        }
        return stack.isEmpty();
    }

    // 문자열의 양 끝을 제거하고 괄호 방향을 뒤집는 메서드
    private String reverseAndTrim(String s) {
        StringBuilder reversed = new StringBuilder();
        for (int i = 1; i < s.length() - 1; i++) {
            if (s.charAt(i) == '(') {
                reversed.append(')');
            } else {
                reversed.append('(');
            }
        }
        return reversed.toString();
    }
}