import java.util.*;

class Solution {
    public int[] solution(int[] answers) {
        ArrayList<Integer> result = new ArrayList<>();
        int[] arr1 = {1,2,3,4,5};
        int[] arr2 = {2,1,2,3,2,4,2,5};
        int[] arr3 = {3,3,1,1,2,2,4,4,5,5};
        
        int person1 = 0;
        int person2 = 0;
        int person3 = 0;
        
        for(int i = 0; i < answers.length; i++) {
            if (arr1[i % arr1.length] == answers[i]) {
                person1 += 1;
            }
            if (arr2[i % arr2.length] == answers[i]) {
                person2 += 1;
            }
            if (arr3[i % arr3.length] == answers[i]) {
                person3 += 1;
            }
        }
        
        int max = Math.max(person1, Math.max(person2, person3));
        
        if (person1 == max) {
            result.add(1);
        }
        if (person2 == max) {
            result.add(2);
        }
        if (person3 == max) {
            result.add(3);
        }
        
        return result.stream()
            .mapToInt(Integer::intValue)
            .toArray();
    }
}