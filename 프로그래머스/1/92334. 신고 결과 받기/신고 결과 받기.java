import java.util.*;

class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        Map<String, Set<String>> map = new HashMap<>();
        
        for (String id : id_list) {
            map.put(id, new HashSet<>());
        }
        
        for (String r : report) {
            String[] info = r.split(" ");
            Set<String> tmp = map.get(info[1]);
            tmp.add(info[0]);
        }
        
        List<String> out = new ArrayList<>();
        
        for (String s : map.keySet()) {
            Set<String> tmp = map.get(s);
            if (tmp.size() >= k) {
                out.add(s);
            }
        }
        
        int n = id_list.length;
        int[] result = new int[n];
        
        for (int i=0; i<n; i++) {
            String target = id_list[i];
            int cnt = 0;
            
            for (String s : out) {
                Set<String> tmp = map.get(s);
                if (tmp.contains(target)) {
                    cnt++;
                }
            }
            
            result[i] = cnt;
        }
        
        return result;
    }
}