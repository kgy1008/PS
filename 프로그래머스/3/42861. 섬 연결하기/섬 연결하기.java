import java.util.*;

class Solution {
    
    public int solution(int n, int[][] costs) {
        int answer = 0;
        int[] parent = new int[n];
        
        for (int i = 0; i<n; i++) {
            parent[i] = i;
        }
        
        Arrays.sort(costs, (o1, o2) -> Integer.compare(o1[2], o2[2]));
        
        for (int[] cost : costs) {
            if (find(cost[0], parent) != find(cost[1], parent)) {
                union(cost[0], cost[1], parent);
                answer += cost[2];
            }
        }
        return answer;
    }
    
    private static int find(int x, int[] parent) {
        if (parent[x] == x) return x;
        return find(parent[x], parent);
    }

    private static void union(int x, int y, int[] parent) {
        parent[find(y, parent)] = find(x, parent);
    }
}