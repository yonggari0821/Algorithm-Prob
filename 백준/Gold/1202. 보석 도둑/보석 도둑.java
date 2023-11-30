import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class jewel implements Comparable<jewel>
{
    int weight;
    int price;

    jewel(int w, int p)
    {
        this.weight = w;
        this.price = p;
    }

    @Override
    public int compareTo(jewel o1) {
        return o1.price - this.price; // 가격 별로 내림차순
    }
}
public class Main {
    static int N, K;
    static long maxPrice;
    static PriorityQueue<jewel> pq = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        int[][] weightAndPrice = new int[N][2];
        for (int n = 0; n < N; n++)
        {
            StringTokenizer stn = new StringTokenizer(br.readLine());
            weightAndPrice[n][0] = Integer.parseInt(stn.nextToken()); // 무게
            weightAndPrice[n][1] = Integer.parseInt(stn.nextToken()); // 가격
        }
        // 무게가 작은 가방부터 살펴보기 위해 보석 무게를 기준으로 오름차순 정렬 <O(N log N)>
        Arrays.sort(weightAndPrice, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        int[] bag = new int[K];
        for (int k = 0; k < K; k++)
            bag[k] = Integer.parseInt(br.readLine());
        // 작은 가방부터 살펴보기 위해 bag 배열 오름차순 정렬 <O(N log N)>
        Arrays.sort(bag);

        // System.out.println(Arrays.deepToString(weightAndPrice));
        // System.out.println(Arrays.toString(bag));

        int jIndex = 0;
        for (int b = 0; b < K; b++)
        {
            int bagSize = bag[b];
            for (; jIndex < N; jIndex++)
            {
                if (weightAndPrice[jIndex][0] <= bagSize)
                    pq.offer(new jewel(weightAndPrice[jIndex][0], weightAndPrice[jIndex][1]));
                else
                    break;
            }
            if (!pq.isEmpty())
            {
                jewel portableAndMostExpensive = pq.poll();
                maxPrice += portableAndMostExpensive.price;
            }
        }
        System.out.println(maxPrice);
        // 작은 가방부터 담을 수 있는 가장 비싼 보석 담기!
    }
}