import java.util.*;

class Solution {
    int[] parent;
    
    public int solution(int n, int[][] computers) {
        parent = new int[n];  
        for (int i=0; i<n; i++) {
            parent[i] = i;
        }
        
        for (int i=0; i<n; i++) {  
            for (int j=i+1; j<n; j++) {
                if (computers[i][j]==1) union(i,j);
            }
        }
        
        HashSet<Integer> answer = new HashSet<>();
        for (int i=0; i<n; i++) {
            answer.add(find(i));  
        }
        
        return answer.size();        
    }
    
    private int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]); 
    }
    
    private void union(int x, int y) {
        int root1 = find(x);
        int root2 = find(y);
        parent[root2] = root1; 
    }
}
