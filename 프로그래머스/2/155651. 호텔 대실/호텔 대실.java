import java.util.*;

/*
    필요한 거
    1) String -> 시간 출력하는 함수
    2) 겹치는 경우 객실 수 필요
    
    1)은 쉬움
    핵심은
    2)를 어떻게 구현할 것인가?임
    
    입실 시간을 기준으로 오름차순 정렬 후
*/

class Dayuse {
    int s;
    int e;
    
    Dayuse(int s, int e) {
        this.s = s;
        this.e = e;
    }
}

class Solution {
    
    private static StringTokenizer st;
    
    private static int makeTime(String str, boolean isEnd) {
        st = new StringTokenizer(str, ":");
        int hourAsMinute = Integer.parseInt(st.nextToken()) * 60;
        int minute = Integer.parseInt(st.nextToken());
        int val = hourAsMinute + minute + (isEnd ? 10 : 0);
        // System.out.println(str + " turned into " + val);
        return val;
    }
    
    public int solution(String[][] book_time) {
        int roomCnt = 0;
        
        PriorityQueue<Dayuse> pq = new PriorityQueue<>(new Comparator<Dayuse>(){
            @Override
            public int compare(Dayuse a, Dayuse b) {
                return a.s - b.s;
            }
        });
        
        for (int i = 0; i < book_time.length; i++) {
            int s = makeTime(book_time[i][0], false);
            int e = makeTime(book_time[i][1], true);
            pq.offer(new Dayuse(s, e));
        }
        
        PriorityQueue<Integer> endq = new PriorityQueue<>(new Comparator<Integer>(){
            @Override
            public int compare(Integer a, Integer b) {
                return a - b;
            }
        });
        
        
        // 현재 종료시간 기준 오름차순으로 정렬되어 있는 대실시간들을 꺼내서
        // 이전 대실 종료 시간보다 현재 대실 시작 시간이 작다면
        // endq로 넣고 endq size의 최대값과 현재 최대 방 수 비교해서 리뉴얼
        // 크다면 endq에서 하나씩 빼보기
        endq.offer(0);
        while (!pq.isEmpty()) {
            Dayuse cur = pq.poll();
            // System.out.println(cur.s + " / " + cur.e);
            
            while (!endq.isEmpty() && endq.peek() <= cur.s) {
                // System.out.println(endq.poll() + " is out!");
                endq.poll();
            }
            
            // System.out.println(cur.e + "is in!");
            endq.offer(cur.e);
            roomCnt = Math.max(roomCnt, endq.size());
        }
        
        return roomCnt;
    }
}