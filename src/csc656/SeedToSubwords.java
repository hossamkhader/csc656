package csc656;
import java.util.*;

public class SeedToSubwords {
	
	public static String[] processSeed(String seed, int maxSWLength){

		Set<String> swSet = new HashSet<String>();
		int seedLen = seed.length();

		for( int swLen = 1; swLen <= seedLen && swLen <= maxSWLength; swLen++ ){
			
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
		}

		boolean containsH = true;

		while(containsH) {
			containsH = false;

			Set<String> swSetCopy = new HashSet<String>(swSet);

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

			swSetCopy = swSet;

		}

		String[] swArr = swSet.toArray(new String[swSet.size()]);

		Arrays.sort(swArr, Comparator.comparingInt(String::length).thenComparing(Comparator.naturalOrder()));

		return swArr;
	}

	public static void main(String[] args){

		String seed;
		int maxSWLength;

		if(args.length != 0){
			seed = args[0];
			try {
				maxSWLength = Integer.parseInt(args[1]);
			} catch (Exception e) {
				maxSWLength = seed.length();
			}
		} else {
			seed = "001H10";
			maxSWLength = seed.length();
		}

		System.out.println("Seed: " + seed);

		String[] output = processSeed(seed, maxSWLength);

		System.out.println("Output: ");
		for(String s : output) {
			System.out.println(s);
		}

	}

}
