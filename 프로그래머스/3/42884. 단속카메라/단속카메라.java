import java.util.*;
/*

도착점을 기준으로 오름차순 정렬 후
현재 구간의 출발점이 마지막에 설치한 카메라 위치보다 클 때 현재 구간의 도착점에 카메라 설치

*/
class Solution {
    public int solution(int[][] routes) {
        int answer = 0;
        
        Arrays.sort( routes, (a, b) -> a[1] - b[1] );
        
        int lastCamera = Integer.MIN_VALUE;
        for (int i = 0; i < routes.length; i++) {
            if (routes[i][0] <= lastCamera) continue;
            lastCamera = routes[i][1];
            answer++;
        }
        
        return answer;
    }
}