import java.util.*;

/*

n: 1~10억
모든 사람이 심사를 받는데 걸리는 시간을 최소로
times: 한 명을 심사하는 데 걸리는 시간
times[i] == 1 ~ 10억
심사관 수 == times.length : 1~100000

<극한 케이스>
10억명이 심사하는데 10억분이 걸리는 심사관 1명한테 심사받았을 때
=> 10억 x 10억 == 100경

log2의 100경은 약 59.794705707972525
Math.log(1000000000000000000L) / Math.log(2)

long타입의 최댓값은
	9223372036854775807
=> 약 922경
커버 가능함!

*/
class Solution {
    
    static long binarySearch() {
        long l = 1;
        long r = 1000000000L * 1000000000L;
        // 만족하는 최소값 => LowerBound
        // tt의 각 값으로 mid를 나눈 값 => 해당 심사관이 해당 시간동안 심사 가능 한 수
        // 이 값을 다 더해서 
        // 1) n보다 같거나 크면 시간을 더 줄여봐도 됨 => r = mid;
        // 2) n보다 작으면 시간을 늘려야 함 => l = mid + 1;
        while (l < r) {
            long mid = (l + r) / 2;
            long tmp = 0;
            for (int i = 0; i < examinerCnt; i++) {
                tmp += mid / tt[i];
            }
            if (tmp >= target) r = mid;
            else l = mid + 1;
        }
        return l;
    }
    
    static int[] tt;
    static int examinerCnt;
    static int target;
    
    public long solution(int n, int[] times) {

        examinerCnt = times.length;
        target = n;
        tt = new int[examinerCnt];
        for (int i = 0; i < examinerCnt; i++) tt[i] = times[i];
        Arrays.sort(tt);
        // System.out.println(Math.log(1000000000000000000L) / Math.log(2));
        // System.out.println(Long.MAX_VALUE);
                
        return binarySearch();
    }
}