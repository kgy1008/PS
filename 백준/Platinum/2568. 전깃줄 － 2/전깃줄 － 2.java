import java.io.*;
import java.util.*;

public class Main {
	
	static int[] lis;
	static int[] trace;
	
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        
        int n = Integer.parseInt(br.readLine());
        Line[] lines = new Line[n];
        for (int i=0; i<n; i++) {
        	st = new StringTokenizer(br.readLine());
        	int a = Integer.parseInt(st.nextToken());
        	int b = Integer.parseInt(st.nextToken());
        	
        	lines[i] = new Line(a,b);
        }

        Arrays.sort(lines, (o1,o2) -> Integer.compare(o1.b, o2.b));
        
        int[] num = new int[n];
        for (int i=0; i<n; i++) {
        	num[i] = lines[i].a;
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
        
        int minCount = n - lastIndex - 1;
        bw.write(String.valueOf(minCount));
        bw.write("\n");
        
        Set<Integer> set = new HashSet<>();
        int maxIdx = lastIndex;
        for (int i=n-1; i>=0; i--) {
        	int target = trace[i];
        	if (target == maxIdx) {
        		set.add(num[i]);
        		maxIdx--;
        	}
        }
        
        Arrays.sort(num);
        
        StringBuilder sb = new StringBuilder();
        
        for (int i=0; i<n; i++) {
        	if (!set.contains(num[i])) {
        		sb.append(num[i]).append("\n");
        	}
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
    
    static class Line {
    	int a;
    	int b;
    	
    	Line(int a, int b) {
    		this.a = a;
    		this.b = b;
    	}
    }
}
