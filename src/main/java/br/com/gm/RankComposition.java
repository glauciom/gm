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
 * Class responsible for generate a Given Composition of N into K elements,
 * Considering their position of the composition list. Documentation:
 * https://arxiv.org/pdf/math/0503335.pdf
 * 
 * @author Glaucio Melo (glaucio.melo@gmail.com)
 *
 */
public class RankComposition {

	private int composition[];

	private int n, k, x, y;

	private BigInteger aproximation;

	public RankComposition(int n, int k) {
		this.n = n;
		this.k = k;
		composition = new int[k];
	}

	private int getElement(BigInteger rank, int i) {
		for (int j = 0; j < n; j++) {
			BigInteger temp = aproximation.add(BigOperators.C(x - i - j, y - j));
			if (temp.compareTo(rank) <= 0) {
				aproximation = temp;
			} else {
				x = x - j;
				y = y - j;
				return j;
			}
		}
		return n;
	}

	public int[] rankCompositionAlgorithm(BigInteger rank) {
		int complement = 0;
		aproximation = BigInteger.ZERO;
		x = n + k - 2;
		y = n;
		for (int i = 0; i < k - 1; i++) {
			int r = k - i - 1;
			composition[r] = getElement(rank, i);
			if (composition[r] != 0) {
				complement += composition[r];
			}
		}
		composition[0] = n - complement;
		return composition;
	}

	@Override
	public String toString() {
		StringBuffer k1 = new StringBuffer();
		for (int i = 0; i < k; i++) {
			k1.append(composition[i] + " ");
		}
		return k1.toString();
	}

	public BigInteger getNumberOfCompositions() {
		return BigOperators.C(n + k - 1, n);
	}

	public static void main(String[] args) {
		int n = 15;
		int k = 5;
//		RankComposition test = new RankComposition(n, k);
//		for (int i = 0; i < test.getNumberOfCompositions().intValue(); i++) {
//			test.rankCompositionAlgorithm(new BigInteger(String.valueOf(i)));
//			System.out.println(i + "\t" + test);
//		}
		RankComposition rank = new RankComposition(n, k);
		rank.rankCompositionAlgorithm(new BigInteger("1706"));
		System.out.println(rank);
	}

	public int[] getComposition() {
		return composition;
	}

}