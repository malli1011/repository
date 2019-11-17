package com.Interview.questions;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EpamCoddingEx {
	
	public static void main(String[] args) {
		Integer[] a = {1,2,3,5,6,8,9,10};
		
		Map<Boolean, List<Integer>> mapPartitionBy = Arrays.asList(a).stream().collect(Collectors.partitioningBy(x -> x % 2 == 0));
		System.out.println(mapPartitionBy);
	}

	public static void usingLoops() {
	Integer[] firstArray = {1,2,3,5,6,8,9,10};
	String[] newArray = new String[10];
	int k=0;
	String temp = null;
	for(int i=0;i<firstArray.length;i++){
		
		if(i+1<firstArray.length && firstArray[i]==firstArray[i+1]-1 ) {
			temp = firstArray[i].toString()+"...";
			int j=i+1;
			while(j+1<firstArray.length && firstArray[j]==firstArray[j+1]-1) {
			j++;
			}
			i=j;
			temp = temp+firstArray[j];
		}else {
			temp = firstArray[i].toString();
		}
		newArray[k]= temp;
		k++;
		}
		for(String s:newArray)
		System.out.print(s +" ");
		
		}
	
	/*
	 * 
	 * */
		
	public void usingStreams() {
		Integer[] a = {1,2,3,5,6,8,9,10};
		
		Map<Boolean, List<Integer>> mapPartitionBy = Arrays.asList(a).stream().collect(Collectors.partitioningBy(x -> x % 2 == 0));
		System.out.println(mapPartitionBy);
	}
		
}
		
	



