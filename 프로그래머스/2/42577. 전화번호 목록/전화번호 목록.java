import java.util.*;

class Solution {
    public boolean solution(String[] phone_book) {
        Set<String> list = new HashSet<>();
        for (String number : phone_book) {
            list.add(number);
        }
        
        for (String number : phone_book) {
            for (int i=1; i< number.length(); i++) {
                String target = number.substring(0,i);
                if (list.contains(target)) {
                    return false;
                }
            }
        }
        return true;
    }
}