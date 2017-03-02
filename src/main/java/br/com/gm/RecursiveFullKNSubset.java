/*   Copyright {2017} {Glaucio Melo - glaucio.melo@gmail.com}

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.*/
package br.com.gm;

/**
 * Class responsible for generate all Subsets of size N with K elements.
 * Documentation: https://arxiv.org/pdf/math/0503335.pdf
 * 
 * @author Glaucio Melo (glaucio.melo@gmail.com)
 *
 */
public class RecursiveFullKNSubset {

	int rank;

	private int[] identity;
	private int[] complement;
	private int[] identityC;
	private int[] complementC;
	
	
	private int sum(int k, int[] y) {
		int z = 0;
		for (int i = 0; i < k; i++) {
			z += y[i];
		}
		return z + k;
	}

	private void doIt(int i, int n, int k, int[] x, int[] y, boolean show, int line) {
		for (x[i] = sum(i, y); x[i] <= n - (k - i); x[i]++) {
			if (i < k - 1) {
				doIt(i + 1, n, k, x, y, show, line);
			} else {
				if (k - 1 != 0) {
					y[k - 1] = x[k - 1] - x[k - 2] - 1;
				} else {
					y[k - 1] = x[0];
				}
				int q = 0;
				for (int r = 0; r < k; r++) {
					q += y[r];
				}
				y[k] = n - k - q;

				show(n, k, x, y, show, line);
			}
		}
		if (i < k - 1) {
			y[i] = 0;
		}
		if (i > 0) {
			y[i - 1]++;
		}
	}

	public long G(int[] subset, int[] search) {
		long result = 0;
		for (int i = 0; i < subset.length; i++) {
			result = dc(subset, search, result, i);
		}
		return result;
	}

	public long GL(int[] subset) {
		return G(subset, identity);
	}

	public long GU(int[] subset) {
		return G(subset, complement);
	}
	
	public long GCL(int[] subset) {
		return G(subset, identityC);
	}

	public long GCU(int[] subset) {
		return G(subset, complementC);
	}

	private long dc(int[] subset, int[] search, long result, int i) {
		double diff = Math.abs(subset[i] - search[i]);
		result += (diff * diff);
		return result;
	}

	public void fullKNSubSet(int n, int k, boolean show, int line) {
		int[] x = new int[k];
		int[] y = new int[k + 1];
		rank = 0;
		this.identity = new int[k];
		this.complement = new int[k];
		this.identityC = new int[k + 1];
		this.complementC = new int[k + 1];
		identityC[0] = n - k;
		complementC[k] = n - k;
		for (int i = 0; i < k; i++) {
			identity[i] = k - i - 1;
			complement[i] = n - i - 1;
		}
		doIt(0, n, k, x, y, show, line);
	}

	private int[] show(int n, int k, int[] x, int[] y, boolean show, int line) {
		if (show) {
			double d = Math.sqrt(((GL(x) - GU(x)) * (GL(x) - GU(x))));
			System.out.print(rank + " " + line + " " + GU(x) + " " + GL(x) + " " + "\t");
			for (int i = 0; i < k; i++) {
				System.out.print(x[i] + " ");
			}
			System.out.print("\t");
			for (int i = k; i >= 0; i--) {
				System.out.print(y[i] + " ");
			}
			System.out.print("\t");
			byte[] compose = ComposeStruct.compose(n, k, x);
			for (int i = 0; i <= k; i++) {
				System.out.print(compose[i] + " ");
			}
			System.out.println();
			rank++;
		}
		return x;
	}

	public static void main(String[] args) {
		int n = 8, k = 3;
		int line = 328;
		boolean showResults = true;
		RecursiveFullKNSubset c = new RecursiveFullKNSubset();

//		System.out.println("K-Subset: C(n,k): C(" + n + ", " + k + ")");
//		System.out.println("Composition: C(n, n - k): C(" + n + ", " + (n - k) + ")");
//		System.out.println();
		long r = System.currentTimeMillis();
		c.fullKNSubSet(n, k, showResults, line);
		System.out.println();

//		System.out.println("Time elapsed: " + (System.currentTimeMillis() - r) + "ms");
	}

}
