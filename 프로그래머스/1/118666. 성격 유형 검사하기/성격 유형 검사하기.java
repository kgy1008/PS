import java.util.*;

class Solution {
    public String solution(String[] survey, int[] choices) {
        HashMap<Character, Integer> map = new HashMap<>();
        map.put('R',0);
        map.put('T',0);
        map.put('C',0);
        map.put('F',0);
        map.put('J',0);
        map.put('M',0);
        map.put('A',0);
        map.put('N',0);
        
        int len = survey.length;
        for (int i=0; i<len; i++) {
            String str = survey[i];
            int choice = choices[i];
            
            if (choice < 4) { // 비동의
                map.put(str.charAt(0), map.get(str.charAt(0)) + 4-choice);
            } else if (choice > 4) { // 동의
                map.put(str.charAt(1), map.get(str.charAt(1)) + choice-4);
            }
        }
        
        
        StringBuilder result = new StringBuilder();
        if (map.get('R') >= map.get('T')) {
            result.append('R');
        } else {
            result.append('T');
        }
        
        if (map.get('C') >= map.get('F')) {
            result.append('C');
        } else {
            result.append('F');
        }
        
        if (map.get('J') >= map.get('M')) {
            result.append('J');
        } else {
            result.append('M');
        }
        
        if (map.get('A') >= map.get('N')) {
            result.append('A');
        } else {
            result.append('N');
        }
        
        return result.toString();
    }
}