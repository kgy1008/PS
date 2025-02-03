import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static long[] pibo = new long[91];
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		pibo[1] = pibo[2] = 1L;
		long answer = dfs(n);
		System.out.println(answer);
	}
	
	 static long dfs(int n) {
	        if (n==1 || n==2 || pibo[n] > 0) return pibo[n];
	        return pibo[n] = dfs(n-1)+dfs(n-2);
	 }
}
