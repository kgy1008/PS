import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class Main {
 
    public static void main(String[] args) throws IOException {
 
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 
        int N = Integer.parseInt(br.readLine());
 
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] size = new int[6];
 
        for(int i=0; i<size.length; i++)
            size[i] = Integer.parseInt(st.nextToken());
 
        st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());   
        int P = Integer.parseInt(st.nextToken());   
 
        int answer = 0; 
        for (int j=0; j<6; j++) {
            answer += size[j] / T;
            if(size[j] % T != 0)
                answer++;
        }
        System.out.println(answer);
        StringBuilder sb = new StringBuilder();
        sb.append(N / P).append(" ").append(N % P);
        System.out.println(sb);
    }
}