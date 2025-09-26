import java.util.*;

class Solution {
    private static int[] minIntensity;
    
    private static List<Node>[] graph;
    
    private static Set<Integer> summit = new HashSet<>();
    
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        graph = new List[n+1];
        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        minIntensity = new int[n + 1];
        Arrays.fill(minIntensity, Integer.MAX_VALUE);
        
        // 산봉우리
        for (int s : summits) {
            summit.add(s);
        }
        
        // 출입구 
        Set<Integer> gate = new HashSet<>();
        for (int g : gates) {
            gate.add(g);
        }

        // 코스 정보
        for (int[] path : paths) {
            int i = path[0];
            int j = path[1];
            int w = path[2];

            boolean isGateI = gate.contains(i);
            boolean isGateJ = gate.contains(j);
            boolean isSummitI = summit.contains(i);
            boolean isSummitJ = summit.contains(j);
            
            // 1. i -> j 경로
            // i가 산봉우리가 아니거나, i와 j가 둘다 출입구가 아니면 이동 가능
            if (!isSummitI && !isGateJ) {
                graph[i].add(new Node(j, w));
            }
            
            // 2. j -> i 경로
            // j가 산봉우리가 아니거나, i와 j가 둘다 출입구가 아니면 이동 가능
            if (!isSummitJ && !isGateI) {
                graph[j].add(new Node(i, w));
            }
        }
        
        // 다익스트라 
        dijkstra(gates);
        
        // 결과 계산
        int minSummit = Integer.MAX_VALUE;
        int value = Integer.MAX_VALUE;
        
        Arrays.sort(summits); 
        
        for (int summit : summits) {
            int intensity = minIntensity[summit];
            
            if (intensity < value) {
                value = intensity;
                minSummit = summit;
            } else if (intensity == value) {
                if (summit < minSummit) {
                    minSummit = summit;
                }
            }
        }
        
        return new int[]{minSummit, value};
    }

    private static void dijkstra(int[] gates) {
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt((Edge o) -> o.intensity));

        // 모든 출입구를 시작점으로 설정
        for (int gate : gates) {
            minIntensity[gate] = 0; 
            pq.add(new Edge(gate, 0));
        }

        while (!pq.isEmpty()) {
            Edge current = pq.poll();
            int u = current.index;
            int intensity = current.intensity;

            if (intensity > minIntensity[u]) {
                continue;
            }

            // 산봉우리에 도착하면 종료
            if (summit.contains(u)) {
                continue;
            }

            for (Node neighbor : graph[u]) {
                int v = neighbor.to;
                int weight = neighbor.weight;
                
                int newIntensity = Math.max(intensity, weight);

                if (newIntensity < minIntensity[v]) {
                    minIntensity[v] = newIntensity;
                    pq.add(new Edge(v, newIntensity));
                }
            }
        }
    }
    
    static class Node {
        int to;
        int weight;
        
        public Node(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    static class Edge {
        int index;
        int intensity;
        
        public Edge(int index, int intensity) {
            this.index = index;
            this.intensity = intensity;
        }
    }
}