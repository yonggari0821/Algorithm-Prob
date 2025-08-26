import java.util.*;

/*
    숫자카드 반씩 나눠 가짐
    chulsoo - arrayA
    younghee - arrayB
    둘 다 최대 50만 길이
    카드의 값은 최대 1억
    중복된 원소가 있을 수 있음 => 중복되면 무조건 답은 0
    
    아래 조건 중 하나를 만족하는 가장 큰 양의 정수 구하기
    철수가 가진 카드의 모든 숫자를 나눌 수 있고 영희가 가진 카드의 모든 숫자를 나눌 수 없는 양의 정수
    그 반대
    
    나눌 수 있고 없고 차이가 뭔데?
    나눌 수 있음 == 나눴을 때 나머지가 0
    나눌 수 없음 == 나눴을 때 나머지가 0이 아님
    
    1) arrayA와 arrayB에 등장하는 수들 중 가장 큰 수까지 모든 수를 대상으로 나눌 수 있는 지 여부 체크해서 구하기
    최대 100만 x 1억 ==> 바람직하지 않음
    
    2) gcd 최대 공약수 구하는 유클리드 호제법을 활용하여
    양쪽의 최대 공약수를 먼저 구하고
    해당 최대 공약수의 공약수들로 반대쪽을 나눠보기
*/

class Solution {
    
    private static int gcdA, gcdB;
    private static int max = 0;
    private int gcd(int a, int b) {
        while (b > 0) {
            int tmp = a % b;
            a = b;
            b = tmp;
        }
        return a;
    }
    
    private boolean cannotDivide(int n, int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] % n == 0) return false;
        }
        return true;
    }
    
    public int solution(int[] arrayA, int[] arrayB) {
        int max = 0;
        
        int len = arrayA.length;
        int a = arrayA[0];
        int b = arrayB[0];
        for (int i = 1; i < len; i++) {
            if (a < arrayA[i]) a = gcd(arrayA[i], a);
            else a = gcd(a, arrayA[i]);
            if (b < arrayB[i]) b = gcd(arrayB[i], b);
            else b = gcd(b, arrayB[i]);
        }
        
        for (int i = 2; i <= a; i++) {
            if (a % i == 0) {
                if (cannotDivide(i, arrayB)) max = Math.max(i, max);
            }
        }
        for (int i = 2; i <= b; i++) {
            if (b % i == 0) {
                if (cannotDivide(i, arrayA)) max = Math.max(i, max);
            }
        } 
        
        return max;
    }
}