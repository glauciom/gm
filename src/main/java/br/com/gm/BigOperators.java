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

	public static double eq(int n, int k, int[] x) {
		double result = 0;
		int d = 0;
		int b = 1 + k - 2 * n;
		for (int i = 0; i < k; i++) {
			d += (x[i] - n + i) * (x[i] - n + i);
		}
		double c = ((k / 6.0) * ((2 * k * k) - (3 * k * (2 * n + 1)) + (6 * (n * n)) + (6 * n) + 1));
		c = c - d;

		for (int i = 0; i < k; i++) {
			result = result + (2 * x[i] * (n - i) - x[i] * x[i]);
		}
		return result - c;
	}

	public static void main(String[] args) {
		int n = 30;
		int[] x = { 3, 5, 7, 8, 9, 10, 13, 17 };
		int k = x.length;
		System.out.println(eq(n, k, x));
	}

}
