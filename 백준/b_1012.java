import java.io.*;
import java.util.*;

/**
 * 백준, 1012
 * 유기농 배추
 * 
 * 
 * condition : 각 테스트 케이스에 대해 필요한 최소의 배추흰지렁이 마리 수를 출력한다.
 * 
 * BFS, DFS 중 DFS로 문제를 풀었다.
 * @author jaminism
 **/

public class Main {
	static int tc, N, M, K;
	static int map[][];
	static boolean[][] visited;
	static int result;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		tc = sc.nextInt();
		
		for (int i = 0; i < tc; i++) {
			M = sc.nextInt();  // 배추밭의 가로길이
			N = sc.nextInt();  // 배추밭의 세로길이
			K = sc.nextInt();  // 배추 위치 
			
			map = new int[N][M];
			visited = new boolean[N][M];
			
			for (int[] arrs : map)
				for (int arr : arrs)
					arr = 0;
			
			for (int j = 0; j < K; j++) {
				int x = sc.nextInt();
				int y = sc.nextInt();
				
                // 배추의 위치에 1을 입력
				map[y][x] = 1;
			}
			
			result = 0;
			
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < M; k++) {
					if (!visited[j][k] && map[j][k] != 0) {
                        // 배추가 심어져 있지 않다면 DFS로 추적
						visited[j][k]=true;
						dfs(k, j, result);
						result = result + 1;
					}
					
				}
			}
			
			System.out.println(result);
		}
	}
	
	public static void dfs(int x, int y, int count) {
		for (int i = 0; i < 4; i++) {
			int sx = dx[i] + x;
			int sy = dy[i] + y;
			// 4방향에 대한 조건문 
			if (sx >= 0 && sy >= 0 && sx < M && sy < N && !visited[sy][sx] && map[sy][sx]==1) {
				//System.out.println("x, y = " + sx + " , " + sy);
				
				visited[sy][sx] = true;
				dfs(sx, sy, count);
			}
		}
	}

}
