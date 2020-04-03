package com.hust.soict.tuananh.helper;

public class SelectionSort implements NumberSorter{
    public void sort(int arr[]){
        int n = arr.length;
        for(int i = 0; i < n; i++){
            //find the minimum number of unsorted array
            int min_idx = i;
            for(int j = i+1; j < n; j++){
                if(arr[j] < arr[min_idx]){
                    min_idx = j;
                }

                //swap
                int tmp = arr[min_idx];
                arr[min_idx] = arr[i];
                arr[i] = tmp;
            }
        }
    }
}
