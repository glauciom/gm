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

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Class responsible for Rank sub-routines for KN subset combinatorial elements.
 * Testing default content reading.
 * 
 * @author Glaucio Melo (glaucio.melo@gmail.com)
 * 
 */
public class RankAlgorithm {

	public static void main(String[] args) throws IOException {

		Path path = Paths.get(args[0]);
		byte[] b = Files.readAllBytes(path);
		ComposeStruct g = ComposeStruct.marshalling(b);
		Map<Byte, Integer> values = new HashMap<Byte, Integer>();
		for (int i = 0; i < g.y.length; i++) {
			Integer current = values.get(g.y[i]);
			if (current == null) {
				values.put(g.y[i], 1);
			} else {
				values.put(g.y[i], ++current);
			}
		}
		Iterator<Map.Entry<Byte, Integer>> it = values.entrySet().iterator();
		System.out.println("size: " + values.size());
		while (it.hasNext()) {
			Map.Entry<Byte, Integer> pair = it.next();
			System.out.println(pair.getKey() + " = " + pair.getValue());
		}
		for (int i = 0; i < 100; i++) {
			System.out.print(g.y[i] + " ");
		}
		write("output.txt", g.y);

	}

	public static void write(String filename, byte[] y) throws IOException {
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(new File(filename));
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			baos.write(y);
			baos.writeTo(fos);
		} catch (IOException ioe) {
			// Handle exception here
			ioe.printStackTrace();
		} finally {
			fos.close();
		}
	}
}
