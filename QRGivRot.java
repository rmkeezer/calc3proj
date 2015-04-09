import java.util.ArrayList;

public class QRGivRot {

	private Matrix<Double> QR;
	private Vector<Matrix<Double>> QMat;
	private Matrix<Double> Q;
	private Matrix<Double> R;

	public QRGivRot(Matrix<Double> m) {
		Matrix<Double> q = new Matrix<Double>();
		Matrix<Double> g = new Matrix<Double>();
		Matrix<Double> I = new Matrix<Double>();
		for (int j = 0; j < m.height(); j++) {
			Vector<Double> row = new Vector<Double>();
			for (int k = 0; k < m.length(); k++) {
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
		g = I.clone();
		q = I.clone();

		int itr = 1;
		double a = m.get(0).get(m.length()-2);
		double b = m.get(0).get(m.length()-1);
		double cosX;
		double sinX;

		for (int i = 0; i < m.height(); i++) {
			for (int j = m.length()-1; j > i; j--) {
				
				a = m.get(j-1).get(i);
				b = m.get(j).get(i);
				cosX = a / (Math.sqrt(a*a + b*b));
				sinX = -b / (Math.sqrt(a*a + b*b));

				g.get(j).insert(j, cosX);
				g.get(j).insert(j-1, sinX);
				g.get(j-1).insert(j, -sinX);
				g.get(j-1).insert(j-1, cosX);

                m = MathUtils.mmult(g.clone(), m);	      

                q = MathUtils.mmult(g, q);

                // Turning the Gn matrix back into the identity.
                 
                for (int k = 0; k < m.height(); k++){
                    for (int l = 0; l < m.length(); l++){
                        if (k == l)
                            g.get(k).insert(l, 1.0);
                        else
                            g.get(k).insert(l, 0.0);
                    }
                }           
                itr += 1;
			}
		}
         
        q = MathUtils.tran(q);
        Matrix<Double> answer = MathUtils.mmult(q, m);

		Q = q;
		R = m;
		QR = answer;
	}
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

	public Matrix<Double> getQ() {
		return Q;
	}
	public Matrix<Double> getR() {
		return R;
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