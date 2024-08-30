import java.util.*;

class Solution {
    public int[] solution(int n, String[] words) {
        int[] answer = new int[2];
        HashSet<String> sentence = new HashSet<>();
        char previous = words[0].charAt(0);
        
        for (int i = 0; i<words.length; i++) {
            if (sentence.contains(words[i]) || previous != words[i].charAt(0)) {
                answer[1] = i/n + 1;
                answer[0] = i%n + 1;
                return answer;
            }
            
            sentence.add(words[i]);
            previous = words[i].charAt(words[i].length() - 1);
        }
        
        return answer;
    }
}