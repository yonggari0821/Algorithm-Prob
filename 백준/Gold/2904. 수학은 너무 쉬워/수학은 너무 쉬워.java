import java.io.*;
import java.util.*;
public class Main {
    static StringBuilder ans = new StringBuilder();
    static int N, totalGCD = 1, minMove = 0;
    static int[] nums;
    static HashMap<Integer, Integer> numbersEach = new HashMap<>();
    static void primeFactorization(int n)
    {
        for (int i = 2; i <= n; i++)
        {
            while (n >= i && n % i == 0)
            {
                numbersEach.put(i, numbersEach.getOrDefault(i, 0) + 1);
                n /= i;
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        if (N == 1) ans.append(Integer.parseInt(br.readLine()) + " " + 0);
        else
        {
            nums = new int[N];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++)
            {
                nums[i] = Integer.parseInt(st.nextToken());
                primeFactorization(nums[i]);
            }
            ArrayList<Integer> gcd = new ArrayList<>();
            Iterator<Map.Entry<Integer, Integer>> it = numbersEach.entrySet().iterator();
            while (it.hasNext())
            {
                Map.Entry<Integer, Integer> tmpEntry = it.next();
                int number = tmpEntry.getKey();
                int each = tmpEntry.getValue();
                if (each >= N)
                {
                    for (int i = 0; i < (each / N); i++)
                    {
                        gcd.add(number);
                        totalGCD *= number;
                    }
                }
            }
            for (int i = 0; i < N; i++)
            {
                for (int j = 0; j < gcd.size(); j++) {
                    if (nums[i] % gcd.get(j) == 0) nums[i] /= gcd.get(j);
                    else minMove++;

                }
            }
            ans.append(totalGCD + " " + minMove);
        }
        bw.write(ans.toString());
        br.close();
        bw.flush();
        bw.close();
    }
}

// 최소 횟수로 모든 수가 갖는 1 이외의 최대 공약수를 만들어야 함.
// 만드는 건 쉬움 그냥 소인수분해 해서 갯수랑 같이 넣어두고 그 중에 N으로 나눈 값을 곱해서 더하면 됨.

// 최소 몇 번 옮겨야 하는 지 아는 게 어려움
// 각 수에서 구한 최대 공약수까지 부족한 소수의 갯수의 합을 구해야 하는데
// 구한 최대 공약수를 소수들의 리스트로 만들어서 하나씩 뽑아다가 주어진 숫자들로 나눈 나머지가 0이 아닌 갯수를 구하면 됨.