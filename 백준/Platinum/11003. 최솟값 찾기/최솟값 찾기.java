import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int l = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		Deque<Node> queue = new ArrayDeque<>();
		StringBuilder sb = new StringBuilder();
		
		for (int i=1; i<=n; i++) {
			int num = Integer.parseInt(st.nextToken());
			
			while (!queue.isEmpty() && queue.peekLast().number > num) {
				queue.pollLast();
			}
			queue.offer(new Node(i, num));
			
			if (!queue.isEmpty() && queue.peek().idx <= i - l) {
				queue.poll();
			} 

			sb.append(queue.peek().number).append(" ");
		}
		System.out.println(sb);
	}
	
	static class Node {
		int idx;
		int number;
		
		Node(int idx, int number) {
			this.idx = idx;
			this.number = number;
		}
	}
}
