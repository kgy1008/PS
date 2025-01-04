import java.util.*;

class Solution {
    public String[] solution(String[] strings, int n) {
        List<String> list = new ArrayList<>();
        for (String s : strings) {
            list.add(s);
        }
        list.sort(null);
        list.sort((o1,o2) -> Integer.compare(o1.charAt(n), o2.charAt(n)));
        
        return list.toArray(new String[0]);
    }
}