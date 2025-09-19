import java.util.*;

class Solution {
    public int[] solution(int[][] edges) {
        int maxNode = 0;
        for (int[] edge : edges) {
            maxNode = Math.max(maxNode, Math.max(edge[0], edge[1]));
        }

        int[] inDegree = new int[maxNode + 1]; // 진입차수
        int[] outDegree = new int[maxNode + 1]; // 진출차수

        for (int[] edge : edges) {
            outDegree[edge[0]]++;
            inDegree[edge[1]]++;
        }

        int generatedNode = 0;
        int donutCount = 0;
        int barCount = 0;
        int eightCount = 0;

        for (int i = 1; i <= maxNode; i++) {
            if (outDegree[i] >= 2 && inDegree[i] == 0) {
                generatedNode = i;
            } else if (outDegree[i] == 0 && inDegree[i] >= 1) {
                barCount++;
            } else if (outDegree[i] >= 2 && inDegree[i] >= 2) {
                eightCount++;
            }
        }
        
        donutCount = outDegree[generatedNode] - barCount - eightCount;

        return new int[]{generatedNode, donutCount, barCount, eightCount};
    }
}