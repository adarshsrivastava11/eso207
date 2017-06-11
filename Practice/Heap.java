import java.io.*;
public class Heap
{
	public static void main(String[] args) {
		int[] array = {1,4,7,8,2,3,10,9,11,13};
		for(int i=0;i<array.length;i++)
			System.out.print(array[i]+ " ");
		System.out.println();
		build_heap(array);
		for(int i=0;i<array.length;i++)
			System.out.print(array[i]+ " ");
		System.out.println();
	}
	public static int parent(int i){
		return (i/2);
	}
	public static int left_child(int i){
		return (2*i);
	}
	public static int right_child(int i){
		return (2*i+1);
	}
	public static void max_heapify(int arr[],int i){
		int l = left_child(i);
		int r = right_child(i);
		int largest;
		int len = arr.length;
		if(l < len && arr[l] > arr[i])
			largest = l;
		else 
			largest = i;
		if(r < len && arr[r] > arr[largest])
			largest = r;
		if(largest != i){
			int temp = arr[i];
			arr[i] = arr[largest];
			arr[largest] = temp;
			max_heapify(arr,largest);
		}

	}
	public static void build_heap(int arr[]){
		int heap_size = arr.length;
		for(int i=(arr.length/2);i > 0;i--){
			max_heapify(arr,i);
		}
	}
}