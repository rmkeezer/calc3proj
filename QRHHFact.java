import java.util.ArrayList;

public class QRHHFact {

	private Matrix<Double> QR;
	private Vector<Matrix<Double>> QMat;
	private Matrix<Double> Q;
	private Matrix<Double> R;

	public QRHHFact(Matrix<Double> m) {

		Matrix<Double> q = m;
		Matrix<Double> r = new Matrix<Double>();
		QMat = new Vector<Matrix<Double>>();

		for (int i = 0; i < q.length() - 1; i++) {
			Matrix<Double> I = new Matrix<Double>();
			for (int j = 0; j < q.height() - i; j++) {
				Vector<Double> row = new Vector<Double>();
				for (int k = 0; k < q.length() - i; k++) {
					if (j == k) {
						row.add(1.0);
					} else {
						row.add(0.0);
					}
				}
				if (j == 0) {
					I.insert(0, row);
				} else {
					I.add(row);
				}
			}
			Matrix<Double> col = new Matrix<Double>();
			for (int j = i; j < q.height(); j++) {
				Vector<Double> row = new Vector<Double>();
				for (int k = 0; k < q.length() - i; k++) {
					if (k == 0) {
						row.add(q.get(j).get(i));
					} else {
						row.add(0.0);
					}
				}
				if (j == i) {
					col.insert(0, row);
				} else {
					col.add(row);
				}
			}
			int sign = 1;
			if (col.get(0).get(0) < 0) {
				sign = -1;
			}

			col.get(0).insert(0, col.get(0).get(0) + sign * MathUtils.mag(col.clone()));
			Matrix<Double> uut = MathUtils.mmult(col.clone(), MathUtils.tran(col.clone()));
			double uutm = MathUtils.mag(col.clone())*MathUtils.mag(MathUtils.tran(col.clone()));
			double div = -2.0/uutm;
			Matrix<Double> uutt = MathUtils.msmult(uut.clone(), div);
			Matrix<Double> H = MathUtils.madd(I, uutt.clone());
			q = MathUtils.mmult(fill(H, q.clone()), q.clone());
			QMat.add(0, H);
		}

		Q = QMat.get(0);
		for (int i = 1; i < QMat.size(); i++) {
			Q = MathUtils.mmult(QMat.get(i), Q);
		}
		Q = MathUtils.msmult(Q, -1.0);
		R = q;
		R = MathUtils.msmult(R, -1.0);


		QR = MathUtils.mmult(Q, R);	}

	public Matrix<Double> fill(Matrix<Double> m, Matrix<Double> t) {
		for (int i = t.height() - m.height() - 1; i >= 0; i--) {
			Vector<Double> row = new Vector<Double>();
			for (int j = 0; j < t.length() - i; j++) {
				if (j == 0) {
					row.add(1.0);
				} else {
					row.add(0.0);
				}
			}
			m.add(0, row);
			for (int k = 1; k < m.height(); k++) {
				m.get(k).add(0, 0.0);
			}
		}
		return m;
	}

	public Matrix<Double> getQ() {
		return Q;
	}
	public Matrix<Double> getR() {
		return R;
	}

	// public Vector<Double> solve(Vector<Double> b) {
 //        int n = QR.height();
 //        int m = QR.length();

 //        Vector<Double> x = new Vector<Double>();
 //        for (int i = 0; i < R.size(); i++) {
 //        	x.add(0.0);
 //        }
 //        Vector<Double> y = b;

 //        // apply Householder transforms to solve Q.y = b
 //        for (int minor = 0; minor < Math.min(m, n); minor++) {

 //            Vector<Double> qrtMinor = QR.get(minor);
 //            double dotProduct = 0;
 //            for (int row = minor; row < m; row++) {
 //                dotProduct += y.get(row) * qrtMinor.get(row);
        
 //            }

 //            System.out.println(dotProduct);
 //            dotProduct /= R.get(minor).get(minor) * qrtMinor.get(minor);
 //            System.out.println(dotProduct);

 //            for (int row = minor; row < m; row++) {
 //                y.insert(row, y.get(row) + dotProduct * qrtMinor.get(row));
 //            }
 //        }

 //        System.out.println(y);

 //        // solve triangular system R.x = y
 //        for (int row = R.size() - 1; row >= 0; row--) {
 //            y.insert(row, y.get(row) / R.get(row).get(row));
 //            double yRow = y.get(row);
 //            Vector<Double> qrtRow = QR.get(row);
 //            x.insert(row, yRow);
 //            for (int i = 0; i < row; i++) {
 //                y.insert(i, y.get(i) - yRow * qrtRow.get(i));
 //            }
 //        }

 //        return x;
 //    }

    public Matrix<Double> solve(Matrix<Double> b) {
	      int nx = b.get(0).size();
	      int n = QR.size();
	      Matrix<Double> X = b;
	      X = MathUtils.mmult(MathUtils.tran(Q), X);
	      for (int k = n-1; k >= 0; k--) {
	         X.get(k).insert(0, X.get(k).get(0) / R.get(k).get(k));
	         for (int i = 0; i < k; i++) {
	            X.get(i).insert(0, X.get(i).get(0) - X.get(k).get(0)*R.get(i).get(k));
	         }
	      }
	      return X;
    }

    public double errorQR(Matrix<Double> h) {
    	return MathUtils.maxNorm(MathUtils.madd(QR, MathUtils.msmult(h, -1.0)));
    }

    public double errorQR(int n) {
    	return MathUtils.maxNorm(MathUtils.madd(QR, MathUtils.msmult(MathUtils.createHilbert(n), -1.0)));
    }

    public double errorX(Matrix<Double> h, Matrix<Double> x, Matrix<Double> b) {
    	return MathUtils.maxNorm(MathUtils.madd(MathUtils.mmult(h, x), MathUtils.msmult(b, -1.0)));
    }

    public double errorX(Matrix<Double> x, Matrix<Double> b, int n) {
    	return MathUtils.maxNorm(MathUtils.madd(MathUtils.mmult(MathUtils.createHilbert(n), x), MathUtils.msmult(b, -1.0)));
    }

}