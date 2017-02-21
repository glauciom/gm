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
public class FullKNSubset {

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
				int q = 0;
				for (int r = 0; r < k - 1; r++) {
					q += y[r];
				}
				y[k - 1] = n - k - q;
				show(k, x, y, show);
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
		int[] y = new int[k];
		doIt(0, n, k, x, y, show);
	}

	private int[] show(int k, int[] x, int[] y, boolean show) {
		if (show) {
			for (int i = 0; i < k; i++) {
				System.out.print(x[i] + 1 + " ");
			}
			System.out.print("\t");
			for (int i = k - 1; i >= 0; i--) {
				System.out.print(y[i] + " ");
			}
			System.out.println();
		}
		return x;
	}

}
