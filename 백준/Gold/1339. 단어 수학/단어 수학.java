import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		Map<Character, Integer> map = new HashMap<>();
		List<String> list = new ArrayList<>();
		
		for (int i=0; i<n; i++) {
			String s = br.readLine();
			list.add(s);
			int len = s.length();
			for (int j=0; j<len; j++) {
				char c = s.charAt(j);
				int weight = (int) Math.pow(10, len-j-1);
				map.put(c, map.getOrDefault(c, 0) + weight);
			}
		}
		
		List<Integer> weights = new ArrayList<>(map.values());
		weights.sort(Collections.reverseOrder());
		
		int num = 9, answer = 0;
		for (int weight : weights) {
			answer += weight * num--;
		}
		System.out.print(answer);
	}
}
