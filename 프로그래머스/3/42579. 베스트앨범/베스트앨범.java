import java.util.*;
import java.util.stream.*;

class Solution {
    public int[] solution(String[] genres, int[] plays) {
        Map<String, Integer> total = new HashMap<>();
        Map<String, List<Music>> map = new HashMap<>();
        
        for (int i=0; i<genres.length; i++) {
            total.put(genres[i], total.getOrDefault(genres[i], 0) + plays[i]);
            map.put(genres[i], new ArrayList<>());
        }
        
        for (int i=0; i<genres.length; i++) {
            List<Music> list = map.get(genres[i]);
            list.add(new Music(i, plays[i]));
        }
        
        for (String key : map.keySet()) {
            List<Music> list = map.get(key);
            list.sort((o1,o2) -> Integer.compare(o2.play, o1.play));
        }
        
        List<String> sortedGenres = total.entrySet().stream()
            .sorted((o1, o2) -> Integer.compare(o2.getValue(), o1.getValue()))
            .map(entry -> entry.getKey())
            .collect(Collectors.toList());

        List<Integer> answer = new ArrayList<>();
        for (String g : sortedGenres) {
            List<Music> list = map.get(g);
            answer.addAll(list.stream().limit(2)
                          .map(music -> music.id)
                          .collect(Collectors.toList()));
        }
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
    
    static class Music {
        int id;
        int play;
        
        Music(int id, int play) {
            this.id = id;
            this.play = play;
        }
    }
}