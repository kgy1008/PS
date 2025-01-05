import java.util.*;

class Solution {
    public int[] solution(String s) {
        String[] arr = s.split("},");
        String[] result = new String[arr.length];
        int idx = 0;
        for (String target : arr) {
            target = target.replace("{","").replace("}","");
            result[idx++] = target;
        }
        
        List<Integer> answer = new ArrayList<>();
        Arrays.sort(result, (o1,o2) -> Integer.compare(o1.length(), o2.length()));
        for (String r : result) {
            String[] tmp = r.split(",");
            for (String t : tmp) {
                int a = Integer.parseInt(t);
                if (!answer.contains(a)) {
                    answer.add(a);   
                }
            }
        }
        
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
}