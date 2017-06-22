import java.io.*;
public class Coin1{
	public static void main(String Args[])throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] c = new int[n];
		int[] a = new int[n+1];
		for(int i = 0 ; i <= n ; i++)
			a[i] = 0;
		for(int i = 0 ; i < n ; i++)
			c[i] = Integer.parseInt(br.readLine());
		a[0] = 0;
		a[1] = c[0];
		System.out.println();
		for(int i = 2; i <= n; i++)
		{
			a[i] = max(a[i-1],a[i-2]+c[i-1]);
			if (a[i-2]+c[i-1] >= a[i-1])
				System.out.print(c[i-1]);
		}
		System.out.println();
		System.out.println(a[n]);

	}
	public static int max(int a, int b){
		if (a > b)
			return a;
		else
			return b;
	}
	
}