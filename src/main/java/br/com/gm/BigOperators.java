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
 * Class responsible for provide useful combinatorics functions, with Big
 * Integer support.
 * 
 * @author Glaucio Melo (glaucio.melo@gmail.com)
 * 
 */
public final class BigOperators {

	public static String f(int r, int op) {
		BigInteger aux = BigInteger.ONE;
		for (int t = r; t > op; t--) {
			aux = aux.multiply(BigInteger.valueOf((long) t));
		}
		return aux.toString();
	}

	public static BigInteger C(int r, int s) {
		if ((r - s) < s) {
			return new BigInteger(f(r, s)).divide(new BigInteger(f(r - s, 1)));
		}
		return new BigInteger(f(r, r - s)).divide(new BigInteger(f(s, 1)));
	}
}
