class Solution {
    public int[] solution(int n, long left, long right) {
        int size = (int) (right - left + 1);
        int[] answer = new int[size];

        for (int i = 0; i < size; i++) {
            long index = left + i;
            answer[i] = (int) (Math.max(index / n, index % n) + 1);
        }

        return answer;
    }
}