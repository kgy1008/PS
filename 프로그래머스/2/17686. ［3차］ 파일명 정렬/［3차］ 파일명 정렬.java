import java.util.*;

class Solution {
    public String[] solution(String[] files) {
        List<Word> list = new ArrayList<>();
        
        for (String file : files) {
            String head = "";
            String number = "";
            int i = 0;
            
            // HEAD 추출
            while (i < file.length() && !Character.isDigit(file.charAt(i))) {
                head += file.charAt(i);
                i++;
            }
            
            // NUMBER 추출 (최대 5자리)
            while (i < file.length() && Character.isDigit(file.charAt(i)) && number.length() < 5) {
                number += file.charAt(i);
                i++;
            }
            
            String tail = file.substring(i);
 
            list.add(new Word(head, number, tail));
        }
        
 
        list.sort(
            Comparator.comparing((Word o) -> o.head.toLowerCase())
              .thenComparing(o -> Integer.parseInt(o.number))
        );

        
        String[] result = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            Word word = list.get(i);
            result[i] = word.head + word.number + word.tail;
        }
        return result;
    }
    
    private class Word {
        String head;
        String number;
        String tail;
        
        Word(String head, String number, String tail) {
            this.head = head;
            this.number = number;
            this.tail = tail;
        }
    }
}
