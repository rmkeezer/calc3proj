import java.util.ArrayList;

public class MathUtils {

	public static Vector<Double> vadd(Vector<Double> v1, Vector<Double> v2) {
		if (v1.size() != v2.size()) { 
			throw new RuntimeException("Vectors not same size");
		}
		Vector<Double> output = new Vector<Double>();
		for (int i = 0; i < v1.size(); i++) {
			output.add(v1.get(i) + v2.get(i));
		}
		return output;
	}

	public static Matrix<Double> madd(Matrix<Double> m1, Matrix<Double> m2) {
		if (m1.size() != m2.size()) {
			throw new RuntimeException("Matrices not same size");
		}
		Matrix<Double> output = new Matrix<Double>();
		for (int i = 0; i < m1.height(); i++) {
			Vector<Double> temp = new Vector<Double>();
			for (int j = 0; j < m1.length(); j++) {
				temp.add(m1.get(i).get(j) + m2.get(i).get(j));
			}
			if (i == 0) {
				output.insert(0, temp);
			} else {
				output.add(temp);
			}
		}
		return output;
	}

	public static Vector<Double> vmult(Vector<Double> v1, double s) {
		Vector<Double> output = new Vector<Double>();
		for (int i = 0; i < v1.size(); i++) {
			output.add(v1.get(i) * s);
		}
		return output;
	}

	public static double dot(Vector<Double> v1, Vector<Double> v2) {
		if (v1.size() != v2.size()) { 
			throw new RuntimeException("Vectors not same size");
		}
		double output = 0;
		for (int i = 0; i < v1.size(); i++) {
			output += (v1.get(i) * v2.get(i));
		}
		return output;
	}

	public static Matrix<Double> tran(Matrix<Double> m) {
		Matrix<Double> output = new Matrix<Double>();
		for (int i = 0; i < m.length(); i++) {
			Vector<Double> temp = new Vector<Double>();
			for (int j = 0; j < m.height(); j++) {
				temp.add(m.get(j).get(i));
			}
			if (i == 0) {
				output.insert(0, temp);
			} else {
				output.add(temp);
			}
		}
		return output;
	}

	public static Matrix<Double> mmult(Matrix<Double> m1, Matrix<Double> m2) {
		if (m1.length() != m2.height()) {
			throw new RuntimeException("m1 length of " + m1.length()
				+ " does not equal m2 height of " + m2.height());
		}
		Matrix<Double> output = new Matrix<Double>();
		for (int i = 0; i < m1.height(); i++) {
			Vector<Double> temp = new Vector<Double>();
			for (int j = 0; j < m2.length(); j++) {
				double sum = 0;
				for (int k = 0; k < m1.length(); k++) {
					sum += m1.get(i).get(k) * m2.get(k).get(j);
				}
				temp.add(sum);
			}
			if (i == 0) {
				output.insert(0, temp);
			} else {
				output.add(temp);
			}
		}
		return output;
	}

    public static Vector<Integer> mvmult(Matrix<Integer> m1, Vector<Integer> v1) {
        if (m1.length() != v1.size()) {
            throw new RuntimeException("m1 length of " + m1.length()
                + " does not equal v1 height of " + v1.size());
        }
        Vector<Integer> output = new Vector<Integer>();
        for (int j = 0; j < m1.length(); j++) {
            int sum = 0;
            for (int k = 0; k < v1.size(); k++) {
                sum += m1.get(j).get(k) * v1.get(k);
            }
            output.add(sum % 2);
        }
        return output;
    }

    public static Vector<Double> mvmult(Matrix<Double> m1, Vector<Double> v1, int doesNothing) {
        if (m1.length() != v1.size()) {
            throw new RuntimeException("m1 length of " + m1.length()
                + " does not equal v1 height of " + v1.size());
        }
        Vector<Double> output = new Vector<Double>();
        for (int j = 0; j < m1.height(); j++) {
            double sum = 0;
            for (int k = 0; k < v1.size(); k++) {
                sum += m1.get(j).get(k) * v1.get(k);
            }
            output.add(sum);
        }
        return output;
    }

    public static Matrix<Double> mpow(Matrix<Double> m1, int n) {
        Matrix<Double> temp = new Matrix<Double>();
        temp = m1;
        for (int i = 0; i < n; i++) {
        	m1 = mmult(temp.clone(), m1.clone());
        }
        return m1;
    }


