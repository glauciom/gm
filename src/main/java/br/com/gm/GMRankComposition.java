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
 * Class responsible for generate all Composition of N into K elements,
 * Considering their position of the composition list. Documentation:
 * https://arxiv.org/pdf/math/0503335.pdf
 * 
 * @author Glaucio Melo (glaucio.melo@gmail.com)
 *
 */
public class GMRankComposition {

	private int composition[];

	private int n, k;

	private long aproximation, x, y;

	private long i, j, g, complement;

	public GMRankComposition(int n, int k) {
		this.n = n;
		this.k = k;
		composition = new int[k];
	}

	private long factorial(long r, long op) {
		long aux = 1;
		for (long t = r; t > op; t--)
			aux *= t;
		return aux;
	}

	private long getBinomialElements(long r, long s) {
		if ((r - s) < s)
			return (factorial(r, s) / factorial(r - s, 1));
		return (factorial(r, r - s) / factorial(s, 1));
	}

	private long getElement(long serial) {
		for (j = 0; j < n; j++)
			if ((long) (aproximation + getBinomialElements(x - i - j, y - j)) <= serial)
				aproximation += getBinomialElements(x - i - j, y - j);
			else {
				x = x - j;
				y = y - j;
				return j;
			}
		return n;
	}

	private int[] serialCompositionAlgorithm(long serial) {
		complement = 0;
		aproximation = 0;
		x = n + k - 2;
		y = n;
		for (i = 0; i < k - 1; i++) {
			g = getElement(serial);
			composition[(int) ((int) k - 1 - i)] = (int) g;
			if (composition[(int) ((int) k - 1 - i)] != 0)
				complement += composition[(int) (k - 1 - i)];
		}
		composition[0] = (int) (n - complement);
		return composition;
	}

	public void serialComposition(long serial) {
		composition = serialCompositionAlgorithm(serial - 1);
	}

	public String toString() {
		StringBuffer k1 = new StringBuffer();
		for (int i = 0; i < k; i++)
			k1.append(composition[i] + " ");
		return k1.toString();
	}

	public long getNumberOfCompositions() {
		return getBinomialElements(n + k - 1, n);
	}

	public static void main(String[] args) {
		GMRankComposition test = new GMRankComposition(5, 3);
		for (int i = 1; i <= test.getNumberOfCompositions(); i++) {
			test.serialComposition(i);
			System.out.println(i + "\t" + test);
		}
	}

	public int[] getComposition() {
		return composition;
	}

}