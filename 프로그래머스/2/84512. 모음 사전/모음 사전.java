import java.util.*;

class Solution {
    private static List<String> list = new ArrayList<>();
    private static String[] arr = new String[]{"A", "E", "I", "O", "U"};
    
    public int solution(String word) {
        for (int len=1; len<=5; len++) {
            find(len, "");
        }
        
        Collections.sort(list);
        
        int answer = 1;
        for (String s : list) {
            if (word.equals(s)) {
                break;
            }
            answer++;
        }
        return answer;
    }
    
    private static void find(int len, String word) {
        if (word.length() == len) {
            list.add(word);
            return;
        }
        
        for (int i=0; i<arr.length; i++) {
            find(len, word+arr[i]);
        }
    }
}