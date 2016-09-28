package csc656;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

public class SeedToSubwords {
	
    /**
     * Method generates all subwords of the given length of the given seed
     * @return the array of subwords
     */
	public static String[] swOfLength(String seed, int swLength){

		// debug // System.out.println(seed + " " + swLength);

		Set<String> swSet = new HashSet<>();
		int seedLen = seed.length();

		int swLen = swLength;
		if(swLen > seed.length()){
			swLen = seed.length();
		}		
		
		int startIndex = 0;
		int endIndex = swLen;
		boolean hitEnd = false;

		while(!hitEnd){

			swSet.add(seed.substring(startIndex, endIndex));

			if(endIndex == seedLen){
				hitEnd = true;
			} else {
				startIndex++;
				endIndex++;
			}
		}

		boolean containsH = true;
			
		while(containsH) {

			containsH = false;

			Set<String> swSetCopy = new HashSet<>(swSet);

			for ( String sw : swSetCopy ){
				int idx = sw.indexOf('H');				
				if(idx > -1){
				
					containsH = true;

					char[] charArr = sw.toCharArray();
					charArr[idx] = '0';
					swSet.add(String.valueOf(charArr));
					charArr[idx] = '1';
					swSet.add(String.valueOf(charArr));
					
					swSet.remove(sw);
				}
			}
		}
		String[] swArr = swSet.toArray(new String[swSet.size()]);

		Arrays.sort(swArr);

		return swArr;
	}
	
    /**
     * Method generates all subwords from length 1 up to the given length of the given seed
     * @return the array of subwords
     */
	public static String[] swUpToMax(String seed, int maxSWLength){

		Set<String> swSet = new HashSet<>();
		int seedLen = seed.length();

		for( int swLen = 1; swLen <= seedLen && swLen <= maxSWLength; swLen++ ){
	
			String[] sws = swOfLength(seed, swLen);

                        swSet.addAll(Arrays.asList(sws));	
		}

		String[] swArr = swSet.toArray(new String[swSet.size()]);

		Arrays.sort(swArr);

		return swArr;
	}
}
