import java.io.*;
import java.util.*;

public class Main {
	static int[] prefixSum;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		prefixSum = new int[n + 1];
		
		st = new StringTokenizer(br.readLine());
		for (int i=1; i<=n; i++) {
			int num = Integer.parseInt(st.nextToken());
			prefixSum[i] = prefixSum[i-1] + num;
		}
		
		StringBuilder sb = new StringBuilder();
		while (m --> 0) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			
			sb.append(prefixSum[end] - prefixSum[start - 1]).append("\n");
		}
		
		bw.write(sb.toString());
		bw.flush();

	}

}
