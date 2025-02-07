import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int[] dp = new int[1001];

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		dp[1]= 3;
		init();
		
		int c = Integer.parseInt(br.readLine());
		while (c --> 0) {
			int n = Integer.parseInt(br.readLine());
			System.out.println(dp[n]);
		}
	}
	
	static void init() {
		for (int i=2; i<1001; i++) {
			int answer = 0;
			for (int j=1; j<i; j++) {
				if (gcd(i, j) == 1) {
					answer++;
				}
			}
			dp[i] = dp[i-1] + answer * 2;
		}
	}
	
	static int gcd(int i, int j) {
		if (j == 0) return i;
		return gcd(j, i%j);
	}
}
