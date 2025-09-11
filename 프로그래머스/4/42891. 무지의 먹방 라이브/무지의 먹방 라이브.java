import java.util.*;

class Solution {
    public int solution(int[] food_times, long k) {
        long sum = 0L;
        for (int time : food_times) {
            sum += time;
        }
        if (sum <= k) {
            return -1;
        }

        PriorityQueue<Food> pq = new PriorityQueue<>((o1, o2) -> Long.compare(o1.time, o2.time));
        for (int i = 0; i < food_times.length; i++) {
            pq.offer(new Food(i + 1, food_times[i]));
        }

        long totalTime = 0L;
        long previousTime = 0L;

        while (totalTime + (pq.peek().time - previousTime) * pq.size() <= k) {
            Food food = pq.poll();
            long timeDiff = food.time - previousTime;
            totalTime += timeDiff * (pq.size() + 1);
            previousTime = food.time;
        }

        List<Food> list = new ArrayList<>(pq);
        list.sort((o1, o2) -> Integer.compare(o1.num, o2.num));

        return list.get((int) ((k - totalTime) % list.size())).num;
    }

    static class Food {
        int num;
        long time;

        Food(int num, long time) {
            this.num = num;
            this.time = time;
        }
    }
}