	public static Matrix<Double> msmult(Matrix<Double> m, double s) {
		Matrix<Double> output = new Matrix<Double>();
		for (int i = 0; i < m.height(); i++) {
			Vector<Double> temp = m.get(i);
			for (int j = 0; j < temp.size(); j++) {
				temp.insert(j, temp.get(j) * s);
			}
			if (i == 0) {
				output.insert(0, temp);
			} else {
				output.add(temp);
			}
		}
		return output;
	}


	public static Matrix<Double> msdiv(Matrix<Double> m, double s) {
		Matrix<Double> output = new Matrix<Double>();
		for (int i = 0; i < m.height(); i++) {
			Vector<Double> temp = m.get(i);
			for (int j = 0; j < temp.size(); j++) {
				temp.insert(j, temp.get(j) / s);
			}
			if (i == 0) {
				output.insert(0, temp);
			} else {
				output.add(temp);
			}
		}
		return output;
	}

	public static double det(Matrix<Double> m) {
		if (m.size() == 0) {
			throw new RuntimeException("m size is 0");
		}
		if (m.size() == 1) {
			return m.get(0).get(0);
		}
		if (m.height() == 2 && m.length() == 2) {
			return m.get(0).get(0)*m.get(1).get(1) - m.get(1).get(0)*m.get(0).get(1);
		}
		double output = 0;
		for (int i = 0; i < m.height(); i++) {
			Matrix<Double> temp = new Matrix<Double>();
			for (int j = 1; j < m.height(); j++) {
				int c = 0;
				Vector<Double> temp2 = new Vector<Double>();
				for (int k = 0; k < m.height(); k++) {
					if (k == i) {
						continue;
					}
					temp2.add(c, m.get(j).get(k));
				}
				if (j == 1) {
					temp.insert(0, temp2);
				} else {
					temp.add(temp2);
				}
			}
			output += Math.pow(-1.0, 1.0 + i + 1.0) * m.get(0).get(i) * det(temp);
		}
		return output;
	}

	public static double tr(Matrix<Double> m) {
		if (m.size() == 0) {
			throw new RuntimeException("m size is 0");
		}
		double output = 0;
		for (int i = 0; i < m.height(); i++) {
			output += m.get(i).get(i);
		}
		return output;
	}

	public static double mag(Matrix<Double> m) {
		double sum = 0;
		for (int i = 0; i < m.height(); i++) {
			for (int j = 0; j < m.length(); j++) {
				sum += m.get(i).get(j) * m.get(i).get(j);
			}
		}
		return Math.sqrt(sum);
	}

    public static double mag(Vector<Double> v) {
        double sum = 0;
        for (int i = 0; i < v.size(); i++) {
            sum += v.get(i) * v.get(i);
        }
        return Math.sqrt(sum);
    }

	public static double maxNorm(Matrix<Double> m) {
		double maxNorm = -1;
		for (int i = 0; i < m.height(); i++) {
			for (int j = 0; j < m.length(); j++) {
				if (Math.abs(m.get(i).get(j)) > maxNorm) {
					maxNorm = Math.abs(m.get(i).get(j));
				}
			}
		}
		return maxNorm;
	}

	public static Matrix<Double> createHilbert(int n) {
		Matrix<Double> output = new Matrix<Double>();
		for (int i = 0; i < n; i++) {
			Vector<Double> row = new Vector<Double>();
			for (int j = 0; j < n; j++) {
				row.add(1.0 / (i + j + 1.0));
			}
			if (i == 0) {
				output.insert(0, row);
			} else {
				output.add(row);
			}
		}
		return output;
	}

	public static Matrix<Double> lu_fact_LOWER(Matrix<Double> m) {
		LUFact lu = new LUFact(m.clone());
		return lu.getLower();
	}

	public static Matrix<Double> lu_fact_UPPER(Matrix<Double> m) {
		LUFact lu = new LUFact(m.clone());
		return lu.getUpper();
	}

	public static Matrix<Double> qr_fact_househ_Q(Matrix<Double> m) {
		QRHHFact qrhh = new QRHHFact(m.clone());
		return qrhh.getQ();
	}

	public static Matrix<Double> qr_fact_househ_R(Matrix<Double> m) {
		QRHHFact qrhh = new QRHHFact(m.clone());
		return qrhh.getR();
	}	

