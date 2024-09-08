import java.util.*;

class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        LinkedHashMap<String, Set<String>> warnList = new LinkedHashMap<>();
        HashMap<String, Integer> warnCount = new HashMap<>();
        
        for (String name : id_list) {
            warnList.put(name, new HashSet<String>());
        }
        
        for (String r : report) {
            String[] reportName = r.split(" ");
            
            Set<String> nameSet = warnList.get(reportName[0]);
            if (!nameSet.contains(reportName[1])) {
                nameSet.add(reportName[1]);
                warnList.put(reportName[0], nameSet);
            
                warnCount.put(reportName[1], warnCount.getOrDefault(reportName[1], 0) + 1);
            }
        }
        
        ArrayList<String> stopPlayer = new ArrayList<>();
        
        for (String player : warnCount.keySet()) {
            if (warnCount.get(player) >= k) {
                stopPlayer.add(player);
            }
        }
        
        stopPlayer.stream().forEach(System.out::println);
        
        int[] result = new int[id_list.length];
        int idx = 0;
        
        for (String reporter : warnList.keySet()) {
            Set<String> r = warnList.get(reporter);
            for (String sp : stopPlayer) {
                if (r.contains(sp)) result[idx]++;
            }
            idx++;
        }
        
        return result;
    }
}