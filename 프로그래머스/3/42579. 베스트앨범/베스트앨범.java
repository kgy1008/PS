import java.util.*;
import java.util.stream.*;

class Solution {
    public int[] solution(String[] genres, int[] plays) {
        HashMap<String, ArrayList<int[]>> record = new HashMap<>();
        HashMap<String, Integer> playCount = new HashMap<>();

        for (String genre : genres) {
            if (!record.containsKey(genre)) {
                record.put(genre, new ArrayList<>());
                playCount.put(genre, 0);
            }
        }

        for (int i = 0; i < genres.length; i++) {
            record.get(genres[i]).add(new int[]{i, plays[i]});
            playCount.put(genres[i], playCount.get(genres[i]) + plays[i]);
        }

        Stream<Map.Entry<String, Integer>> sortedGenre = playCount.entrySet().stream()
            .sorted((o1, o2) -> Integer.compare(o2.getValue(), o1.getValue()));

        ArrayList<Integer> answer = new ArrayList<>();

        sortedGenre.forEach(entry -> {
            Stream<int[]> sortedSongs = record.get(entry.getKey()).stream()
                .sorted((o1, o2) -> {
                    if (o2[1] == o1[1]) {
                        return Integer.compare(o1[0], o2[0]);
                    }
                    return Integer.compare(o2[1], o1[1]);
                })
                .limit(2);

            sortedSongs.forEach(song -> answer.add(song[0]));
        });

        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
}
