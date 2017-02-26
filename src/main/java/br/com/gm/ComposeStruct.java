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

import java.util.BitSet;

/**
 * Class responsible for byte[] -> subset int[] index conversion
 * 
 * @author Glaucio Melo (glaucio.melo@gmail.com)
 *
 */
public class ComposeStruct {

	private static short BYTE_SIZE = 8;

	int n, k, d;
	int subset[];
	byte[] y;
	static int gap;

	public static ComposeStruct marshalling(byte[] b) {
		ComposeStruct header = new ComposeStruct();
		BitSet set = BitSet.valueOf(b);
		header.n = BYTE_SIZE * b.length;
		header.k = set.cardinality();
		int[] subset = new int[header.k];
		int from = 0;
		for (int i = 0; i < subset.length; i++) {
			subset[i] = set.nextSetBit(from);
			from = subset[i] + 1;
		}
		header.subset = subset;
		header.y = compose(header);
		return header;
	}

	public static byte[] compose(int n, int k, int[] x) {
		byte[] y = new byte[k + 1];
		y[k] = (byte) x[0];
		gap = x[0];
		for (int i = 1; i < k; i++) {
			y[k - i] = (byte) (x[i] - x[i - 1] - 1);
			if (gap < y[k-i]) {
				gap = y[k-i];
			}
		}

		int q = 0;
		for (int r = 1; r <= k; r++) {
			q += y[r];
		}
		y[0] = (byte) (n - k - q);
		if (gap < y[0]) {
			gap = y[0];
		}
		return y;
	}

	public static byte[] compose(ComposeStruct g) {
		return compose(g.n, g.k, g.subset);
	}

	@Override
	public String toString() {
		return "GMStruct [n=" + n + ", k=" + k + ", d=" + d + ", gap=" + gap + "]";
	}

	

}
