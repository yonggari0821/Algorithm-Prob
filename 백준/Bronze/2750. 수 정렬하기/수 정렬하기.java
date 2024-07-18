import java.io.*;
import java.util.*;

public class Main {

    static void swap(int a, int b, int[] numArrs)
    {
        int t = numArrs[a];
        numArrs[a] = numArrs[b];
        numArrs[b] = t;
    }

    static boolean HOrL = true;

    static int part(int[] arr, int left, int right)
    {
        if (HOrL) return partH(arr, left, right);
        return partL(arr, left, right);
    }

    static void quickSort(int[] arr, int left, int right)
    {
        // 기본적으로 퀵 정렬은 분할 정복 // 따라서 분할을 해나가는 재귀 호출이 필요함! // 재귀 호출을 구현하기 위해서는 반드시 탈출 조건이 필요함
        // 분할 정복에서 재귀 호출을 탈출하는 조건으로는 "더 이상 분할할 수 없는" 조건을 설정해야함
        // 이는 바로 "요소가 1개인 상황"이므로 이걸 대신 "left < right"로 나타낼 수 있음! (원소가 1개면 left == right 이므로)
        // 원소가 1개인 곳까지 depth가 진행되면 아무것도 안하고 함수가 끝남
        if (left < right)
        {
            int pivot = part(arr, left, right);
            quickSort(arr, left, pivot);
            quickSort(arr,pivot + 1, right);
        }
    }


    static int partH(int[] arr, int left, int right)
    {
        // Hoare Partition의 경우 pivot을 현재 구간의 가장 왼쪽으로 설정함
        int pivot = arr[left];
        // pivot 다음 값부터 끝까지
        int hl = left - 1;
        int hr = right + 1;
        // 오름차순 가정
        while (true)
        {
            // 좌측값과 pivot값을 비교해서
            // 오름차순 : 작으면 그냥 넘기기 // 내림차순 : 크면 그냥 넘기기
            do {
                hl++;
            } while (arr[hl] < pivot);
            // 반대로
            do {
                hr--;
            } while (arr[hr] > pivot);
            // 교차한 경우
            if (hl >= hr) return hr;
            swap(hl, hr, arr);
        }
    }

    static int partL(int[] arr, int left, int right)
    {
        // Lomuto Partition의 경우 pivot을 현재 구간의 가장 오른쪽으로 설정함
        int pivot = arr[right];
        // 이 값을 활용해서 다음 pivot을 설정함 // 실제로는 pivot보다 작은 수들의 갯수를 나타냄
        int idx = left - 1;
        for (int i = left; i < right; i++)
        {
            // pivot 보다 작은 경우에 idx를 하나 올리면서 그 자리와 i번째 자리의 값을 바꿈
            if (arr[i] < pivot)
            {
                idx++;
                swap(idx, i, arr);
            }
        }
        swap(idx + 1, right, arr);
        return idx + 1;
    }

    public static void main(String[] args) throws IOException {
//        Runtime runtime = Runtime.getRuntime();
//        runtime.gc();
//
//        // 연산 시작 전 메모리 사용량
//        long memoryBefore = runtime.totalMemory() - runtime.freeMemory();
//        System.out.println("Used memory before operation: " + memoryBefore + " bytes");

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int[] a = new int[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            a[i] = Integer.parseInt(st.nextToken());
        }

//        int[] a = {6, 8, 0, 9, 1, 2, 4, 5, 3, 12};
//        long from = System.nanoTime();
//        System.out.println(from);
//        System.out.println("-----before-----");
//        System.out.println(Arrays.toString(a));
//        System.out.println("-----start sorting-----");

        quickSort(a, 0, a.length - 1);

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
//
//        long timeComplexity = to - from;
//        System.out.println("timeComplexity = " + timeComplexity);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) sb.append(a[i]).append('\n');
        System.out.println(sb.toString());
    }
}

/*

*/