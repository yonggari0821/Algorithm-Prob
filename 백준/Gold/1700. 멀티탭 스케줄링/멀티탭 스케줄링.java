import java.io.*;
import java.util.*;
/*
[기타 1700]_멀티탭 스케쥴링_안상준

플러그를
꼽

 */


public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] electronicDevices = new int[K];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) electronicDevices[i] = Integer.parseInt(st.nextToken());

        boolean[] plugged = new boolean[101];
        int put = 0;
        int ans = 0;
        for (int i = 0; i < K; i++) {
            int temp = electronicDevices[i];

            if (!plugged[temp]) // 콘센트가 꽂혀있지 않은 경우
            {
                if (put < N) // 콘센트를 꽂을 공간이 있는 경우 
                { 
                    plugged[temp] = true;
                    put++;
                } 
                else // 콘센트를 꽂을 공간이 없는 경우
                { 
                    ArrayList<Integer> arrList = new ArrayList<>();
                    for (int j = i; j < K; j++) // 현재 꽂혀 있는 콘센트가 나중에도 사용되는지 확인해서
                    { 
                        if (plugged[electronicDevices[j]] && !arrList.contains(electronicDevices[j])) arrList.add(electronicDevices[j]);
                    }

                    if (arrList.size() != N) // 나중에도 사용되는 콘센트가 구멍의 개수보다 작을 경우
                    { 
                        for (int j = 0; j < plugged.length; j++) 
                        {
                            if (plugged[j] && !arrList.contains(j))
                            { 
                                plugged[j] = false; // 그 콘센트를 제거.
                                break;
                            }
                        }
                    } 
                    else // 현재 꽂혀 있는 모든 콘센트가 나중에도 사용될 경우
                    { 
                        int remove = arrList.get(arrList.size() - 1); // 가장 마지막에 사용될 콘센트를 제거.
                        plugged[remove] = false;
                    }
                    plugged[temp] = true;
                    ans++;
                }
            }
        }
        System.out.println(ans + "\n");
        br.close();
    }
}