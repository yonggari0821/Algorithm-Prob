import java.util.*;

/*

구명보트 무게 제한 안에서 모든 사람을 구하고
최소 갯수를 써야 함

근데 문제에서 한 번에 많아봐야 최대 2명이라고 했으므로
그냥 좌 우 포인터 하나씩 두고
더한 값하고 무게 제한 비교해서 풀면 됨

*/
class Solution {
    public int solution(int[] people, int limit) {
        int answer = 0;
        
        Arrays.sort(people);
        
        int l = 0;
        int r = people.length - 1;
        
        while (l <= r) {
            if (people[l] + people[r] > limit) {
                // 문제에서 사람 무게 최대 값보다 limit이 무조건 크다 했으므로
                // 한 명은 무조건 태울 수 있음!
                r--;
            }
            else {
                l++;
                r--;
            }
            answer++;
        }
        
        return answer;
    }
}