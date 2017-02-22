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

import java.math.BigInteger;

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

	private BigInteger aproximation;

	public GMRankComposition(int n, int k) {
		this.n = n;
		this.k = k;
		composition = new int[k];
	}

	private int getElement(BigInteger serial, int i, int x, int y) {
		for (int j = 0; j < n; j++) {
			BigInteger temp = aproximation.add(GM.C(x - i - j, y - j));
			if (temp.compareTo(serial) <= 0) {
				aproximation = temp;
			} else {
				x = x - j;
				y = y - j;
				return j;
			}
		}
		return n;
	}

	private int[] serialCompositionAlgorithm(BigInteger serial) {
		int complement = 0;
		aproximation = BigInteger.ZERO;
		int x = n + k - 2;
		int y = n;
		for (int i = 0; i < k - 1; i++) {
			int r = k - i - 1;
			composition[r] = getElement(serial, i, x, y);
			if (composition[r] != 0) {
				complement += composition[r];
			}
		}
		composition[0] = n - complement;
		return composition;
	}

	public String toString() {
		StringBuffer k1 = new StringBuffer();
		for (int i = 0; i < k; i++) {
			k1.append(composition[i] + " ");
		}
		return k1.toString();
	}

	public BigInteger getNumberOfCompositions() {
		return GM.C(n + k - 1, n);
	}

	public static void main(String[] args) {
		int n = 5;
		int k = 3;
		GMRankComposition test = new GMRankComposition(n, k);
		for (int i = 0; i < test.getNumberOfCompositions().intValue(); i++) {
			test.serialCompositionAlgorithm(new BigInteger(String.valueOf(i)));
			System.out.println(i + "\t" + test);
		}
	}

	public int[] getComposition() {
		return composition;
	}

}