	public static Matrix<Double> qr_fact_givens_Q(Matrix<Double> m) {
		QRGivRot qrgv = new QRGivRot(m.clone());
		return qrgv.getQ();
	}

	public static Matrix<Double> qr_fact_givens_R(Matrix<Double> m) {
		QRGivRot qrgv = new QRGivRot(m.clone());
		return qrgv.getR();
	}	

	public static Matrix<Double> solve_lu_b(Matrix<Double> mb) {
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
		LUFact lu = new LUFact(m);
		return lu.solve(mb);
	}

	public static Matrix<Double> solve_qr_b(Matrix<Double> mb) {
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
		QRHHFact qr = new QRHHFact(m);
		return qr.solve(b);
	}

	public static Matrix<Double> solve_qrgiv_b(Matrix<Double> mb) {
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
		QRGivRot qr = new QRGivRot(m);
		return qr.solve(b);
	}

	public static double qr_fact_househ_Err(Matrix<Double> m) {
		QRHHFact qrhh = new QRHHFact(m.clone());
		return qrhh.errorQR(m);
	}

	public static double qr_fact_househ_Err(int n) {
		QRHHFact qrhh = new QRHHFact(MathUtils.createHilbert(n));
		return qrhh.errorQR(n);
	}

	public static double qr_fact_househ_XErr(Matrix<Double> h, Matrix<Double> hb, Matrix<Double> b) {
		QRHHFact qrhh = new QRHHFact(h.clone());
		return qrhh.errorX(h.clone(), solve_qr_b(hb.clone()), b.clone());
	}

	public static double qr_fact_househ_XErr(Matrix<Double> hb, Matrix<Double> b, int n) {
		QRHHFact qrhh = new QRHHFact(MathUtils.createHilbert(n));
		return qrhh.errorX(solve_qr_b(hb.clone()), b.clone(), n);
	}

	public static double qr_fact_givens_Err(Matrix<Double> m) {
		QRGivRot qrgr = new QRGivRot(m.clone());
		return qrgr.errorQR(m);
	}

	public static double qr_fact_givens_Err(int n) {
		QRGivRot qrgr = new QRGivRot(MathUtils.createHilbert(n));
		return qrgr.errorQR(n);
	}

	public static double qr_fact_givens_XErr(Matrix<Double> h, Matrix<Double> hb, Matrix<Double> b) {
		QRGivRot qrgr = new QRGivRot(h.clone());
		return qrgr.errorX(h.clone(), solve_qrgiv_b(hb.clone()), b.clone());
	}

	public static double qr_fact_givens_XErr(Matrix<Double> hb, Matrix<Double> b, int n) {
		QRGivRot qrgr = new QRGivRot(MathUtils.createHilbert(n));
		return qrgr.errorX(solve_qrgiv_b(hb.clone()), b.clone(), n);
	}


	public static double lu_fact_Err(Matrix<Double> m) {
		LUFact lu = new LUFact(m.clone());
		return lu.errorLU(m.clone());
	}

	public static double lu_fact_Err(int n) {
		LUFact lu = new LUFact(MathUtils.createHilbert(n));
		return lu.errorLU(n);
	}

	public static double lu_fact_XErr(Matrix<Double> h, Matrix<Double> hb, Matrix<Double> b) {
		LUFact lu = new LUFact(h.clone());
		return lu.errorX(h.clone(), solve_lu_b(hb.clone()), b.clone());
	}

	public static double lu_fact_XErr(Matrix<Double> hb, Matrix<Double> b, int n) {
		LUFact lu = new LUFact(MathUtils.createHilbert(n));
		return lu.errorX(solve_lu_b(hb.clone()), b.clone(), n);
	}

	public static double power_method(Matrix<Double> m, double tol) {
		Matrix<Double> guess = new Matrix<Double>();
		for (int i = 0; i < m.height(); i++) {
			Vector<Double> row = new Vector<Double>();
			row.add(1.0);
			if (i == 0) {
				guess.insert(0, row);
			} else {
				guess.add(row);
			}
		}
		double eigen1 = MathUtils.maxNorm(m);
		double eigen2 = MathUtils.maxNorm(m);
        int itr = 0;
		do {
			eigen1 = eigen2;
			guess = MathUtils.mmult(m, guess);
			eigen2 = MathUtils.maxNorm(guess);
			guess = MathUtils.msdiv(guess, eigen2);
            itr++;
            if (itr > 100) {
                throw new Error("Method did not produce a result within " + itr + " iterations.");
            }
		} while (Math.abs(eigen1 - eigen2) > tol);
		System.out.println("Power Method completed after " + itr + " iterations");
		return eigen2;
	}

