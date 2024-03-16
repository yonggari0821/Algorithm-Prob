import java.io.*;
import java.util.StringTokenizer;

public class Main {
	static int n,size;
	static int[] heightsOfRectangles, tree;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		while(true) {
			String s = br.readLine();
			if(s.equals("0")) break;

			st = new StringTokenizer(s);
			n = Integer.parseInt(st.nextToken());

			heightsOfRectangles = new int[n];
			for(int i=0; i<n; i++) {
				heightsOfRectangles[i] = Integer.parseInt(st.nextToken());
			}

			size = getTreeSize();
			tree= new int[size];
			init(heightsOfRectangles,1,0,n-1);
			System.out.println(getMax(0,n-1));
		}
	}

	static int getTreeSize() {
		int h = (int)Math.ceil(Math.log(n)/Math.log(2)) +1;
		return (int)Math.pow(2, h);
	}

	static void init(int[] arr, int node, int start, int end) {
		if(start == end) {
			tree[node] = start;
		}
		else {
			// 반으로 나눠서 왼쪽 오른쪽
			int mid = (start + end) /2;
			init(arr, node*2, start, mid);
			init(arr, node*2+1, mid+1, end);

			// 오른쪽이 더 큼 >>> 왼쪽 배정
			if(arr[tree[node*2]] < arr[tree[node*2+1]]) {
				tree[node] = tree[node*2];
			}
			// 왼쪽이 더 큼 >>> 오른쪽 배정
			else {
				tree[node] = tree[node*2+1];
			}
		}
	}
	static long getMax(int left, int right) {
		int n = heightsOfRectangles.length;
		int m = getMinIndex(0,n-1,left,right,1);

		long area = (long)(right - left +1)*(long) heightsOfRectangles[m];
		if(left<= m-1) {
			long tmp = getMax(left, m-1);
			if(area < tmp) {
				area = tmp;
			}
		}

		if(m+1 <= right) {
			long tmp = getMax(m+1, right);
			if(area < tmp) {
				area = tmp;
			}
		}
		return area;
	}

	static int getMinIndex(int left, int right, int queryleft, int queryright, int node) {
		// 무관한 범위
		if(queryleft > right || queryright < left) return -1;
		// 완전 포함 범위
		if(queryleft <= left && right <= queryright) {
			return tree[node];
		}

		int mid = (left + right) / 2;
		int leftNode = getMinIndex(left, mid, queryleft, queryright, node*2);
		int rightNode = getMinIndex(mid+1, right, queryleft, queryright, node*2+1);

		if(leftNode == -1) {
			return rightNode;
		}
		else if(rightNode == -1) {
			return leftNode;
		}
		// 더 작은 쪽의 노드 값 반환
		else {
			if(heightsOfRectangles[leftNode]<= heightsOfRectangles[rightNode])
				return leftNode;
			else
				return rightNode;
		}
	}

}