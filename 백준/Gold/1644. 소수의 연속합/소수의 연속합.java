import java.io.*;
import java.util.*;

/*
백준 1644 소수의 연속합
N <= 400만
*/

public class Main {
    static StringBuilder sb = new StringBuilder();
    static int N, cnt = 0;
    static List<Integer> primeList;
    static long[] cumul;

    static void tp(int target)
    {
        // 왼쪽 끝과 오른쪽 끝은 소수 리스트의 크기 즉 소수의 갯수를 참고
        int l = 0;
        int r = primeList.size() - 1;
        // 만약 target이 2이거나 소수 그 자체인 경우도 고려해서 카운트 // 둘 다 동시에 만족할 수는 없으므로 ||로 처리
        if (primeList.get(l) == target || primeList.get(r) == target) cnt++;

        // 임시 값 of l번째부터 r번째까지의 연속된 소수 합
        long tmp = cumul[r] - cumul[l] + primeList.get(l);
        while (l < r && r < primeList.size())
        {
            // 아직 목표하는 값보다 크다면 소수를 하나씩 빼가면서 우측 포인터도 왼쪽으로 이동
            while (tmp > target) {
                tmp -= primeList.get(r);
                r--;
            }
            // 그렇게 찾았으면 cnt 늘리기 // while에 등호를 넣어서 하지 않고 if문으로 따로 빼서 할 수 있는 이유는 소수 리스트이기 때문에 똑같은 수가 또 들어있을 수 없음!
            if (tmp == target) cnt++;
            // 찾은 후에는 현재 왼쪽 포인터 값 빼주고 왼쪽 오른쪽 이동 후에 오른쪽 값이 아직 유효한 범위인 경우 더해줌으로써 투포인터 구간 전체가 한 칸 이동!
            tmp -= primeList.get(l);
            l++;
            r++;
            if (r < primeList.size()) tmp += primeList.get(r);
        }
        sb.append(cnt);
    }

    public static void main(String[]args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        if (N < 2) sb.append(0); // 0,1 은 소수가 아님
        else if (N < 4) sb.append(1); // 2, 3은 소수지만 연속된 소수의 합으로 나타내는 건 각자 밖에 없음
        else {
            boolean[] prime = new boolean[N + 1]; // 에라토스테네스의 체를 위한 소수인지 아닌지 배열
            Arrays.fill(prime, true); // 처음에는 다 소수로 처리
            prime[0] = false; // 0 제외
            prime[1] = false; // 1 제외
            primeList = new ArrayList<Integer>(); // 소수 리스트
            for (int i = 2; i <= N; i++)
            {
                // 소수면 소수 리스트에 넣고 제곱부터 시작해서 끝까지 자기 자신 더해가면서 소수 아님 처리 feat. 에라토스테네스의 채
                if (prime[i]) {
                    primeList.add(i);
                    // 이 부분을 아래 주석 처럼단순히 for문으로 j = i * i로 하게되면 N의 범위가 4000000이기 때문에 i값이 커지게되면 j가 음수가 되서 오버플로우를 발생시키고 이는 자연스레 ArrayIndexOutOfBoundsException으로 이어짐!
//                    for (int j = i * i; j <= N; j += i) {
//                        prime[j] = false;
//                    }
                    // 따라서 이렇게 해야 함!
                    // N의 범위가 최대 400만이므로 i가 그 제곱근인 2000까지일 때만 추가적으로 에라토스테네스의 체로 걸러두면 됨!
                    if (i <= 2000)
                    {
                        for (int j = i * i; j <= N; j += i) {
                            prime[j] = false;
                        }
                    }
                }
            }
            // 소수들의 누적합 배열 만들기
            cumul = new long[primeList.size()];
            // 첫 번째는 2임을 명시
            cumul[0] = 2;
            // 그 다음부터는 직전 값에 primeList에서 하나씩 빼와서 더해주기
            for (int i = 1; i < cumul.length; i++) cumul[i] = cumul[i - 1] + primeList.get(i);
//            System.out.println("primeList = " + primeList);
//            System.out.println("Cumul = " + Arrays.toString(cumul));
            // 투포인터 방식으로 구해가기
            tp(N);
        }
        System.out.println(sb.toString());
    }
}