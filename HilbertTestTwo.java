import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

public class HilbertTestTwo {

    public static void main(String[] args) {

        // Matrix<Double> h = openMatrix("a.dat");
        // System.out.println("H = " + h);
        //Matrix<Double> b = openMatrix("c.dat");
        //System.out.println(b);
        String hherr, hhxsol, hhxerr, gerr, gxsol, gxerr, luerr, luxsol, luxerr;
        hherr = hhxsol = hhxerr = gerr = gxsol = gxerr = luerr = luxsol = luxerr = "";

        for (int n = 2; n < 21; n++) {
            Matrix<Double> b = new Matrix<Double>();
            for (int i = 0; i < n; i++) {
                Vector<Double> row = new Vector<Double>();
                double bi = Math.pow(0.1, ((double)n) / 3);
                row.add(bi);
                if (i == 0) {
                    b.insert(0, row);
                } else {
                    b.add(row);
                }
            }
            // System.out.println(b);

            Matrix<Double> h = MathUtils.createHilbert(n);
            // System.out.println(h);
            // System.out.println(b);

            //System.out.println(MathUtils.lu_fact_LOWER(h));
            //System.out.println(MathUtils.lu_fact_UPPER(h));
            //MathUtils.qr_face_househ(h);

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
            // System.out.println("R = " + MathUtils.qr_fact_househ_R(h));
            hherr += "Householder Error #" + n + " = " + MathUtils.qr_fact_househ_Err(n) + "\n";
            hhxsol += "Householder x Solution #" + n + " = " + MathUtils.solve_qr_b(hb.clone()) + "\n";
            hhxerr += "Householder x Error #" + n + " = " + MathUtils.qr_fact_househ_XErr(hb.clone(), b.clone(), n) + "\n";
            // System.out.println("Q = " + MathUtils.qr_fact_househ_Q(h));
            // System.out.println("R = " + MathUtils.qr_fact_givens_R(h));
            // System.out.println("R = " + MathUtils.qr_fact_givens_Q(h));
            // System.out.println("b = " + MathUtils.qr_solve_b(hb));
            gerr += "Givens Error #" + n + " = " + MathUtils.qr_fact_givens_Err(n) + "\n";
            gxsol += "Givens x Solution #" + n + " = " + MathUtils.solve_qrgiv_b(hb.clone()) + "\n";
            gxerr += "Givens x Error #" + n + " = " + MathUtils.qr_fact_givens_XErr(hb.clone(), b.clone(), n) + "\n";
            // System.out.println("L = " + MathUtils.lu_fact_LOWER(h));
            // System.out.println("U = " + MathUtils.lu_fact_UPPER(h));
            luerr += "LU Fact Error #" + n + " = " + MathUtils.lu_fact_Err(n) + "\n";
            luxsol += "LU x Solution #" + n + " = " + MathUtils.solve_lu_b(hb.clone()) + "\n";
            luxerr += "LU Fact x Error #" + n + " = " + MathUtils.lu_fact_XErr(hb.clone(), b.clone(), n) + "\n";
        }

        System.out.println(hhxsol);
        System.out.println(gxsol);
        System.out.println(luxsol);
        System.out.println(hherr);
        System.out.println(hhxerr);
        System.out.println(gerr);
        System.out.println(gxerr);
        System.out.println(luerr);
        System.out.println(luxerr);

        try {
        PrintWriter pw = new PrintWriter("part1d.txt", "UTF-8");
        pw.println(hherr);
        pw.println(hhxerr);
        pw.println(gerr);
        pw.println(gxerr);
        pw.println(luerr);
        pw.println(luxerr);
        pw.println(hhxsol);
        pw.println(gxsol);
        pw.println(luxsol);
        pw.close();
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        } catch(UnsupportedEncodingException e) {
            e.printStackTrace();
        }
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