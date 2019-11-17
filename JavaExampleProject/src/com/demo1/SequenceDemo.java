package com.demo1;

public class SequenceDemo {

	public static void main(String[] args) {
		Integer[] num = {1,2,3,5,8,9,10};
		String[] sequenceNumber = new String[3];
		int k = 0;
		for(int i = 0; i<num.length; i++) {
			if(i+1 < num.length) {
				if(num[i+1].equals(num[i]+1)) {
					int j = i;
					
					while(j+1<num.length && num[j+1].equals(num[j]+1)) {
						j++;
					}
					if(j+1 <num.length) {
						//j++;
					}
					sequenceNumber[k] = num[i] + "..." + num[j] ;
					i=j;
					k++;
				}
				else {
					sequenceNumber[k] = num[i].toString();
					k++;
				}
			} else {
				if(!sequenceNumber[k-1].contains(num[i].toString())) {
					sequenceNumber[k] = num[i].toString();
				}
			}
		}
		for(int i =0; i< sequenceNumber.length; i++)
			System.out.println(sequenceNumber[i]);
	}

}
