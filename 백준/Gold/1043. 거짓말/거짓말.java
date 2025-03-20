import java.util.*;
import java.io.*;

public class Main {

    static int[] parent;
    static void makeSet(int n) {
        parent = new int[n + 1];
        for (int i = 1; i <= n; i++) parent[i] = i;
    }

    static int find(int a) {
        if (parent[a] == a) return a;
        else return parent[a] = find(parent[a]);
    }

    static void union(int a, int b) {
        int ra = find(a);
        int rb = find(b);
        if (ra < rb) parent[rb] = ra;
        else if (ra == rb) return;
        else parent[ra] = rb;
    }

    public static void main (String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 사람 수
        int M = Integer.parseInt(st.nextToken()); // 파티
        makeSet(N);
        int ans = 0;
        st = new StringTokenizer(br.readLine());
        int truthPplNum = Integer.parseInt(st.nextToken());
//        System.out.println("truthPplNum = " + truthPplNum);
        boolean truthFlag = false;
        int truthRepresent = 0;
        for (int i = 0; i < truthPplNum; i++) {
            int truthPpl = Integer.parseInt(st.nextToken());
//            System.out.println("truthPpl = " + truthPpl);
            if (!truthFlag) {
                truthRepresent = truthPpl;
                truthFlag = true;
//                System.out.println("truthRepresent = " + truthRepresent);
            }
            else {
                parent[truthPpl] = truthRepresent;
//                System.out.println(truthPpl + "'s parent is " + truthRepresent );
            }
        }

        ArrayList<Integer>[] partyPplList = new ArrayList[M + 1];
        for (int i = 0; i <= M; i++) partyPplList[i] = new ArrayList<Integer>();

        for (int partyNum = 1; partyNum <= M; partyNum++) {
            st = new StringTokenizer(br.readLine());
            int partyPplNum = Integer.parseInt(st.nextToken()); // 파티 인원수
            int basis = 0; //
            for (int ppl = 0; ppl < partyPplNum; ppl++) {
                int pplNum = Integer.parseInt(st.nextToken());
                partyPplList[partyNum].add(pplNum);
                if (ppl == 0) basis = pplNum;
                else {
                    union(basis, pplNum);
//                    System.out.println("parent of " + pplNum + " = " + basis);
                }
            }
        }

//        System.out.println(Arrays.toString(parent));

        for (int i = 1; i <= M; i++) {
            int partySize = partyPplList[i].size();
            boolean flag = true;
            for (int j = 0; j < partySize; j++) {
                int ppl = partyPplList[i].get(j);
                if (find(ppl) == find(truthRepresent)) {
                    flag = false;
                    break;
                }
            }
            if (flag) ans++;
        }

        System.out.println(ans);
    }
}