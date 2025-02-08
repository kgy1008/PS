import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		Deque<String> stack = new ArrayDeque<>();
		while (n --> 0) {
			String s = br.readLine();
			if (s.equals("size")) {
				System.out.println(stack.size());
			} else if (s.equals("empty")) {
				System.out.println(stack.isEmpty() ? 1 : 0);
			} else if (s.equals("front")) {
				if (stack.isEmpty()) {
					System.out.println(-1);
				} else {
					System.out.println(stack.peek());
				}
			} else if (s.equals("back")){
				if (stack.isEmpty()) {
					System.out.println(-1);
				} else {
					System.out.println(stack.peekLast());
				}
			} else if (s.equals("pop")){
				if (stack.isEmpty()) {
					System.out.println(-1);
				} else {
					System.out.println(stack.poll());
				}
			}
			else {
				String[] arr = s.split(" ");
				stack.offer(arr[1]);
			}
		}
	}

}
