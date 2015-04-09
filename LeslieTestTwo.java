import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class LeslieTestTwo {

	public static void main(String[] args) {

		

		Matrix<Double> leslie = new Matrix<Double>();
		try {
			leslie = openMatrix(args[0]);
		} catch (IOException e) {
            System.err.println("You did not enter a valid filename");
        }
		Vector<Double> x = new Vector<Double>();
		try {
			Matrix<Double> temp = openMatrix(args[2]);
            for (int i = 0; i < temp.height(); i++) {
                x.add(temp.get(i).get(temp.length()-1));
            }
		} catch (IOException e) {
            System.err.println("You did not enter a valid filename");
        }


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