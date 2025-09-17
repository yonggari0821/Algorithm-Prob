import java.util.*;

/*
    pop insert 합쳐서 1회 작업으로 간주
    홀 + 홀 = 짝
    짝 + 짝 = 짝
    => 무조건 총합이 짝수여야 함
    
    일단 총합을 구해보자!
    총합을 구할 때 일단 30만 x 10의 9승이 최대이므로 long 타입 필요
    
    총합을 구했으면
*/

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        // 모든 숫자 더해서 짝수인지부터 체크
        long sum1 = 0;
        long sum2 = 0;
        long sum = 0;
        Queue<Integer> q1 = new LinkedList<>();
        Queue<Integer> q2 = new LinkedList<>();
        for (int i = 0; i < queue1.length; i++) {
            sum1 += queue1[i];
            sum2 += queue2[i];
            q1.offer(queue1[i]);
            q2.offer(queue2[i]);
        }
        sum = sum1 + sum2;
        
        // 총합이 짝수가 아니면 같은 수로 만들 수 없음
        if (sum % 2 != 0) return -1;
        
        // 만들 수 있다면 sum1과 sum2에서 하나씩 빼가면서 해보기
        int answer = 0;
        int i1 = 0;
        int i2 = 0;
        while (sum1 != sum2 && !q1.isEmpty() && !q2.isEmpty() && answer != -1) {
            if (sum1 > sum2) {
                sum2 += q1.peek();
                sum1 -= q1.peek();
                q2.offer(q1.poll());
            }
            else {
                sum1 += q2.peek();
                sum2 -= q2.peek();
                q1.offer(q2.poll());
            }
            answer++;
            if (answer >= 600000) answer = -1;
        }
        
        if (sum1 == sum2) return answer;
        else return -1;
    }
}