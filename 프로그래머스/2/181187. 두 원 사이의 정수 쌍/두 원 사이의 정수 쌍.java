import java.util.*;

/*

[틀린 풀이]
원을 둘러싸는 점 갯수를
반지름이
1 일때 8
2 일때 16
3 일때 24
즉 8의 배수
로 구함

반례
r2 = 4, r1 = 3
내 방식대로 구하면 24가 나옴
답은 28

*/

// class Solution {
    
//     private static int[] points;
//     private static long[] sum;
    
//     public long solution(int r1, int r2) {
//         long answer;
        
//         points = new int[r2 + 1];
//         sum = new long[r2 + 1];
        
//         for (int i = 1; i <= r2; i++) {
//             points[i] = 8 * i;
//             sum[i] = sum[i - 1] + points[i];
//         }
        
//         answer = sum[r2 - 1] - sum[r1 - 1] + 4; 
        
//         System.out.println(sum[3] - sum[2] + 4);
//         return answer;
//     }
// }

// ----------------------------------------------------------------------------
// ----------------------------------------------------------------------------
// ----------------------------------------------------------------------------

/*
[정확한 풀이]
r과 c값을 구해서 각각을 제곱해서 원점과의 거리를 구해서 직접 비교해보기
r1² ≤ x² + y² ≤ r2²를 만족해야 함


*/

class Solution {
    public long solution(int r1, int r2) {
        long answer = 0;
        long smallR = (long)Math.pow(r1,2);
        long bigR = (long)Math.pow(r2,2);
        long onSmallPoint = 0;
        for(long i = 0; i <= r2; i++) {
            // System.out.println("---[" + i + "]---");
            long max = (long) Math.sqrt(bigR - i*i);
            long min = (long) Math.sqrt(smallR - i*i);
            
            if(Math.sqrt(smallR-i*i) % 1 == 0) {
                onSmallPoint++;
                // System.out.println("on Small! plus point!");    
            }
            
            // System.out.println("max = " + max);
            // System.out.println("min = " + min);
            
            answer += (max-min) * 4;
            // System.out.println("");
        }
        
        answer += onSmallPoint*4;
        answer -= 4; // 큰 원의 최외각 점들은 c=0일때 중복으로 세어지기 때문에 차감!
        return answer;
    }
}