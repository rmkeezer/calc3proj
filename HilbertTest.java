import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class HilbertTest {

	public static void main(String[] args) {

		// Matrix<Double> h = openMatrix("a.dat");
		// System.out.println("H = " + h);
		Matrix<Double> b = openMatrix("c.dat");
		//System.out.println(b);

		int n = 4;
		if (args[0] != null) {
			n = Integer.parseInt(args[0]);
		}		
		if (n < 4) {
			for (int i = 0; i < 4 - n; i++) {
				b.remove(3 - i);
			}
		} else if (n > 4) {
			double temp = b.get(0).get(0);
			for (int i = 0; i < n - 4; i++) {
				Vector<Double> tempRow = new Vector<Double>();
				tempRow.add(temp);
				b.add(tempRow);
			}
		}
		Matrix<Double> h = MathUtils.createHilbert(n);
		System.out.println(h);
		System.out.println(b);

		Matrix<Double> test = new Matrix<Double>();
		Vector<Double> t1 = new Vector<Double>();
		t1.add(1.0);
		t1.add(0.0);
		t1.add(1.0);
		test.insert(0, t1);
		Vector<Double> t2 = new Vector<Double>();
		t2.add(0.0);
		t2.add(1.0);
		t2.add(1.0);
		test.add(t2);
		Vector<Double> t3 = new Vector<Double>();
		t3.add(1.0);
		t3.add(1.0);
		t3.add(0.0);
		test.add(t3);

		Matrix<Double> hb = h.clone();
		for (int i = 0; i < b.height(); i++) {
			hb.get(i).add(b.get(i).get(0));
		}
		System.out.println("Q = " + MathUtils.qr_fact_househ_Q(h));
		System.out.println("R = " + MathUtils.qr_fact_househ_R(h));
		System.out.println("Error = " + MathUtils.qr_fact_househ_Err(n));
		// System.out.println("Error = " + MathUtils.qr_fact_househ_XErr(hb, b, n));
		// System.out.println("R = " + MathUtils.qr_fact_givens_R(h));
		// System.out.println("R = " + MathUtils.qr_fact_givens_Q(h));
		// System.out.println("b = " + MathUtils.qr_solve_b(hb));
		// System.out.println("Error = " + MathUtils.qr_fact_givens_Err(n));
		// System.out.println("Error = " + MathUtils.qr_fact_givens_XErr(hb, b, n));
		// System.out.println("L = " + MathUtils.lu_fact_LOWER(h));
		// System.out.println("U = " + MathUtils.lu_fact_UPPER(h));
		// System.out.println("Error = " + MathUtils.lu_fact_Err(n));
		// System.out.println("Error = " + MathUtils.lu_fact_XErr(hb, b, n));


	}

	public static Matrix<Double> openMatrix(String fn) {
		String file = FileIO.openFile(fn);
		int height = 0;
		for (int i = 0; i < file.length(); i++) {
			if (file.charAt(i) == '\n') {
				height++;
			}
		}
		List<String> ele = Arrays.asList(file.split("\\s"));
		int rowSize = ele.size() / height;
		Matrix<Double> output = new Matrix<Double>();
		for (int i = 0; i < height; i++) {
			List<Double> row = new ArrayList<Double>();
			for (int j = i*rowSize; j < rowSize + i*rowSize; j++) {
				row.add(Double.parseDouble(ele.get(j)));
			}
			if (i == 0) {
				output.insert(0, new Vector<Double>(row));
			} else {
				output.add(new Vector<Double>(row));
			}
		}
		return output;
	}

}