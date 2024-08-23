import java.util.*;

class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        HashMap<String, Integer> cnt = new HashMap<>();
        HashMap<String, HashSet<String>> warn = new HashMap<>();
        
        for (String id : id_list) {
            cnt.put(id, 0); 
            warn.put(id, new HashSet<>()); 
        }
        
        for (String rep : report) {
            String[] warnList = rep.split(" ");
            String reporter = warnList[0]; 
            String target = warnList[1]; 
            
            if (!warn.get(reporter).contains(target)) {
                warn.get(reporter).add(target);
                cnt.put(target, cnt.get(target) + 1);
            }
        }
        
        List<String> result = new ArrayList<>();

        for (Map.Entry<String, Integer> entry : cnt.entrySet()) {
            if (entry.getValue() >= k) {
                result.add(entry.getKey());
            }
        }
        
        int[] answer = new int[id_list.length];
        
        for (int i = 0; i < id_list.length; i++) {
            int count = 0;
            for (String n : warn.get(id_list[i])) {
                if (result.contains(n)) {
                    count++;
                }
            }
            answer[i] = count;
        }
        return answer;
    }
}
