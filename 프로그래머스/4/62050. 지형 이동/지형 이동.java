import java.util.*;

class Solution {
    private static class Node {
        int x, y, cost;
        public Node(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }
    } 
    
    public int solution(int[][] land, int height) {
        int answer = 0;
        int n = land.length;
        
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};
        boolean[][] visited = new boolean[n][n];
        
        PriorityQueue<Node> pq = new PriorityQueue<>((o1,o2) -> Integer.compare(o1.cost, o2.cost));
        pq.add(new Node(0,0,0));
        
        while(!pq.isEmpty()) {
            Node now = pq.poll();
            
            if (visited[now.x][now.y]) continue;
            
            visited[now.x][now.y] = true;
            answer += now.cost;
            
            for (int i=0; i<4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];
                
                if(!(0<=nx && nx<n && 0<=ny && ny<n)) continue;
                
                int tempCost = Math.abs(land[now.x][now.y] - land[nx][ny]);
                int newCost = tempCost > height ? tempCost : 0;
                
                pq.add(new Node(nx, ny, newCost));
            }
        }
        
        return answer;
    }
}