import java.util.*;
import java.util.stream.*;

class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        Map<String, List<String>> map = new LinkedHashMap<>();
        
        for (String id : id_list) {
            map.put(id, new ArrayList<>());
        }
        
        Map<String, Integer> warnCount = new HashMap<>();
        
        for (String r : report) {
            String[] info = r.split(" ");
            List<String> list = map.get(info[0]);
            if (!list.contains(info[1])) {
                list.add(info[1]);
                warnCount.put(info[1], warnCount.getOrDefault(info[1], 0) + 1);
            }
        }
        
        List<String> out = warnCount.entrySet().stream()
            .filter(o-> o.getValue() >= k)
            .map(o -> o.getKey())
             .collect(Collectors.toList());
        
        int[] result = new int[id_list.length];
        
        for (int i=0; i<id_list.length; i++) {
            int cnt = 0;
            List<String> target = map.get(id_list[i]);
            for (String o : out) {
                if (target.contains(o)) {
                    cnt++;
                }
            }
            result[i] = cnt;
        }
        
        return result;
    }
}