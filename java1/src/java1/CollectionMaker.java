package java1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CollectionMaker {
	
	
	public int[] removeDoubletsUsingArray(int[] integers) {
		
		if(integers.length == 0) {
			return integers;
		}
		int[] noDoublets = new int[1];
		int noDoubletIndex = 0;
		boolean doublet = false;
		for(int i = 0; i < integers.length; i++) {
			int integer = integers[i];
			doublet = false;
			for(int j = 0; j < noDoublets.length; j++) {
				if(integer == noDoublets[j]) {
					doublet = true;
				}
			}
			if(!doublet) {
				if(noDoubletIndex+1 > noDoublets.length) {
					int[] tmpNoDoublets = new int[noDoublets.length+1];
					for(int k = 0; k < noDoublets.length; k++) {
						tmpNoDoublets[k] = noDoublets[k];
					}
					noDoublets = tmpNoDoublets;		
				}
				noDoublets[noDoubletIndex] = integer;
				noDoubletIndex++;
			}		
		}
		return noDoublets;
	}
	
	public Set<Integer> removeDoubletsUsingSet(int[] integers){
		
		Set<Integer> noDoublets = new HashSet<Integer>();
		
		for(int i = 0; i < integers.length; i++) {
			noDoublets.add(integers[i]);
		}
		return noDoublets;
	}
	
	public List<Integer> addValueToEndOfArrayUsingList(int[] integers, int intToAdd){
		
		List<Integer> anAdditionalValue = IntStream.of(integers).boxed().collect(Collectors.toList());
		anAdditionalValue.add(intToAdd);
		return anAdditionalValue;
	}
	
	public int[] removeFirstElementWithValue(int[] integers, int intToRemove) {
		
		if(integers.length == 1 && integers[0] == intToRemove) {
			return new int[0];
		}
		
		int indexToRemove = -1;
		for(int i = 0; i < integers.length; i++) {
			if(integers[i] == intToRemove) {
				indexToRemove = i;
				break;
			}
		}
		if(indexToRemove != -1) {
			int[] tmpArray = new int[integers.length-1];
			int tmpArrayIndex = 0;
			for(int i = 0; i < integers.length; i++) {
				if(i == indexToRemove) continue;
				tmpArray[tmpArrayIndex] = integers[i];
				tmpArrayIndex++;		
			}
			integers = tmpArray;
		}
		
		return integers;
		
	}
	
	public Map<Integer, String> makeMapFromIntegerAndStringArray(int[] keys, String[] values){
		
		if(keys.length != values.length) return null;

		return IntStream.range(0, keys.length).boxed().collect(Collectors.toMap(i -> keys[i], i -> values[i]));
	
	}
	
	
	
	
}
