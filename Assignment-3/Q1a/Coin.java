import java.io.*;
import java.util.*;
public class Coin{
	static File fout = null;
	static FileOutputStream fos = null; 
 	static BufferedWriter bw = null;
	public static void main(String Args[])throws Exception{
		FileReader fr = new FileReader("INPUT");
		fout = new File("OUTPUT");
		fos = new FileOutputStream(fout);
		bw = new BufferedWriter(new OutputStreamWriter(fos));
		Scanner sc = new Scanner(fr);
		int n = sc.nextInt();
		int[] a = new int[n+1];
		int[] c = new int[n];

		for(int i = 0; i < n ; i++)
			c[i] = sc.nextInt();

		a[0] = 0;
		a[1] = c[0];

		for(int i = 2; i <= n; i++)
			a[i] = max(a[i-1],a[i-2]+c[i-1]);

		find_coins(a,c,n);
		bw.newLine();
		bw.close();

	}

	public static void find_coins(int a[],int c[],int i)throws Exception{
		if(i > 1){
			if(a[i] == a[i-2]+c[i-1]){
				find_coins(a,c,i-2);
				bw.write(c[i-1]+" ");
				System.out.println(c[i-1]);
			}
			else{
				find_coins(a,c,i-1);
			}
		}
		
	}

	public static int max(int a, int b){
		if (a > b)
			return a;
		else
			return b;
	}

	
}