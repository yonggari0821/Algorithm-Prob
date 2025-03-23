import java.util.*;

class Solution {
    public int[] solution(String[] operations) {
        StringTokenizer st;
        Queue<Integer> minQ = new PriorityQueue<>();
        Queue<Integer> maxQ = new PriorityQueue<>((o1, o2) -> o2 - o1);
        Map<Integer, Integer> map = new HashMap();

        for (int s = 0; s < operations.length; s++) {
            String str = operations[s];
            
            st = new StringTokenizer(str);
            Character oper = st.nextToken().charAt(0);
            Integer num = Integer.parseInt(st.nextToken());
            
            // 삽입
            if (oper == 'I') {
                map.put(num, map.getOrDefault(num, 0) + 1);
                minQ.offer(num);
                maxQ.offer(num);
            }
            // 추출
            else {
                if (map.size() == 0) continue;
                // 최댓값 추출
                if (num == 1) {
                    int maxVal = maxQ.poll();
                    if (map.containsKey(maxVal)) {
                        int cnt = map.get(maxVal);
                        if (cnt == 1) map.remove(maxVal);
                        else if (cnt > 1) map.put(maxVal, map.get(maxVal) - 1);
                        minQ.remove(maxVal);
                    }
                }
                // 최소값 추출
                else {
                    int minVal = minQ.poll();
                    if (map.containsKey(minVal)) {
                        int cnt = map.get(minVal);
                        if (cnt == 1) map.remove(minVal);
                        else if (cnt > 1) map.put(minVal, map.get(minVal) - 1);
                        maxQ.remove(minVal);
                    }
                }
            }
        } // for            
        if (map.size() > 1) {
            int max = maxQ.peek();
            int min = minQ.peek();
            System.out.println(max + "," + min);
            return new int[] {max, min};
        } else if (map.size() == 1) {
            int val = maxQ.peek();
            System.out.println(val + "," + val);
            return new int[] {val, val};
        } else {
            System.out.println("0,0");
            return new int[] {0, 0};
        }
    }
}
