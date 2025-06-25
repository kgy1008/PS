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
 
            list.add(new Word(file, head, number));
        }
        
        // 정렬
        list.sort((o1, o2) -> {
            int headCompare = o1.head.toLowerCase().compareTo(o2.head.toLowerCase());
            if (headCompare == 0) {
                int num1 = Integer.parseInt(o1.number);
                int num2 = Integer.parseInt(o2.number);
                return Integer.compare(num1, num2); 
            }
            
            return headCompare;
        });
        
        // 결과
        String[] result = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i).original;
        }
        return result;
    }

    private class Word {
        String original;
        String head;
        String number;

        Word(String original, String head, String number) {
            this.original = original;
            this.head = head;
            this.number = number;
        }
    }
}
