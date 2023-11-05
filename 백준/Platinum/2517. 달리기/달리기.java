import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;

public class Main {
    static int N, S;
    static int[] ability; // 실력 배열 // index가 등수
    static int[] bestRank;
    static HashMap<Integer, Integer> hm;

    static int[] tree;
    static StringBuilder ans;

    static int TDquery(int left, int right, int node, int queryLeft, int queryRight)
    {
        if (right < queryLeft || left > queryRight) return 0;
        else if (queryLeft <= left && right <= queryRight) return tree[node];
        else
        {
            int mid = left + (right - left) / 2;
            int leftValue = TDquery(left, mid, node * 2, queryLeft, queryRight);
            int rightValue = TDquery(mid + 1, right, node * 2 + 1, queryLeft, queryRight);
            return leftValue + rightValue;
        }
    }

    static void TDupdate(int left, int right, int node, int target, int diff)
    {
        if (target < left || right < target) return;
        else
        {
            tree[node] += diff;
            if (left != right)
            {
                int mid = left + (right - left) / 2;
                TDupdate(left, mid, node * 2, target, diff);
                TDupdate(mid + 1, right, node * 2 + 1, target, diff);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        ans = new StringBuilder();
        hm = new HashMap<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        S = 1;
        while (S < N) S *= 2;
        tree = new int[2 * S];

        ability = new int[N + 1];
        bestRank = new int[N + 1];
        for (int n = 1; n <= N; n++)
        {
            ability[n] = Integer.parseInt(br.readLine());
            hm.put(ability[n], n);
        }
        // System.out.println(Arrays.toString(ability)); // 2(1) 8(2) 10(3) 7(4) 1(5) 9(6) 4(7) 15(8)
        Arrays.sort(ability);
        // System.out.println(Arrays.toString(ability)); // 1(5) 2(1) 4(7) 7(4) 8(2) 9(6) 10(3) 15(8)

        for (int i = 1; i <= N; i++)
        {
            int target = hm.get(ability[i]);
            TDupdate(1, S, 1, target, 1);
            int tominus = TDquery(1, S, 1, 1, target - 1);
            int tmp = target - tominus;
            bestRank[target] = tmp;
        }

        for (int i = 1; i <= N ; i++)
            ans.append(bestRank[i]).append('\n');

        System.out.println(ans.toString());
    }
}

// update 함수 호출 횟수
// == 실력 오름차순 정렬 배열의 인덱스
// == 해당 인덱스의 값(최대 실력) 이하의 선수 수
// 구간 합 쿼리(ql, qr)
// ql = 1 고정
// qr = 자기 등수 이전까지의 등수 == 자신이 실력을 비교해서 앞질러볼 수 있는 사람 수
// 구간합 == 자기 보다 앞서 있으면서 자기보다 낮은 실력인 선수 수
// 이를 자기 등수에서 빼면 최선 등수