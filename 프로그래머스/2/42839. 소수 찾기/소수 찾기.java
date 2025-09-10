import java.util.*;

class Solution {
    static Set<Integer> set = new HashSet<>();
    static List<Integer> nums = new ArrayList<>();
    
    public int solution(String numbers) {
        for (int i=0; i<numbers.length(); i++) {
            char c = numbers.charAt(i);
            int num = Integer.parseInt(String.valueOf(c));
            nums.add(num);
        }
        
        for (int i=1; i<= nums.size(); i++) {
            boolean[] visited = new boolean[nums.size()];
            solve(i, new ArrayList<>(), visited);
        }
        
        return set.size();
    }
    
    private static void solve(int size, List<Integer> list, boolean[] visited) {
        if (size == 0) {
            isPrime(list);
            return;
        }
        
        for (int i=0; i<nums.size(); i++) {
            if (!visited[i]) {
                visited[i] = true;
                list.add(nums.get(i));
                
                solve(size-1, list, visited);
                
                visited[i] = false;
                list.remove(list.size()-1);
            }
        }
    }
    
    private static void isPrime(List<Integer> list) {
        StringBuilder sb = new StringBuilder();
        for (int num : list) {
            sb.append(num);
        }
        
        int target = Integer.parseInt(sb.toString());
        
        if (target < 2) {
            return;
        }
        
        for (int i=2; i*i<=target; i++) {
            if (target % i == 0) {
                return;
            }
        }
        set.add(target);
    }
}