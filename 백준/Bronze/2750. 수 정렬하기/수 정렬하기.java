import java.io.*;
import java.util.*;

public class Main {

    static void swap(int a, int b, int[] numArrs)
    {
        int t = numArrs[a];
        numArrs[a] = numArrs[b];
        numArrs[b] = t;
    }

    public static void main(String[] args) throws IOException {
//        Runtime runtime = Runtime.getRuntime();
//        runtime.gc();
//
//        // 연산 시작 전 메모리 사용량
//        long memoryBefore = runtime.totalMemory() - runtime.freeMemory();
//        System.out.println("Used memory before operation: " + memoryBefore + " bytes");

//        int[] a = {6, 8, 0, 9, 1, 2, 4, 5, 3};
//        long from = System.nanoTime();
//        System.out.println(from);
//        System.out.println("-----before-----");
//        System.out.println(Arrays.toString(a));
//        System.out.println("-----start sorting-----");

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int[] a = new int[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            a[i] = Integer.parseInt(st.nextToken());
        }

        int n = a.length;
        for (int i = 1; i < n; i++) {
            int toInsert = a[i];
            for (int j = i - 1; j >= 0 && a[j] > toInsert; j--) {
                a[j + 1] = a[j];
                a[j] = toInsert;
            }
        }

//        long to = System.nanoTime();
//        System.out.println(to);
//        System.out.println("-----finally-----");
//
//        long memoryAfter = runtime.totalMemory() - runtime.freeMemory();
//        System.out.println("Used memory after operation: " + memoryAfter + " bytes");
//        long memoryUsed = memoryAfter - memoryBefore;
//        System.out.println("Memory used by operation: " + memoryUsed + " bytes");
//
//        System.out.println(Arrays.toString(a));
//        long timeComplexity = to - from;
//        System.out.println("timeComplexity = " + timeComplexity);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(a[i]).append("\n");
        }
        System.out.println(sb.toString());
    }
}

/*

*/