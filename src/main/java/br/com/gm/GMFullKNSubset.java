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
public class GMFullKNSubset {

	int rank;

	private int sum(int k, int[] y) {
		int z = 0;
		for (int i = 0; i < k; i++) {
			z += y[i];
		}
		return z + k;
	}

	private void doIt(int i, int n, int k, int[] x, int[] y, boolean show) {
		for (x[i] = sum(i, y); x[i] <= n - (k - i); x[i]++) {
			if (i < k - 1) {
				doIt(i + 1, n, k, x, y, show);
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

				show(n, k, x, y, show);
			}
		}
		if (i < k - 1) {
			y[i] = 0;
		}
		if (i > 0) {
			y[i - 1]++;
		}
	}

	public void fullKNSubSet(int n, int k, boolean show) {
		int[] x = new int[k];
		int[] y = new int[k + 1];
		rank = 0;
		doIt(0, n, k, x, y, show);
	}

	private int[] show(int n, int k, int[] x, int[] y, boolean show) {
		if (show) {
			System.out.print(rank + "\t");
			for (int i = 0; i < k; i++) {
				System.out.print(x[i] + " ");
			}
			System.out.print("\t");
			for (int i = k; i >= 0; i--) {
				System.out.print(y[i] + " ");
			}
			System.out.print("\t");			
			byte[] compose = GMStruct.compose(n, k, x);
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
		boolean showResults = true;
		GMFullKNSubset c = new GMFullKNSubset();

		System.out.println("K-Subset: C(n,k): C(" + n + ", " + k + ")");
		System.out.println("Composition: C(n, n - k): C(" + n + ", " + (n - k) + ")");
		System.out.println();
		long r = System.currentTimeMillis();
		c.fullKNSubSet(n, k, showResults);
		System.out.println();

		System.out.println("Time elapsed: " + (System.currentTimeMillis() - r) + "ms");
	}

}
