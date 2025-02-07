import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	static int divider = -1;
	static int out = 0;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int[] num = new int[n];
		for (int i=0; i<n; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}

		int[] lgcd = new int[n];
		int[] rgcd = new int[n];
		
		for (int i=0; i<n; i++) {
			if (i == 0) {
				lgcd[i] = num[i];
			} else {
				lgcd[i] = gcd(num[i], lgcd[i-1]);
			}
		}
		
		for (int i=n-1; i>=0; i--) {
			if (i == n-1) {
				rgcd[i] = num[i];
			} else {
				rgcd[i] = gcd(num[i], rgcd[i+1]);
			}
		}
		
		for (int i=0; i<n; i++) {
			int tmp;
			if (i==0) {
				tmp = rgcd[i+1];
			} else if (i==n-1) {
				tmp = lgcd[i-1];
			} else {
				tmp = gcd(rgcd[i+1], lgcd[i-1]);
			}
			
			if (num[i] % tmp != 0) {
				int k = Math.max(divider, tmp);
				if (k == tmp) {
					divider = tmp;
					out = num[i];
				}
			}
		}
		if (divider == -1) {
			System.out.println(-1);
			return;
		}
		System.out.printf("%d %d", divider, out);
	}
	
	static int gcd(int i, int j) {
		if (j == 0) return i;
		return gcd(j, i%j);
	}
}
