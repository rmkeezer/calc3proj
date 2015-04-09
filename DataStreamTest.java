import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class DataStreamTest {

	public static void main(String[] args) {

		// Matrix<Double> h = openMatrix("a.dat");
		// System.out.println("H = " + h);
		//Matrix<Double> b = openMatrix("c.dat");
		//System.out.println(b);

		int n = 5;
		if (args.length > 0) {
			n = Integer.parseInt(args[0]);
		}
		Vector<Integer> ds = new Vector<Integer>();
		for (int i = 0; i < n; i++) {
			ds.add(Math.random() > 0.5 ? 1 : 0);
		}
		double tol = 1e-8;
		if (args.length > 1 && args[1] != null) {
			tol = Double.valueOf(args[1]);
			System.out.println(tol);
		}
		// ds.add(1);
		// ds.add(0);
		// ds.add(1);
		// ds.add(1);
		// ds.add(1);
		// ds.add(0);
		// ds.add(1);
		// ds.add(1);
		// ds.add(1);
		// ds.add(0);
		// ds.add(0);
		// ds.add(0);
		// ds.add(1);
		// ds.add(0);
		System.out.println(ds);
		Vector<Integer> y = MathUtils.encoding(ds);
		Vector<Double> x0 = new Vector<Double>();
		for (int i = 0; i < n + 3; i++) {
			x0.add(0.0);
		}
		// x0.add(0.0);
		// x0.add(0.0);
		// x0.add(0.0);
		// x0.add(0.0);
		// x0.add(0.0);
		// x0.add(0.0);
		// x0.add(0.0);
		// x0.add(0.0);
		// x0.add(0.0);
		// x0.add(0.0);
		// x0.add(0.0);
		// x0.add(0.0);
		// x0.add(0.0);
		// x0.add(0.0);
		// x0.add(0.0);
		// x0.add(0.0);
		// x0.add(0.0);
        Vector<Double> y1 = new Vector<Double>();
        for (int i = 0; i < y.size(); i+=2) {
            y1.add((double)(int)y.get(i));
        }
        Matrix<Double> a0 = new Matrix<Double>();
        for (int j = 0; j < MathUtils.A0.size(); j++) {
	        Vector<Double> row = new Vector<Double>();
	        for (int i = 0; i < MathUtils.A0.size(); i++) {
	            row.add((double)(int)MathUtils.A0.get(j).get(i));
	        }
	        if (j == 0) {
	        	a0.insert(0, row);
	        } else {
	        	a0.add(row);
	        }
        }
		Vector<Double> x1d = MathUtils.jacobi(a0, y1, x0, tol);
		Vector<Integer> x1 = new Vector<Integer>();
        for (int i = 0; i < x1d.size() - 3; i++) {
            x1.add(Math.abs(x1d.get(i).intValue() % 2));
        }
        System.out.println(x1);
        Vector<Double> y2 = new Vector<Double>();
        for (int i = 1; i < y.size(); i+=2) {
            y2.add((double)(int)y.get(i));
        }
        Matrix<Double> a1 = new Matrix<Double>();
        for (int j = 0; j < MathUtils.A1.size(); j++) {
	        Vector<Double> row = new Vector<Double>();
	        for (int i = 0; i < MathUtils.A1.size(); i++) {
	            row.add((double)(int)MathUtils.A1.get(j).get(i));
	        }
	        if (j == 0) {
	        	a1.insert(0, row);
	        } else {
	        	a1.add(row);
	        }
        }
		Vector<Double> x2d = MathUtils.gauss_seidel(a1, y2, x0, tol);
		Vector<Integer> x2 = new Vector<Integer>();
        for (int i = 0; i < x2d.size() - 3; i++) {
            x2.add(Math.abs(x2d.get(i).intValue() % 2));
        }
        System.out.println(x2);
		// if (n < 4) {
		// 	for (int i = 0; i < 4 - n; i++) {
		// 		b.remove(3 - i);
		// 	}
		// } else if (n > 4) {
		// 	double temp = b.get(0).get(0);
		// 	for (int i = 0; i < n - 4; i++) {
		// 		Vector<Double> tempRow = new Vector<Double>();
		// 		tempRow.add(temp);
		// 		b.add(tempRow);
		// 	}
		// }
		// Matrix<Double> h = MathUtils.createHilbert(n);
		// System.out.println(h);
		// System.out.println(b);
		// Matrix<Integer> test = new Matrix<Integer>();
		// Vector<Integer> t1 = new Vector<Integer>();
		// t1.add(5);
		// t1.add(-2);
		// t1.add(3);
		// test.insert(0, t1);
		// Vector<Integer> t2 = new Vector<Integer>();
		// t2.add(-3);
		// t2.add(9);
		// t2.add(1);
		// test.add(t2);
		// Vector<Integer> t3 = new Vector<Integer>();
		// t3.add(2);
		// t3.add(-1);
		// t3.add(-7);
		// test.add(t3);
		// Vector<Integer> y1 = new Vector<Integer>();
		// y1.add(-1);
		// y1.add(2);
		// y1.add(3);
		// ds = new Vector<Integer>();
		// ds.add(0);
		// ds.add(0);
		// ds.add(0);


		// MathUtils.jacobi(test, y1, ds, 0.0);


		// double tol = 0.01;
		// if (args.length > 1) {
		// 	tol = Double.parseDouble(args[1]);
		// }
		// System.out.println(MathUtils.powerMethod(h, tol));

		//System.out.println(MathUtils.lu_fact_LOWER(h));
		//System.out.println(MathUtils.lu_fact_UPPER(h));
		//MathUtils.qr_face_househ(h);

		
		// Matrix<Double> hb = h.clone();
		// for (int i = 0; i < b.height(); i++) {
		// 	hb.get(i).add(b.get(i).get(0));
		// }
		// System.out.println("R = " + MathUtils.qr_fact_househ_R(h));
		// System.out.println("Error = " + MathUtils.qr_fact_househ_Err(n));
		// System.out.println("Q = " + MathUtils.qr_fact_househ_Q(h));
		// System.out.println("R = " + MathUtils.qr_fact_givens_R(h));
		// System.out.println("R = " + MathUtils.qr_fact_givens_Q(h));
		// System.out.println("b = " + MathUtils.qr_solve_b(hb));
		// System.out.println("Error = " + MathUtils.qr_fact_givens_Err(n));
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