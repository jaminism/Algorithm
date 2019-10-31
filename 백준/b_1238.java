import java.io.*;
import java.util.*;

/**
 * 백준, 1238
 * 파티
 * 
 * 
 * condition : 첫 번째 줄에 N명의 학생들 중 오고 가는데 가장 오래 걸리는 학생의 소요시간을 출력한다.
 * 
 * 다익스트라 문제
 * @author jaminism
 **/


public class Main {
	static int N, M, X;
	static int INF = 1000 * 10000 +1;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		X = sc.nextInt();
		
		int[][] map;
		int[][] re_map;
		int[] cost;
		int[] re_cost;
		
		map = new int[N+2][N+2];
		cost = new int[N+2];
		
		re_map = new int[N+2][N+2];
		re_cost = new int[N+2];
		
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				map[i][j] = re_map[i][j] = INF;
			}
			cost[i] = re_cost[i] = INF;
		}
		
		
		for (int i = 0; i < M; i++) {
			int from = sc.nextInt();
			int to = sc.nextInt();
			int time = sc.nextInt();
			
			map[from][to] = re_map[to][from] = time;
		}
		
		dijkstra(map, cost, X);
		dijkstra(re_map, re_cost, X);
		
		int result = 0;
		
		for (int i = 1; i <= N; i++) {
			if (result < cost[i] + re_cost[i])
				result = cost[i] + re_cost[i];
		}
		System.out.println(result);
	}

	public static void dijkstra(int[][] arr, int[] value, int start) {
		Queue<Integer> queue = new LinkedList<Integer>();
		value[start] = 0;
		queue.add(start);
		
		while (!queue.isEmpty()) {
			int from = queue.poll();
			
			for (int to = 1; to <= N; to++) {
				if ((value[to] > value[from] + arr[from][to])) {
//					System.out.println("from = " + from + " , to = " + to);
//					System.out.println(cost[to] + " > " + cost[from] + " + " + map[from][to]);
					value[to] = value[from] + arr[from][to];
//					System.out.println("cost = " + cost[to]);
					queue.add(to);
				}
			}
		}
	}
}
