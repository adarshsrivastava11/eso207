import java.io.*;
import java.util.*;
public class Knapsack{
	public static void main(String args[])throws Exception{
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] c = new int[n];
		int[] v = new int[n];
		int i,j;

		for(i = 0 ; i < n ; i++){
			c[i] = sc.nextInt();
		}
		for(i = 0 ; i < n ; i++){
			v[i] = sc.nextInt();
		}
		int v_max = 0;
		for(i=0;i<n;i++){
			if(v[i] > v_max)
				v_max = v[i];

		}
		
		int[][] a = new int[n][v_max];
		for(i = 0 ; i < n ; i++){
			for(j=0;j < v_max;j++){
				a[i][j] = 0;
			}
		}

		for(i = 0 ; i < n ; i++){
			System.out.print("|");
			for(j=0;j < v_max;j++){
				System.out.print(" "+a[i][j]+" ");
			}
			System.out.print("|");
			System.out.println();
		}

		for(i=1;i<n;i++){
			for(j=0;j < v_max;j++){
				if(j-v[i] > 0)
					a[i][j] = max(a[i-1][j] , c[i]+a[i-1][j]);
				else
					a[i][j] = a[i-1][j];
			}
		}
		for(i = 0 ; i < n ; i++){
			System.out.print("|");
			for(j=0;j < v_max;j++){
				System.out.print(" "+a[i][j]+" ");
			}
			System.out.print("|");
			System.out.println();
		}
	}

	public static int max(int a, int b){
		if (a > b)
			return a;
		else
			return b;
	}
}