import java.io.*;
import java.util.*;

public class Main {
	
	static int[] dx = {0,0,1,-1};
	static int[] dy = {1,-1,0,0};
	static long[][] dist;
	static int[][] map;
	static List<Edge> edges;
	static int w, h;
	static final long INF = Long.MAX_VALUE;
   
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        
        while (true) {
        	st = new StringTokenizer(br.readLine());
        	w = Integer.parseInt(st.nextToken());
        	h = Integer.parseInt(st.nextToken());
        	
        	if (w == 0 && h == 0) {
        		break;
        	}
        	
        	edges = new ArrayList<>();
        	dist = new long[w][h];
        	map = new int[w][h];
        	map[w-1][h-1] = 2; // 출구
        	
        	for (int i=0; i<w; i++) {
        		Arrays.fill(dist[i], INF);
        	}
        	dist[0][0] = 0L;
        	
        	int g = Integer.parseInt(br.readLine()); // 묘비의 개수
        	while (g --> 0) {
        		st = new StringTokenizer(br.readLine()); // 묘비의 위치를 나타내는 x,y
        		int x = Integer.parseInt(st.nextToken());
        		int y = Integer.parseInt(st.nextToken());
        		
        		map[x][y] = -1; // 묘비
        	}
        	
        	int e = Integer.parseInt(br.readLine()); // 귀신 구멍의 수
        	while (e --> 0) {
        		st = new StringTokenizer(br.readLine());
        		int x1 = Integer.parseInt(st.nextToken());
        		int y1 = Integer.parseInt(st.nextToken());
        		map[x1][y1] = 1;  // 귀신 구멍
        		
        		int x2 = Integer.parseInt(st.nextToken());
        		int y2 = Integer.parseInt(st.nextToken());
        		int time = Integer.parseInt(st.nextToken());
        		
        		edges.add(new Edge(new Node(x1,y1), new Node(x2,y2), time));  // 단방향 간선 저장
        	}
        	
        	// 모든 간선 추가
        	for (int i=0; i<w; i++) {
        		for (int j=0; j<h; j++) {
        			if (map[i][j] != 0) { // 현재 위치가 잔디가 아니면 건너뛴다
        				continue;
        			}
        			
        			for (int k=0; k<4; k++) {
            			int nx = i + dx[k];
            			int ny = j + dy[k];
            			
            			if (nx <0 || nx >= w || ny<0 || ny >= h) {
            				continue;
            			}
            			
            			if (map[nx][ny] == -1) { // 묘비면 건너뛴다.
            				continue;
            			}
            			
            			edges.add(new Edge(new Node(i,j), new Node(nx, ny), 1));
            		}
        		}
        	}
        
        	boolean hasCycle = bellmanFord();
        	if (hasCycle) {
        		sb.append("Never\n");
        	} else {
        		if (dist[w-1][h-1] == INF) {
        			sb.append("Impossible\n");
        		} else {
        			sb.append(dist[w-1][h-1]).append("\n");
        		}
        	}
        }
        
        bw.write(sb.toString());
        bw.flush();
    }
    
    static boolean bellmanFord() {
        for (int i = 0; i < w*h; i++) { // (정점의 개수 - 1) 만큼 반복 마지막 반복에서는 
            for (int j = 0; j < edges.size(); j++) {  // 간선의 개수만큼 반복
                Edge edge = edges.get(j); // 현재 간선

                // 현재 간선의 출발 정점에서의 거리가 INF가 아닌 경우에만 갱신
                if (dist[edge.from.x][edge.from.y] != INF && dist[edge.to.x][edge.to.y] > dist[edge.from.x][edge.from.y] + edge.cost) {
                	dist[edge.to.x][edge.to.y] = dist[edge.from.x][edge.from.y] + edge.cost;

                    if (i == w*h - 1) { // 음수 사이클 존재
                       return true;
                    }
                }
            }
        }
        return false;
    }
    
    static class Node {
    	int x;
    	int y;
    	
    	Node (int x, int y) {
    		this.x = x;
    		this.y = y;
    	}
    }
    
    static class Edge {
    	Node from;
    	Node to;
    	int cost;
    	
    	Edge(Node from, Node to, int cost) { 
    		this.from = from;
    		this.to = to;
    		this.cost = cost;
    	}
    }
}
