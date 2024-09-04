import java.util.*;

class Solution {
    public int[] solution(String[] genres, int[] plays) {
        HashMap<String, Integer> map = new HashMap<>();
        HashMap<String, ArrayList<int[]>> music = new HashMap<>();
        
        for (int i = 0; i < genres.length; i++) {
            map.put(genres[i], map.getOrDefault(genres[i], 0) + plays[i]);

            ArrayList<int[]> list = music.getOrDefault(genres[i], new ArrayList<>());
            list.add(new int[]{i, plays[i]});
            music.put(genres[i], list);
        }
        
        List<Map.Entry<String, Integer>> entryList = new ArrayList<>(map.entrySet());
        entryList.sort((a, b) -> b.getValue().compareTo(a.getValue()));

        LinkedHashMap<String, Integer> sortedMap = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> entry : entryList) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        
        ArrayList<Integer> ans = new ArrayList<>();
        
        for (String genre : sortedMap.keySet()) {
            ArrayList<int[]> m = music.get(genre);
            m.sort((a, b) -> {
                if (b[1] != a[1]) {
                    return b[1] - a[1];
                } else {
                    return a[0] - b[0];
                }
            });

            ans.add(m.get(0)[0]); 
            if (m.size() > 1) {  
                ans.add(m.get(1)[0]); 
            }
        }
        return ans.stream().mapToInt(i -> i).toArray();
    }
}
