import java.util.*;

class Solution {
    public int[] solution(int[] answers) {
        int[] person = new int[4];
        int[] mark1 = new int[] {1,2,3,4,5};
        int[] mark2 = new int[] {2,1,2,3,2,4,2,5};
        int[] mark3 = new int[] {3,3,1,1,2,2,4,4,5,5};
        
        for (int i=0; i<answers.length; i++) {
            if (answers[i] == mark1[i%5]) {
                person[1]++;
            }
            if (answers[i] == mark2[i%8]) {
                person[2]++;
            }
            if (answers[i] == mark3[i%10]) {
                person[3]++;
            }
        }
        
        ArrayList<Integer> ans = new ArrayList<>();
        int max = 0;
        for (int i : person) {
            if (max < i) {
                max = i;
            }
        }
        
        for  (int i=1; i<4; i++) {
            if (max == person[i]) {
                ans.add(i);
            }
        }
        return ans.stream().mapToInt(Integer::intValue).toArray();
    }
}