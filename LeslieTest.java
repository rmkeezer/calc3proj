import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class LeslieTest {

	public static void main(String[] args) {

		Matrix<Double> leslie = new Matrix<Double>();
		for (int i = 0; i < 9; i++) {
			Vector<Double> row = new Vector<Double>();
			for (int j = 0; j < 9; j++) {
				row.add(0.0);
			}
			if (i == 0) {
				leslie.insert(0, row);
			} else {
				leslie.add(row);
			}
		}

		leslie.get(0).insert(1, 1.2);
		leslie.get(0).insert(2, 1.1);
		leslie.get(0).insert(3, .9);
		leslie.get(0).insert(4, .1);
		leslie.get(1).insert(0, .7);
		leslie.get(2).insert(1, .85);
		leslie.get(3).insert(2, .9);
		leslie.get(4).insert(3, .9);
		leslie.get(5).insert(4, .88);
		leslie.get(6).insert(5, .8);
		leslie.get(7).insert(6, .77);
		leslie.get(8).insert(7, .4);

		System.out.println(leslie);

		Vector<Double> x = new Vector<Double>();
		x.add(2.1e5);
		x.add(1.9e5);
		x.add(1.8e5);
		x.add(2.1e5);
		x.add(2.0e5);
		x.add(1.7e5);
		x.add(1.2e5);
		x.add(0.9e5);
		x.add(0.5e5);

		Vector<Double> x1 = MathUtils.mvmult(leslie.clone(), x, 0);
		System.out.println("2010 = " + x1);
		Vector<Double> x2 = MathUtils.mvmult(MathUtils.mpow(leslie.clone(), 2), x, 0);
		System.out.println("2020 = " + x2);
		Vector<Double> x3 = MathUtils.mvmult(MathUtils.mpow(leslie.clone(), 3), x, 0);
		System.out.println("2030 = " + x3);
		Vector<Double> x4 = MathUtils.mvmult(MathUtils.mpow(leslie.clone(), 4), x, 0);
		System.out.println("2040 = " + x4);
		Vector<Double> x5 = MathUtils.mvmult(MathUtils.mpow(leslie.clone(), 5), x, 0);
		System.out.println("2050 = " + x5);

		double tol = 1e-8;
		if (args.length > 1) {
			tol = Double.valueOf(args[1]);
		}
		System.out.println(MathUtils.power_method(leslie.clone(), tol));

		System.out.println("Change 2nd group growth rate");
		x1 = MathUtils.mvmult(leslie.clone(), x, 0);
		System.out.println("2010 = " + x1);
		x2 = MathUtils.mvmult(MathUtils.mpow(leslie.clone(), 2), x, 0);
		System.out.println("2020 = " + x2);
		leslie.get(0).insert(1, 0.6);
		x3 = MathUtils.mvmult(leslie.clone(), x2, 0);
		System.out.println("2030 = " + x3);
		x4 = MathUtils.mvmult(MathUtils.mpow(leslie.clone(), 2), x, 0);
		System.out.println("2040 = " + x4);
		x5 = MathUtils.mvmult(MathUtils.mpow(leslie.clone(), 3), x, 0);
		System.out.println("2050 = " + x5);

		tol = 1e-8;
		if (args.length > 1) {
			tol = Double.valueOf(args[1]);
		}
		System.out.println(MathUtils.power_method(leslie.clone(), tol));


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