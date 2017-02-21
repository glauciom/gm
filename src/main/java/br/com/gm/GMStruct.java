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

import java.util.Arrays;
import java.util.BitSet;

/**
 * Class responsible for byte[] -> subset int[] index conversion
 * 
 * @author Glaucio Melo (glaucio.melo@gmail.com)
 *
 */
public class GMStruct {

	private static short BYTE_SIZE = 8;

	int n, k, d;
	int subset[];

	public static GMStruct marshalling(byte[] b) {
		GMStruct header = new GMStruct();
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
		return header;
	}

	@Override
	public String toString() {
		return "GMStruct [n=" + n + ", k=" + k + ", d=" + d + ", subset=" + Arrays.toString(subset) + "]";
	}

}
