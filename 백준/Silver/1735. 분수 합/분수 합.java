import java.io.*;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int[] up = new int[2];
		int[] down = new int[2];
		for (int i=0; i<2; i++) {
			st = new StringTokenizer(br.readLine());
			up[i] = Integer.parseInt(st.nextToken());
			down[i] = Integer.parseInt(st.nextToken());
		}
		
		int gcd = gcd(down[0], down[1]);
		int t1 = down[0] / gcd;
		int t2 = down[1] / gcd;
		
		int bottom = gcd * t1 * t2;
		int top = up[0] * t2 + up[1] * t1;
		
		int l = gcd(bottom, top);
		
		
		System.out.println(top/l + " " + bottom/l);
		

	}
	
	static int gcd(int i, int j) {
		if (j == 0) return i;
		return gcd(j, i%j);
	}

}
