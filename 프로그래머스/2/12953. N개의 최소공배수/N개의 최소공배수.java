class Solution {
    public int solution(int[] arr) {
        int answer = 0;

        if(arr.length == 1) return arr[0]; // 원소가 한 개인 경우 => 자기 자신이 최소 공배수

        // 그 외에는 최소 공배수 구하기
        int g = gcd(arr[0], arr[1]);
        answer = (arr[0] * arr[1]) / g;

        /*  
        원소의 개수가 3개 이상인 경우
        앞의 두 원소의 최소 공배수와 다음 원소의 최소 공배수를 구하며
        배열의 끝까지 반복
        */ 
        if(arr.length > 2) { 
            for(int i = 2; i < arr.length; i++) {
                g = gcd(answer, arr[i]);
                answer = (answer * arr[i]) / g;
            }
        }

        return answer;
    }

    //최대 공약수 구하기
    static int gcd(int o1, int o2)
    {
        // gcd by 유클리드 호제법 
        // gcd (o1, o2) == gcd (o2, o1 % o2)
        while (o2 != 0)
        {
            int tmp = o1 % o2;
            o1 = o2;
            o2 = tmp;
        }
        return o1;
    }
}