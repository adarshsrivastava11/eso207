import java.util.*;

public class Matrix{
	public static void main(String args[])throws Exception{
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		int i,j,max_size,itrate_over;
		if (n >= m){
			max_size = m;
			itrate_over = n;
		}
		else{
			max_size = n;
			itrate_over = m;
		}
		int[][] arr = new int[n][m];
		int[][] square = new int [max_size][max_size];

		for(i = 0 ; i < itrate_over ; i++){
			if( i+max_size < itrate_over )
				check_matrix(arr,i,max_size);
		}
	}

	public void check_matrix(int arr[],int i,int max_size){

	}
}