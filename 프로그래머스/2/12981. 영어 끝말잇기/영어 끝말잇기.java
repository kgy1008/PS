import java.util.*;

class Solution {
    public int[] solution(int n, String[] words) {
        HashSet<String> sentence = new HashSet<>();
        int[] answer = new int[2];
        sentence.add(words[0]);
        String previous = words[0];
        
        for (int i = 1; i<words.length; i++) {
            if (sentence.contains(words[i]) || previous.charAt(previous.length()-1) != words[i].charAt(0)) {
                answer[1] = i/n + 1;
                answer[0] = i%n + 1;
                return answer;
            }
            
            sentence.add(words[i]);
            previous = words[i];
        }
        
        return answer;
    }
}