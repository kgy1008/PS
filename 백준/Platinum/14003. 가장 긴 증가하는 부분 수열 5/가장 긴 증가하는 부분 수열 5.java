import java.io.*;
import java.util.*;

public class Main {
	
	static int[] lis;
	static int[] trace;
	
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int n = Integer.parseInt(br.readLine());
        int[] num = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        for (int i=0; i<n; i++) {
        	num[i] = Integer.parseInt(st.nextToken());
        }
        
        trace = new int[n];
        lis = new int[n];
        lis[0] = num[0];
        int lastIndex = 0;
        
        for (int i=1; i<n; i++) {
        	if (lis[lastIndex] < num[i]) {
        		lis[++lastIndex] = num[i];
        		trace[i] = lastIndex;
        	} else {
        		int idx = lowerBound(num[i], lastIndex);
        		lis[idx] = num[i];
        		trace[i] = idx;
        	}
        }
        
        bw.write(String.valueOf(lastIndex + 1));
        bw.write("\n");
        
        Deque<Integer> stack = new ArrayDeque<>();
        int maxIdx = lastIndex;
        for (int i=n-1; i>=0; i--) {
        	int target = trace[i];
        	if (target == maxIdx) {
        		stack.push(num[i]);
        		maxIdx--;
        	}
        }
        
        StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty()) {
        	sb.append(stack.pop()).append(" ");
        }
        
        bw.write(sb.toString());
        bw.flush();

        bw.close();
        br.close();
    }
    
    static int lowerBound(int target, int right) {
    	int left = 0;
    	
    	while (left < right) {
    		int mid = (left + right) / 2;
    		
    		if (lis[mid] < target) {
    			left = mid + 1;
    		} else {
    			right = mid;
    		}
    	}
    	return right;
    }
}
