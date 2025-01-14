import java.util.*;

class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        HashMap<String, Set<String>> map = new HashMap<>();
        Map<String, Integer> warn = new HashMap<>();
        for (String id : id_list) {
            map.put(id, new HashSet<>());
        }
        
        for (String r : report) {
            String[] tmp = r.split(" ");
            Set<String> set = map.get(tmp[0]);
            if (!set.contains(tmp[1])) {
                set.add(tmp[1]);
                warn.put(tmp[1], warn.getOrDefault(tmp[1],0) + 1);
            }
        }
        
        Set<String> out = new HashSet<>();
        for (String name : warn.keySet()) {
            if (warn.get(name) >= k) {
                out.add(name);
            }
        }
        
        int[] answer = new int[id_list.length];
        int idx = 0;
        for (String name : id_list) {
            int cnt = 0;
            Set<String> tmp = map.get(name);
            for (String t : tmp) {
                if (out.contains(t)) {
                    cnt++;
                }
            }
            answer[idx++] = cnt;
        }
        return answer;
    }
}