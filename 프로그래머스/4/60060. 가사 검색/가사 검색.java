import java.util.*;

class Solution {
    public int[] solution(String[] words, String[] queries) {
        int[] answer = new int[queries.length];
        HashMap<Integer, List<String>> map = new HashMap<>(); // 문자열 길이 - 문자열
        HashMap<Integer, List<String>> reverseMap = new HashMap<>();
        
        for (String word : words) {
            int len = word.length();
            // 정방향
            List<String> list1 = map.getOrDefault(len, new ArrayList<>());
            list1.add(word);
            map.put(len, list1);
            
            // 역방향
            List<String> list2 = reverseMap.getOrDefault(len, new ArrayList<>());
            list2.add(new StringBuilder(word).reverse().toString());
            reverseMap.put(len, list2);
        }
        
        for (int key : map.keySet()) {
            List<String> list1 = map.get(key);
            list1.sort((o1,o2) -> o1.compareTo(o2)); // 사전 순 정렬
            
            List<String> list2 = reverseMap.get(key);
            list2.sort((o1,o2) -> o1.compareTo(o2));
        }
        
        for (int i=0; i<queries.length; i++) {
            String query = queries[i];
            int len = query.length();

            String start = query.replace('?', 'a');
            String end = query.replace('?', 'z');

            int startIdx, endIdx;
            List<String> list;

            if (query.charAt(0) == '?') { // 역방향
                list = reverseMap.getOrDefault(len, new ArrayList<>());
                if (list.isEmpty()) {
                    answer[i] = 0;
                    continue;
                }

                query = new StringBuilder(query).reverse().toString();
                start = new StringBuilder(start).reverse().toString();
                end = new StringBuilder(end).reverse().toString();

            } else { // 정방향
                list = map.getOrDefault(len, new ArrayList<>());
                if (list.isEmpty()) {
                    answer[i] = 0;
                    continue;
                }
            }

            startIdx = lowerBound(list, start);
            endIdx = upperBound(list, end); 

            answer[i] = endIdx - startIdx;
        }
        
        return answer;
    }
    
    private static int lowerBound(List<String> list, String target) {
        int low = 0;
        int high = list.size();
        int mid = 0;
        
        while (low < high) {
            mid = (low + high) / 2;
            String str = list.get(mid);
            
            if (str.compareTo(target) < 0) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return high;
    }
    
    private static int upperBound(List<String> list, String target) {
        int low = 0;
        int high = list.size();

        while (low < high) {
            int mid = (low + high) / 2;
            if (list.get(mid).compareTo(target) <= 0) { 
                low = mid + 1;
            } else { 
                high = mid;
            }
        }
        return high;
    }
}