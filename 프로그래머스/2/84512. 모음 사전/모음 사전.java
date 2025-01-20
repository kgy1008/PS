import java.util.*;

class Solution {
    static List<String> list = new ArrayList<>();
    static String [] words = {"A", "E", "I", "O", "U"};
    
    public int solution(String word) {
        dfs("", 0);

        int answer = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals(word)) {
                answer = i;
                break;
            }
        }
        return answer;
    }

    static void dfs(String str, int len) {
        list.add(str);
        if (len == 5) return;
        for (int i = 0; i < 5; i++) {
            dfs(str + words[i], len + 1);
        }
    }
}