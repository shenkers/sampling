package sampling;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;

/**
 * this will sample n indices randomly from a sample size of N
 * @param n numbers of items to sample to sample, sum(n) <= N
 * @param N number sampled from
 */
public class StreamMultinomialSampler implements Iterable<Integer>{
	int N;
	int[] n;

	public StreamMultinomialSampler(int N, int... n) {
		this.n=n;
		this.N=N;
	}

	public Iterator<Integer> iterator() {
		return new StreamSampleIterator(N,n);
	}

	class StreamSampleIterator implements Iterator<Integer>{

		int j;
		// holds the number from each state to sample, the last slot is for a null sample
		int[] nSamples;
		Random rand;

		public StreamSampleIterator(int N, int[] n) {
			this.nSamples=new int[n.length];
			nSamples[0] = n[0];
			int total = 0;
			for(int j=0; j<n.length; j++){
				nSamples[j] = n[j];
				total += n[j];
			}
			if(total>N)
				throw new RuntimeException(String.format("Sampling more items than elements, %d > %d", total, N));
			this.j=N;
			rand = new Random();
		}

		public boolean hasNext() {
			return j > 0; 
		}

		/**
		 * return true if the current index is an element of the n subset of N
		 */
		public Integer next() {
			Integer b = null;
			
			int r = rand.nextInt(j);
//			System.out.printf("r %d\n", r);
//			System.out.printf("cum %s\n", Arrays.toString(nSamples));
			int cumul = 0;
			for(int i=0; i<nSamples.length; i++){
				cumul += nSamples[i];
				if(r < cumul){
					b = i;
					nSamples[i]--;
					break;
				}
			}
			j--;

			return b;
		}

		public void remove() {

		}
	}
	
	public static void main(String[] args) throws IOException {
		StreamMultinomialSampler s = new StreamMultinomialSampler(10, 3,5,2);
		
		for(Integer b : s){
			System.out.println("s:"+b);
			System.out.println();
		}
		
		
	}
}