    public static Matrix<Integer> A0;
    public static Matrix<Integer> A1;

    public static Vector<Integer> encoding(Vector<Integer> x) {
        A0 = new Matrix<Integer>();
        A1 = new Matrix<Integer>();
        Vector<Integer> y = new Vector<Integer>();
        Vector<Integer> y1 = new Vector<Integer>();
        Vector<Integer> y2 = new Vector<Integer>();
        for (int i = 0; i < x.size() + 3; i++) {
            Vector<Integer> a0 = new Vector<Integer>();
            for (int j = 0; j < x.size() + 3; j++) {
                if ( i - 3 >= 0 && j == i - 3) {
                    a0.add(1);
                } else if (i - 2 >= 0 && j == i - 2) {
                    a0.add(1);
                } else if (i >= 0 && j == i) {
                    a0.add(1);
                } else {
                    a0.add(0);
                }
            }
            if (i == 0) {
                A0.insert(0, a0);
            } else {
                A0.add(a0);
            }
        }
        for (int i = 0; i < x.size() + 3; i++) {
            Vector<Integer> a1 = new Vector<Integer>();
            for (int j = 0; j < x.size() + 3; j++) {
                if ( i - 3 >= 0 && j == i - 3) {
                    a1.add(1);
                } else if (i - 1 >= 0 && j == i - 1) {
                    a1.add(1);
                } else if (i >= 0 && j == i) {
                    a1.add(1);
                } else {
                    a1.add(0);
                }
            }
            if (i == 0) {
                A1.insert(0, a1);
            } else {
                A1.add(a1);
            }
        }
        for (int i = 0; i < 3; i++) {
            x.add(0);
        }
        y1 = mvmult(A0, x);
        y2 = mvmult(A1, x);
        for (int i = 0; i < y1.size(); i++) {
            y.add(y1.get(i));
            y.add(y2.get(i));
        }

        return y;
    }

    public static Vector<Double> jacobi(Matrix<Double> A, Vector<Double> y, Vector<Double> x0, double tol) {
        Vector<Double> xtemp = new Vector<Double>();
        for (int i = 0; i < x0.size(); i++) {
            xtemp.add(x0.get(i));
        }
        Vector<Double> ytemp = new Vector<Double>();
        for (int i = 0; i < x0.size(); i++) {
            ytemp.add(x0.get(i));
        }
        double before = mag(ytemp);
        int itr = 0;
        do {
            before = mag(ytemp);
            for (int i = 0; i < xtemp.size(); i++) {
                double temp = y.get(i);
                for (int j = 0; j < A.size(); j++) {
                    if (j != i) {
                        temp -= A.get(i).get(j) * xtemp.get(j);
                    }
                }
                temp /= A.get(i).get(i);
                ytemp.insert(i, temp);
            }
            xtemp = ytemp;
            itr++;
            if (itr > 100) {
                throw new Error("Method did not produce a result within " + itr + " iterations.");
            }
        } while (Math.abs(mag(ytemp) - before) > tol);
        System.out.println("The Jacobi Iteration completed after " + itr + " iterations.");
        return xtemp;
    }

    public static Vector<Double> gauss_seidel(Matrix<Double> A, Vector<Double> y, Vector<Double> x0, double tol) {
        Vector<Double> xtemp = new Vector<Double>();
        for (int i = 0; i < x0.size(); i++) {
            xtemp.add(x0.get(i));
        }
        double before = mag(xtemp);
        int itr = 0;
        do {
            before = mag(xtemp);
            for (int i = 0; i < xtemp.size(); i++) {
                double temp = y.get(i);
                for (int j = 0; j < A.size(); j++) {
                    if (j != i) {
                        temp -= A.get(i).get(j) * xtemp.get(j);
                    }
                }
                temp /= A.get(i).get(i);
                xtemp.insert(i, temp);
            }
            itr++;
            if (itr > 100) {
                throw new Error("Method did not produce a result within " + itr + " iterations.");
            }
        } while (Math.abs(mag(xtemp) - before) > tol);
        System.out.println("The Gauss Seidel Iteration completed after " + itr + " iterations.");

        return xtemp;
    }

}