import java.util.*;

/*
    차량 별 주차 요금 계싼
    차량 번호 / 내역(입차/출차) / 시각 순으로 보면서 
    
    1) 기본 시간 이하라면 기본 요금
    2) 초과 시 기본요금 + 단위 시간 x 단위 요금 청구
        초과한 시간은 올림 처리
    
    없는 차량이 출차 되는 경우나 이미 있는데 또 입차하는 경우 등은 불가능
    
    => HashMap써서 풀면 될 거 같음
*/

class Use {
    int carNum;
    int time;
    
    Use(int carNum, int time) {
        this.carNum = carNum;
        this.time = time;
    }
}

class Solution {
    
    private static int baseHour;
    private static int baseFee;
    private static int plusHour;
    private static int plusFee;
    private static Map<Integer, Integer> map;
    private static Map<Integer, Integer> ansMap;
    
    private static StringTokenizer st;
    private static int toMinute(String str) {
        st = new StringTokenizer(str, ":");
        int hour = Integer.parseInt(st.nextToken());
        int minute = Integer.parseInt(st.nextToken());
        return minute + 60 * hour;
    }
    
    private static int getFee(int time) {
        if (time <= baseHour) {
            return baseFee;
        }
        else {
            int gap = time - baseHour;
            if (gap % plusHour == 0) return baseFee + plusFee * (gap / plusHour);
            else return baseFee + plusFee * (gap / plusHour + 1);
        }
    }
    
    public int[] solution(int[] fees, String[] records) {
        int[] answer;
        baseHour = fees[0]; // 기본 시간
        baseFee = fees[1]; // 기본 요금
        plusHour = fees[2]; // 추가 단위 시간
        plusFee = fees[3]; // 추가 단위 요금
        map = new HashMap<>(); // 차량 별 시간 map
        ansMap = new HashMap<>(); // 차량 별 누적 시간 map
        
        for (int i = 0; i < records.length; i++) {
            StringTokenizer sts = new StringTokenizer(records[i]);
            int min = toMinute(sts.nextToken());
            int carNum = Integer.parseInt(sts.nextToken());
            int isIn = map.getOrDefault(carNum, -1);
            
            if (isIn == -1) {
                map.put(carNum, min);
                // System.out.println("car #: " + carNum + " is in!");
            }
            else {
                int time = min - isIn;
                int pastTime = ansMap.getOrDefault(carNum, 0);
                ansMap.put(carNum, pastTime + time);
                map.put(carNum, -1);
                // System.out.println("car #: " + carNum + " is out!");
                // System.out.println("car #: " + carNum + " has " + pastTime + " past and now plus " + time);
            }
        }
        
        for (int carN : map.keySet()) {
            if (map.get(carN) != -1) {
                int pastTime = ansMap.getOrDefault(carN, 0);
                ansMap.put(carN, 23 * 60 + 59 - map.get(carN) + pastTime);
                map.put(carN, -1);
            }
        }
        
        PriorityQueue<Use> pq = new PriorityQueue<>(new Comparator<Use>(){
            @Override
            public int compare(Use a, Use b) {
                return a.carNum - b.carNum;
            }
        });
        
        for (int carN : ansMap.keySet()) {
            pq.offer(new Use(carN, ansMap.get(carN)));
        }
        
        answer = new int[pq.size()];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = getFee(pq.poll().time);
        }
        
        return answer;
    }
}