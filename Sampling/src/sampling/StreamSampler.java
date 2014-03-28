package sampling;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Iterator;

/**
 * this will sample n indices randomly from a sample size of N
 * @param n number to sample, n<=N
 * @param N number sampled from
 */
public class StreamSampler implements Iterable<Boolean>{
	int n,N;

	public StreamSampler(int n, int N) {
		this.n=n;
		this.N=N;
	}



	public Iterator<Boolean> iterator() {
		return new StreamSampleIterator(n,N);
	}

	class StreamSampleIterator implements Iterator<Boolean>{

		int n,N;
		int i,j;

		public StreamSampleIterator(int n, int N) {
			this.n=n;
			this.N=N;
			this.i=n;
			this.j=N;
		}

		public boolean hasNext() {
			return j > 0; 
		}

		/**
		 * return true if the current index is an element of the n subset of N
		 */
		public Boolean next() {
			Boolean b = null;
			// no more elements to sample
			if(i==0){
				b=false;
			}
			// all remaining elements must be in set
			else if(i==N){
				i--;
				b=true;
			}
			// sample according to likelihood given earlier samples
			else {
				// is part of set
				if(Math.random() < i*1./j){
					i--;
					b=true;
				}
				// is not part of set
				else{
					b=false;
				}
			}
			j--;

			return b;
		}

		public void remove() {

		}
	}
	
	public static void main(String[] args) throws IOException {
		int n = Integer.parseInt(args[0]);
		int N = Integer.parseInt(args[1]);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StreamSampler s = new StreamSampler(n, N);
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		for(Boolean b : s){
			String line = br.readLine();
			if(b){
				bw.write(line);
				bw.write('\n');
			}
		}
		
		br.close();
		bw.close();
	}
}
