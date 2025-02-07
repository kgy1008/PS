import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {
	static boolean[] isPrime = new boolean[1000000];
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		Arrays.fill(isPrime, true);
		getPrimeNum();

		while(true) {
			int n = Integer.parseInt(br.readLine());
			if (n==0) break;
			
			boolean isWrong = true;
			int start = 3;
			while (start <= n / 2) {
				if (isPrime[start]) {
					int target = n - start;
					if (isPrime[target]) {
						bw.write(n + " = " + start + " + " + target);
						bw.newLine();
						isWrong = false;
						break;
					}
				}
				start++;
			}
			if (isWrong) {
				bw.write("Goldbach's conjecture is wrong.");
                bw.newLine();
			}
		}
		
		br.close();
        bw.flush();
        bw.close();
	}
	
	 static void getPrimeNum() {
			isPrime[0]=isPrime[1]=false; 
			
			for(int i=2;i*i<1000000;i++){
				if(isPrime[i]){
					for(int j=i*i;j<1000000;j+=i) { 
						isPrime[j] = false;
					}
				}
			}
		}
}
