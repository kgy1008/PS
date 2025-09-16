import java.util.*;

class Solution {
    static long answer = 0L;
    static List<String> list = List.of("-", "+", "*");
    static boolean[] visited = new boolean[3];
    
    public long solution(String expression) {
        solve(expression, new ArrayList<>());
        return answer;
    }
    
    private static void solve(String expression, List<String> tmp) {
        if (tmp.size() == 3) {
            calculate(expression, tmp);
            return;
        }
        
        for (int i=0; i<3; i++) {
            if (!visited[i]) {
                visited[i] = true;
                tmp.add(list.get(i));
                
                solve(expression, tmp);
                
                visited[i] = false;
                tmp.remove(tmp.size()-1);
            }
        }
    }
    
    private static void calculate(String ex, List<String> tmp) {
        List<Long> numbers = new ArrayList<>();
        List<Character> operators = new ArrayList<>();

        StringTokenizer st = new StringTokenizer(ex, "-+*/", true);
        while (st.hasMoreTokens()) {
            String token = st.nextToken();
            if (list.contains(token)) {
                operators.add(token.charAt(0));
            } else {
                numbers.add(Long.parseLong(token));
            }
        }

        // 우선순위에 따라 계산
        for (String t : tmp) {
            char op = t.charAt(0);
            for (int i = 0; i < operators.size(); i++) {
                if (operators.get(i) == op) {
                    long num1 = numbers.get(i);
                    long num2 = numbers.get(i + 1);
                    long result = 0;

                    if (op == '+') {
                        result = num1 + num2;
                    } else if (op == '-') {
                        result = num1 - num2;
                    } else {
                        result = num1 * num2;
                    }

                    numbers.set(i, result);
                    numbers.remove(i + 1);
                    operators.remove(i);
                    i--;
                }
            }
        }
        answer = Math.max(answer, Math.abs(numbers.get(0)));
    }
}