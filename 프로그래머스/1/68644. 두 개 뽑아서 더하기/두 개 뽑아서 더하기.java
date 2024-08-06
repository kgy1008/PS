import java.util.HashSet;
import java.util.Arrays;
import java.util.stream.Collectors;

class Solution {
    public int[] solution(int[] numbers) {
        HashSet<Integer> answer = new HashSet<>();

        Arrays.sort(numbers);
        
        for (int i = 0; i < numbers.length - 1; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                answer.add(numbers[i] + numbers[j]);
            }
        }
        
        return answer.stream()
                     .sorted()
                     .mapToInt(Integer::intValue)
                     .toArray();
    }
}
