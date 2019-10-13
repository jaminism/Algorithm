import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * 백준, 14888
 * 연산자 끼워넣기
 * 
 * 
 * condition : 첫째 줄에 만들 수 있는 식의 결과의 최댓값을, 둘째 줄에는 최솟값을 출력한다
 * 
 * 브루트포스 문제
 * @author jaminism
 **/

public class Main {
  	static int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE-1;
    static int N, plus, sub, multi, divede;
    static int[] map = new int [12];
    
	  public static void main(String[] args) {
		    Scanner sc = new Scanner(System.in);
		
        N = sc.nextInt();
        for(int i=0; i<N; i++)
            map[i] = sc.nextInt();
        
        plus = sc.nextInt();
        sub = sc.nextInt();
        multi = sc.nextInt();
        divede = sc.nextInt();
        
        // n이 1부터 시작
        calc(1, 0, 0, 0, 0, map[0]);
        
        System.out.println(max);
        System.out.println(min);
	}

	public static void calc(int n, int p, int s, int m, int d, int value) {
		  if (n==N) {
		  	max = Math.max(max, value);
	  		min = Math.min(min, value);
	  		return;
	  	}
		  // 조건에 맞을때 재귀로 값누적
	  	if (p < plus)
	  		calc(n+1, p+1, s, m, d, value + map[n]);
	  	if (s < sub)
	  		calc(n+1, p, s+1, m, d, value - map[n]);
	  	if (m < multi)
	  		calc(n+1, p, s, m+1, d, value * map[n]);
	  	if (d < divede)
	  		calc(n+1, p, s, m, d+1, value / map[n]);
	  }
}
