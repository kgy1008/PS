import java.util.*;

class Solution {
    public int solution(String s) {
        int answer = 0;
        int len = s.length();
        String rhkfgh = s+s;
        int start = 0;
        int end = len;
        
        HashMap<Character, Character> map = new HashMap<>();
        map.put('(', ')');
        map.put('[', ']');
        map.put('{', '}');
        
        while(start<len) {
            ArrayDeque<Character> stack = new ArrayDeque<>();
            stack.push(rhkfgh.charAt(start));
            String target = rhkfgh.substring(start+1,end);
            
            for (Character t : target.toCharArray()) {
                if (t == '(' || t == '[' || t == '{') stack.push(t);
                else {
                    if (!stack.isEmpty() && map.get(stack.peek()) == t) stack.pop();
                }
            }
            
            if (stack.isEmpty()) answer++;
            start++;
            end++;
        }
        
        return answer;
    }
}