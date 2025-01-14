import java.util.*;

class Solution {
    public int solution(int n, int[][] costs) {
        int[] parent = new int[n];
        for (int i=0; i<n; i++) {
            parent[i] = i;
        }
        
        List<Node> edges = new ArrayList<>();
        
        for (int[] cost : costs) {
            edges.add(new Node(cost[0], cost[1], cost[2]));
        }
        
        edges.sort((o1, o2) -> Integer.compare(o1.cost, o2.cost));
        
        int result = 0;
        
        for (Node edge : edges) {
            if (find(parent, edge.start) != find(parent, edge.end)) {
                union(parent, edge.start, edge.end);
                result += edge.cost;
            }
        }
        return result;
    }
    
    static int find(int[] parent, int x) {
        if (parent[x] == x) {
            return parent[x];
        }
        parent[x] = find(parent, parent[x]);
        return parent[x];
    }
    
    static void union(int[] parent, int a, int b) {
        a = find(parent, a);
        b = find(parent, b);
        if (a < b) {
            parent[b] = a;
        } else {
            parent[a] = b;
        }
    }
    
    static class Node {
        int start;
        int end;
        int cost;
        
        Node(int start, int end, int cost) {
            this.start = start;
            this.end = end;
            this.cost = cost;
        }
    }
}