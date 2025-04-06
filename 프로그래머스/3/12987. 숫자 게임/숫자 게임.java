import java.util.*;

/*

BruteForce 시 B의 최대 길이인 10만!이 걸릴수도 있기 때문에 모종의 방법이 필요

B배열과 A배열 정렬 후
덱을 만들어서 넣어 둔 후
덱의 맨 뒤의 숫자를 peek해서 
    A배열의 숫자보다 클 경우 ans++; 하고 맨 뒤 숫자 빼기
                 작을 경우 맨 앞의 숫자를 빼기

1357
2268


*/
class Solution {
    public int solution(int[] A, int[] B) {
        int ans = 0;
        
        Arrays.sort(A);
        Arrays.sort(B);
        
        Deque<Integer> d = new LinkedList<>();
        
        for (int i = 0; i < B.length; i++) d.offerLast(B[i]);
        
        for (int i = A.length - 1; i >= 0; i--) {
            int cur = A[i];
            int num = d.peekLast();
            if (cur < num) {
                d.pollLast();
                ans++;
            }
            else {
                d.pollFirst();
            }
        }
        
        return ans;
    }
}