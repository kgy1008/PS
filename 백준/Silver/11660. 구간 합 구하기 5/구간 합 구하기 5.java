import java.io.*;
import java.util.*;

public class Main {
	
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());  // 표의 크기
        int m = Integer.parseInt(st.nextToken());  // 합을 구해야 하는 횟수
        
        int[][] num = new int[n+1][n+1];
        
        for (int i=1; i<=n; i++) {
        	st = new StringTokenizer(br.readLine());
        	for (int j=1; j<=n; j++) {
        		num[i][j] = Integer.parseInt(st.nextToken());
        	}
        }
        
        int[][] prefixSum = new int[n+1][n+1];
        for (int i=1; i<=n; i++) {
        	for (int j=1; j<=n; j++) {
        		prefixSum[i][j] = prefixSum[i-1][j] + prefixSum[i][j-1] - prefixSum[i-1][j-1] + num[i][j];
        	}
        }
        
        StringBuilder sb = new StringBuilder();
        while (m --> 0) {
        	st = new StringTokenizer(br.readLine());
        	int x = Integer.parseInt(st.nextToken());
        	int y = Integer.parseInt(st.nextToken());
        	int a = Integer.parseInt(st.nextToken());
        	int b = Integer.parseInt(st.nextToken());
        	
        	int answer = prefixSum[a][b] - prefixSum[x-1][b] - prefixSum[a][y-1] + prefixSum[x-1][y-1];
        	sb.append(answer).append("\n");
        }
        
        bw.write(sb.toString());       
        bw.flush();
        
        bw.close();
        br.close();
    }
}
