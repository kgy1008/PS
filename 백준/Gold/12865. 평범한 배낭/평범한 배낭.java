import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// Knapsack G5
public class Main {
    static class Item{
        int w;
        int v;
        public Item(int w, int v){
            this.w = w;
            this.v = v;
        }
    }

    static int N, K;
    static Item[] items;
    static int[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        items = new Item[N+1];
        dp = new int[N+1][K+1];

        int w, v;
        for(int i=1; i<=N; i++){
            st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());
            items[i] = new Item(w, v);
        }

        knapsack();
    }

    static void knapsack(){
        for(int k=1; k<=K; k++){
            for(int i=1; i<=N; i++){
                // 현재 아이템(items[i])을 담을 수 없으면 이전 행의 값으로 dp 배열 갱신
                if(k - items[i].w < 0) {
                    dp[i][k] = dp[i-1][k];
                }
                // 현재 아이템을 담을 수 있으면
                else{
                    // 이전 행의 값
                    // vs.
                    // 현재 아이템을 담고, 남는 무게만큼 담을 수 있는 이전행의 값(이전행의 값은 이전까지의 모든 아이템들을 고려한 max값이니까)
                    dp[i][k] = Math.max(dp[i-1][k], items[i].v + dp[i-1][k-items[i].w]);
                }
            }
        }

        System.out.println(dp[N][K]);
    }

}
