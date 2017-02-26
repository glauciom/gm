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
 * Class responsible for generating a Rank of K-Subset of N set, Considering
 * their original k-subset array. Documentation:
 * https://arxiv.org/pdf/math/0503335.pdf
 * 
 * @author Glaucio Melo (glaucio.melo@gmail.com)
 *
 */
public class UnrankKNSubset {

	private int n, k;
	private BigInteger rank;

	public UnrankKNSubset(int n) {
		this.n = n;
	}

	public BigInteger unrankKNSubsetAlgorithm(int[] subset) {
		return element(subset);
	}

	public BigInteger element(int[] subset) {
		this.k = subset.length;
		int x = n;
		int y = k - 1;
		int index = 0;
		rank = BigInteger.ZERO;
		for (int i = 0; i < subset.length; i++) {
			for (int j = 1; j < subset[i] - index; j++)
				rank = rank.add(BigOperators.C(x - j, y));
			x = x - (subset[i] - index);
			y = y - 1;
			index = subset[i];
		}
		return rank;
	}

	public String toString() {
		return rank.toString();
	}

	public static void main(String[] args) {
		int n = 5;
		int k = 3;
		RankKNSubset test = new RankKNSubset();
		int k1 = BigOperators.C(n, k).intValue();
		UnrankKNSubset test1 = new UnrankKNSubset(n);

		for (int i = 0; i < k1; i++) {
			test.rankKNSubsetAlgorithm(i + "", n, k);
			test1.unrankKNSubsetAlgorithm(test.getSubset());
			System.out.println(test + "\t" + test1);
		}

	}
}