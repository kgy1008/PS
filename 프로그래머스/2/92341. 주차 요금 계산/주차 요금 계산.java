import java.util.*;

class Solution {
    public int[] solution(int[] fees, String[] records) {
        Map<String, Integer> totalParkingTime = new HashMap<>();
        Map<String, Integer> inCar = new HashMap<>();

        for (String record : records) {
            String[] info = record.split(" ");
            String carNumber = info[1];
            String status = info[2];

            if ("IN".equals(status)) {
                inCar.put(carNumber, toMinute(info[0]));
            } else {
                int inTime = inCar.get(carNumber);
                int outTime = toMinute(info[0]);
                int totalTime = outTime - inTime;

                totalParkingTime.put(carNumber, totalParkingTime.getOrDefault(carNumber, 0) + totalTime);
                inCar.remove(carNumber);
            }
        }

        int defaultTime = toMinute("23:59");
        for (String carNumber : inCar.keySet()) {
            int inTime = inCar.get(carNumber);
            int totalTime = defaultTime - inTime;
            totalParkingTime.put(carNumber, totalParkingTime.getOrDefault(carNumber, 0) + totalTime);
        }

        TreeMap<String, Integer> finalFees = new TreeMap<>();
        for (Map.Entry<String, Integer> entry : totalParkingTime.entrySet()) {
            String carNumber = entry.getKey();
            int totalTime = entry.getValue();
            finalFees.put(carNumber, calculate(totalTime, fees));
        }

        int[] result = new int[finalFees.size()];
        int idx = 0;
        for (int price : finalFees.values()) {
            result[idx++] = price;
        }

        return result;
    }

    private static int calculate(int time, int[] fees) {
        int defaultTime = fees[0];
        int defaultMoney = fees[1];
        int timeUnit = fees[2];
        int moneyUnit = fees[3];

        if (time <= defaultTime) {
            return defaultMoney;
        }

        int fee = defaultMoney;
        time -= defaultTime;
        int cnt = (int) Math.ceil((double) time / timeUnit);
        fee += (cnt * moneyUnit);

        return fee;
    }

    private static int toMinute(String time) {
        String[] times = time.split(":");
        int hour = Integer.parseInt(times[0]);
        int minute = Integer.parseInt(times[1]);
        return hour * 60 + minute;
    }
}