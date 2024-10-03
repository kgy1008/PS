import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        ArrayDeque<Integer> truck = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            truck.offer(Integer.parseInt(st.nextToken()));
        }

        int time = 0;
        int weight = 0;
        ArrayDeque<Integer> tunnel = new ArrayDeque<>();
        for(int i =0; i<w ; i++) {
            tunnel.offer(0);
        }

        while(!tunnel.isEmpty()) {
            time++;
            weight -= tunnel.poll();
            if(!truck.isEmpty()) {
                if(truck.peek() + weight <= l) {
                    weight+=truck.peek();
                    tunnel.offer(truck.poll());
                }
                else{
                    tunnel.offer(0);
                }
            }
        }
        System.out.println(time);
    }
}
