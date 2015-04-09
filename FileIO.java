import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileIO {

	public static String openFile(String fn) {
		try {
			File file = new File(fn);
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			StringBuffer sb = new StringBuffer();

			String line = "";
			line = br.readLine();
			while(line != null) {
				sb.append(line);
				sb.append("\n");
				line = br.readLine();
			}
			fr.close();
			return sb.toString();
		} catch (IOException e) {
			System.out.println(e);
			return "";
		}
	}

}