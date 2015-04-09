import java.util.ArrayList;

public class LUFact {

	private Matrix<Double> H;
	private Matrix<Double> LU;
	private Matrix<Double> Lower;
	private Matrix<Double> Upper;

	public LUFact(Matrix<Double> m) {
		H = m;
		Matrix<Double> up = m;
		Matrix<Double> low = new Matrix<Double>();
		for (int i = 0; i < m.height(); i++) {
			Vector<Double> row = new Vector<Double>();
			for (int j = 0; j < m.length(); j++) {
				if (i == j) {
					row.add(1.0);
				} else {
					row.add(0.0);
				}
			}
			if (i == 0) {
				low.insert(0, row);
			} else {
				low.add(row);
			}
		}

		for (int col = 0; col < m.length(); col++) {
			for (int row = col+1; row < m.height(); row++) {
				Vector<Double> rowV1 = m.get(col);
				Vector<Double> rowV2 = m.get(row);
				double coef = rowV2.get(col) / rowV1.get(col);
				rowV2 = MathUtils.vadd(rowV2, MathUtils.vmult(rowV1, -coef));
				up.insert(row, rowV2);
				low.get(row).insert(col, coef);
			}
		}

		Upper = up;
		Lower = low;
		LU = MathUtils.mmult(Lower, Upper);
	}

	public Matrix<Double> getUpper() {
		return Upper;
	}
	public Matrix<Double> getLower() {
		return Lower;
	}

	public Matrix<Double> solve(Matrix<Double> mb) {
		Matrix<Double> m = new Matrix<Double>();
		Matrix<Double> b = new Matrix<Double>();
		for (int i = 0; i < mb.height(); i++) {
			Vector<Double> row = new Vector<Double>();
			for (int j = 0; j < mb.length(); j++) {
				if (j == mb.length() - 1) {
					Vector<Double> row2 = new Vector<Double>();
					row2.add(mb.get(i).get(j));
					if(i == 0) {
						b.insert(0, row2);
					} else {
						b.add(row2);
					}
				} else {
					row.add(mb.get(i).get(j));
				}
			}
			if (i == 0) {
				m.insert(0, row);
			} else {
				m.add(row);
			}
		}
		int n = m.size();
		Matrix<Double> X = b;

		for (int k = 0; k < n; k++) {
			for (int i = k+1; i < n; i++) {
				X.get(i).insert(0, X.get(i).get(0) - X.get(k).get(0)*Lower.get(i).get(k));
			}
		}
		for (int k = n-1; k >= 0; k--) {
			X.get(k).insert(0, X.get(k).get(0) / Upper.get(k).get(k));
			for (int i = 0; i < k; i++) {
				X.get(i).insert(0, X.get(i).get(0) - X.get(k).get(0)*Upper.get(i).get(k));
			}
		}
		return X;
	}

    public double errorLU(Matrix<Double> h) {
    	return MathUtils.maxNorm(MathUtils.madd(LU, MathUtils.msmult(h, -1.0)));
    }

    public double errorLU(int h) {
    	return MathUtils.maxNorm(MathUtils.madd(LU, MathUtils.msmult(MathUtils.createHilbert(h), -1.0)));
    }

    public double errorX(Matrix<Double> h, Matrix<Double> x, Matrix<Double> b) {
    	return MathUtils.maxNorm(MathUtils.madd(MathUtils.mmult(h, x), MathUtils.msmult(b, -1.0)));
    }

    public double errorX(Matrix<Double> x, Matrix<Double> b, int n) {
    	return MathUtils.maxNorm(MathUtils.madd(MathUtils.mmult(MathUtils.createHilbert(n), x), MathUtils.msmult(b, -1.0)));
    }

}