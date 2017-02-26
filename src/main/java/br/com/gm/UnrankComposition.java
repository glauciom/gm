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
 * Class responsible for generating a Composition Rank of N into K elements,
 * Considering their original composition array. Documentation:
 * https://arxiv.org/pdf/math/0503335.pdf
 * 
 * @author Glaucio Melo (glaucio.melo@gmail.com)
 *
 */
public class UnrankComposition {

	private int n, k;
	private BigInteger rank;

	private void prepareAttributes(int[] composition) {
		this.n = this.k = 0;
		this.k = composition.length;
		for (int i = 0; i < composition.length; i++) {
			n += composition[i];
		}
	}

	public BigInteger unrankCompositionAlgorithm(int[] composition) {
		prepareAttributes(composition);
		int x = n + k - 2;
		int y = n;
		rank = BigInteger.ZERO;
		for (int i = composition.length - 1; i >= 1; i--) {
			for (int j = 0; j < composition[i]; j++) {
				rank = rank.add(BigOperators.C(x - j - ((k - 1) - i), y - j));
			}
			x = x - composition[i];
			y = y - composition[i];
		}
		return rank;
	}

	public String toString() {
		return "" + rank;
	}

	public BigInteger getRank() {
		return rank;
	}

	public BigInteger getNumberOfCompositions() {
		return BigOperators.C(n + k - 1, n);
	}

	public static void main(String[] args) {
		int n = 5;
		int k = 3;
		RankComposition test = new RankComposition(n, k);
		for (int i = 0; i < test.getNumberOfCompositions().intValue(); i++) {
			int[] composition = test.rankCompositionAlgorithm(new BigInteger(String.valueOf(i)));
			UnrankComposition unrankComposition = new UnrankComposition();
			unrankComposition.unrankCompositionAlgorithm(composition);
			System.out.println(test + "\t" + unrankComposition);
		}
	}

}