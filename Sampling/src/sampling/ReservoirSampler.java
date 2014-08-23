package sampling;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * 
 * a fixed-memory solution to sample n items from a
 * stream of unknown length
 * @author sol
 *
 * @param <T>
 */
public class ReservoirSampler<T> {

	List<T> R;
	
	/**
	 * 
	 * @param it - the stream to sample items from
	 * @param k - the number of items to sample from the stream
	 * @param rand - a source of random numbers
	 */
	public ReservoirSampler(Iterator<T> it, int k, Random rand) {
		R = new ArrayList<T>(k);
		
		int i;
		
		for (i = 0; i < k; i++) {
			R.add(it.next());
		}
		
		while(it.hasNext()){
			i++;
			int j = rand.nextInt(i);
			T element = it.next();
			
			if(j<k){
				R.set(j, element);
			}
		}
	}
	
	/**
	 * @return the list of k items sampled uniformly from the stream
	 */
	public List<T> getSample(){
		return R;
	}
	
}