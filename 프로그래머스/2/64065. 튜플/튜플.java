import java.util.*;

class Solution {
    public int[] solution(String s) {
        Set<Integer> ans = new LinkedHashSet<>();
        
        s = s.replaceAll("\\{\\{", "").replaceAll("\\}\\}", "");
        String[] groups = s.split("\\},\\{");
        
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();

        for (String group : groups) {
            ArrayList<Integer> tempList = new ArrayList<>();
            for (String num : group.split(",")) {
                tempList.add(Integer.parseInt(num));
            }
            result.add(tempList);
        }
        
        Collections.sort(result, (a, b) -> Integer.compare(a.size(), b.size()));
        
        for (ArrayList<Integer> r : result) {
            for (int i : r) {
                ans.add(i);
            }
        }
        return ans.stream().mapToInt(Integer::intValue).toArray();
    }
}