import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	static String[] arr;
	static int n;
	static int k;
	static boolean[] visited = new boolean[26];
	static int answer = 0;

	public static void main(String[] args) throws IOException{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		if (k<5) {
			System.out.println(0);
			return;
		} else if (k == 26) {
			System.out.println(n);
			return;
		}
		
		arr = new String[n];
		for (int i=0; i<n; i++) {
			String line = br.readLine();
			line = line.replace("anta", "").replace("tica", "");
			arr[i] = line;
		}
		
		visited['a'-'a'] = true;
		visited['n'-'a'] = true;
		visited['t'-'a'] = true;
		visited['c'-'a'] = true;
		visited['i'-'a'] = true;
		
		solve(0, k-5);
		System.out.println(answer);
	}
	
	static void solve(int idx, int k) {
		if (k == 0) {
			check();
			return;
		}

		for (int i=idx; i<26; i++) {
			if (!visited[i]) {
				visited[i] = true;
				solve(i, k-1);
				visited[i] = false;
			}
		}
	}
	
	static void check() {
		int tmp = 0;
		for(int i = 0; i < n; i++) { 
            boolean read = true;
            for(int j = 0; j < arr[i].length(); j++) {
                if(!visited[arr[i].charAt(j) - 'a']) {
                    read = false;
                    break;
                }
            }
            if(read) tmp++;
        }
		answer = Math.max(answer, tmp);
	}
}
