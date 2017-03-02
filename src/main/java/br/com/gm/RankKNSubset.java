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
 * Class responsible for generate a Given Subset of K of an N-Set, Considering
 * their position of the K-Subset list. Documentation:
 * https://arxiv.org/pdf/math/0503335.pdf
 * 
 * @author Glaucio Melo (glaucio.melo@gmail.com)
 *
 */
public class RankKNSubset {
	private int x, y, index;
	private BigInteger rank, aproximation;
	private int[] subset;

	public int[] rankKNSubsetAlgorithm(String number, int n, int k) {
		this.subset = new int[k];
		x = n;
		y = k - 1;
		rank = new BigInteger(number);
		aproximation = BigInteger.ZERO;
		index = 0;
		for (int i = 0; i < k; i++) {
			subset[i] = element(rank);
		}
		return subset;
	}

	public int element(BigInteger rank) {
		BigInteger aux = null;
		for (int j = 1; j <= x - y + 1; j++) {
			aux = aproximation.add(BigOperators.C(x - j, y));
			System.out.print("C(" + (x - j) + ", " + y + ") ");
			if (aux.compareTo(rank) <= 0)
				aproximation = aux;
			else {
				x = x - j;
				y = y - 1;
				index = index + j;
				return index - 1;
			}
		}
		return index - 1;
	}

	public String toString() {
		StringBuffer k1 = new StringBuffer();
		for (int i = 0; i < subset.length; i++) {
			k1.append(subset[i] + " ");
		}
		return k1.toString();
	}

	public static void main(String[] args) {
		int n = 8;
		int k = 3;
		RankKNSubset rank = new RankKNSubset();
//		int c = BigOperators.C(n, k).intValue();
//		for (int i = 0; i < c; i++) {
//			test.rankKNSubsetAlgorithm(i + "\t", n, k);
//			System.out.println(i + "\t" + test);
//		}
		rank.rankKNSubsetAlgorithm("2000", n, k);
		System.out.println();
		System.out.println(rank);
	}

	public int[] getSubset() {
		return subset;
	}

}