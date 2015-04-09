import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

public class Main {

    public static void main(String[] args) {
        boolean quit = false;
        while (!quit) {
            System.out.println("How many decimals should be displayed?");
            BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in));
            int i = 0;
            try {
                i = Integer.parseInt(br1.readLine());
                Vector.DEC = i;
                quit = true;
            } catch (NumberFormatException e){
                System.err.println("You did not enter an integer.");
                continue;
            } catch (IOException e) {
                System.err.println("You did not enter an integer.");
                continue;
            }
        }
        quit = false;
        while (!quit) {
            System.out.println("\n");
            System.out.println("Welcome to Matthew Keezer's Calculus 3 Project!");
            System.out.println("Please type in the corresponding number to go to each part:");
            System.out.println("\n");
            System.out.println("1) Part 1");
            System.out.println("2) Part 2");
            System.out.println("3) Part 3");
            System.out.println("4) Change # of decimals to display");
            System.out.println("5) Exit");
            System.out.println("\n");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Please enter a selection: ");
            int i = 0;
            try {
                i = Integer.parseInt(br.readLine());
            } catch (NumberFormatException e){
                System.err.println("You did not enter an integer.");
                continue;
            } catch (IOException e) {
                System.err.println("You did not enter an integer.");
                continue;
            }
            if (i == 1) {
                part1();
            } else if (i == 2) {
                part2();
            } else if (i == 3) {
                part3();
            } else if (i == 4) {
                boolean stop = false;
                while (!stop) {
                    System.out.println("How many decimals should be displayed?");
                    BufferedReader br2 = new BufferedReader(new InputStreamReader(System.in));
                    int k = 0;
                    try {
                        k = Integer.parseInt(br2.readLine());
                        Vector.DEC = k;
                        stop = true;
                    } catch (NumberFormatException e){
                        System.err.println("You did not enter an integer.");
                        continue;
                    } catch (IOException e) {
                        System.err.println("You did not enter an integer.");
                        continue;
                    }
                }
            } else if (i == 5) {
                quit = true;
            } else {
                System.err.println("You did not enter a correct number.");
                continue;
            }
        }
    }

    public static void part1() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        boolean stop = false;
        while (!stop) {
            System.out.println("\n");
            System.out.println("Which program do you want to run?");
            System.out.println("\n");
            System.out.println("1) lu_fact");
            System.out.println("2) qr_fact_househ");
            System.out.println("3) qr_fact_givens");
            System.out.println("4) solve_lu_b");
            System.out.println("5) solve_qr_b");
            System.out.println("6) Hilbert Matrix 2 - 20");
            System.out.println("7) Back to menu");
            System.out.println("\n");
            System.out.println("Please enter a selection: ");
            int j = 0;
            try {
                j = Integer.parseInt(br.readLine());
            } catch (NumberFormatException e){
                System.err.println("You did not enter an integer.");
                continue;
            } catch (IOException e) {
                System.err.println("You did not enter an integer.");
                continue;
            }

            if (j == 1) {
                System.out.println("\n");
                System.out.println("Please enter the matrix filename(with extension (e.g a.dat)): ");
                String fn = "";
                try {
                    fn = br.readLine();
                    Matrix<Double> m = openMatrix(fn);
                    lu_fact(m);
                } catch (IOException e) {
                    System.err.println("The file you entered does not exist");
                    continue;
                }
            } else if (j == 2) {
                System.out.println("\n");
                System.out.println("Please enter the matrix filename(with extension (e.g a.dat)): ");
                String fn = "";
                try {
                    fn = br.readLine();
                    Matrix<Double> m = openMatrix(fn);
                    qrfhh(m);
                } catch (IOException e) {
                    System.err.println("The file you entered does not exist");
                    continue;
                }
            } else if (j == 3) {
                System.out.println("\n");
                System.out.println("Please enter the matrix filename(with extension (e.g a.dat)): ");
                String fn = "";
                try {
                    fn = br.readLine();
                    Matrix<Double> m = openMatrix(fn);
                    qrfg(m);
                } catch (IOException e) {
                    System.err.println("The file you entered does not exist");
                    continue;
                }
            } else if (j == 4) {
                System.out.println("\n");
                System.out.println("Please enter the matrix filename(with extension (e.g a.dat)): ");
                String fn = "";
                try {
                    fn = br.readLine();
                    Matrix<Double> m = openMatrix(fn);
                    System.out.println("Please enter the n x 1 vector filename(with extension (e.g b.dat)): ");
                    fn = br.readLine();
                    Matrix<Double> b = openMatrix(fn);
                    solvelub(m, b);
                } catch (IOException e) {
                    System.err.println("The file you entered does not exist");
                    continue;
                }
            } else if (j == 5) {

                boolean halt = false;
                while (!halt) {
                    System.out.println("\n");
                    System.out.println("Which QR solution do you want?");
                    System.out.println("\n");
                    System.out.println("1) Householder");
                    System.out.println("2) Givens");
                    int k = 0;
                    try {
                        k = Integer.parseInt(br.readLine());
                    } catch (NumberFormatException e){
                        System.err.println("You did not enter an integer.");
                        continue;
                    } catch (IOException e) {
                        System.err.println("You did not enter an integer.");
                        continue;
                    }
                    if (k == 1) {
                        halt = hh();
                    } else if (k == 2) {
                        halt = givens();
                    } else {
                        System.err.println("You did not enter a correct number.");
                        continue;
                    }
                }
            } else if (j == 6) {
                hilbertRun();
            } else if (j == 7) {
                stop = true;
            } else {
                System.err.println("You did not enter a correct number.");
                continue;
            }
        }
    }

    public static boolean hh() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("\n");
        System.out.println("Please enter the n x n matrix filename(with extension (e.g a.dat)): ");
        String fn = "";
        try {
            fn = br.readLine();
            Matrix<Double> m = openMatrix(fn);
            System.out.println("Please enter the n x 1 vector filename(with extension (e.g b.dat)): ");
            fn = br.readLine();
            Matrix<Double> b = openMatrix(fn);
            solveqrhhb(m, b);
            return true;
        } catch (IOException e) {
            System.err.println("The file you entered does not exist");
        }
        return false;
    }

    public static boolean givens() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("\n");
        System.out.println("Please enter the n x n matrix filename(with extension (e.g a.dat)): ");
        String fn = "";
        try {
            fn = br.readLine();
            Matrix<Double> m = openMatrix(fn);
            System.out.println("Please enter the n x 1 vector filename(with extension (e.g b.dat)): ");
            fn = br.readLine();
            Matrix<Double> b = openMatrix(fn);
            solveqrgb(m, b);
            return true;
        } catch (IOException e) {
            System.err.println("The file you entered does not exist");
        }
        return false;
    }

    public static void part2() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        boolean stop = false;
        while (!stop) {
            System.out.println("\n");
            System.out.println("Which program do you want to run?");
            System.out.println("\n");
            System.out.println("1) encoding");
            System.out.println("2) jacobi");
            System.out.println("3) gauss_seidel");
            System.out.println("4) decoding");
            System.out.println("5) Back to menu");
            System.out.println("\n");
            System.out.println("Please enter a selection: ");
            int j = 0;
            try {
                j = Integer.parseInt(br.readLine());
            } catch (NumberFormatException e){
                System.err.println("You did not enter an integer.");
                continue;
            } catch (IOException e) {
                System.err.println("You did not enter an integer.");
                continue;
            }

            if (j == 1) {
                System.out.println("Please enter length n of the stream x: ");
                int k = 0;
                try {
                    k = Integer.parseInt(br.readLine());
                    encoding(k);
                } catch (NumberFormatException e){
                    System.err.println("You did not enter an integer.");
                    continue;
                } catch (IOException e) {
                    System.err.println("You did not enter an integer.");
                    continue;
                }
            } else if (j == 2) {
                System.out.println("\n");
                System.out.println("Please enter the n x n matrix filename(with extension (e.g a.dat)): ");
                String fn = "";
                try {
                    fn = br.readLine();
                    Matrix<Double> m = openMatrix(fn);
                    System.out.println("Please enter the n x 1 vector filename(with extension (e.g b.dat)): ");
                    fn = br.readLine();
                    Matrix<Double> b = openMatrix(fn);
                    Vector<Double> b1 = new Vector<Double>();
                    for (int i = 0; i < b.height(); i++) {
                        b1.add(b.get(i).get(b.length()-1));
                    }
                    System.out.println("Please enter the n x 1 initial guess vector filename(with extension (e.g b.dat)): ");
                    fn = br.readLine();
                    Matrix<Double> x0 = openMatrix(fn);
                    Vector<Double> x1 = new Vector<Double>();
                    for (int i = 0; i < x0.height(); i++) {
                        x1.add(x0.get(i).get(x0.length()-1));
                    }
                    System.out.println("Please enter the tol: ");
                    double k = 0.0;
                    k = Double.valueOf(br.readLine());
                    jacobi(m, b1, x1, k);
                } catch (NumberFormatException e){
                    System.err.println("You did not enter an integer.");
                    continue;
                } catch (IOException e) {
                    System.err.println("The file you entered does not exist");
                }
            } else if (j == 3) {
                System.out.println("\n");
                System.out.println("Please enter the n x n matrix filename(with extension (e.g a.dat)): ");
                String fn = "";
                try {
                    fn = br.readLine();
                    Matrix<Double> m = openMatrix(fn);
                    System.out.println("Please enter the n x 1 vector filename(with extension (e.g b.dat)): ");
                    fn = br.readLine();
                    Matrix<Double> b = openMatrix(fn);
                    Vector<Double> b1 = new Vector<Double>();
                    for (int i = 0; i < b.height(); i++) {
                        b1.add(b.get(i).get(b.length()-1));
                    }
                    System.out.println("Please enter the n x 1 initial guess vector filename(with extension (e.g b.dat)): ");
                    fn = br.readLine();
                    Matrix<Double> x0 = openMatrix(fn);
                    Vector<Double> x1 = new Vector<Double>();
                    for (int i = 0; i < x0.height(); i++) {
                        x1.add(x0.get(i).get(x0.length()-1));
                    }
                    System.out.println("Please enter the tol: ");
                    double k = 0.0;
                    k = Double.valueOf(br.readLine());
                    gauss_seidel(m, b1, x1, k);
                } catch (NumberFormatException e){
                    System.err.println("You did not enter an integer.");
                    continue;
                } catch (IOException e) {
                    System.err.println("The file you entered does not exist");
                }
            } else if (j == 4) {
                System.out.println("\n");
                System.out.println("Please enter the file of output binary stream y: ");
                String fn = "";
                try {
                    fn = br.readLine();
                    Matrix<Double> y = openMatrix(fn);
                    Vector<Integer> y1 = new Vector<Integer>();
                    for (int i = 0; i < y.height(); i++) {
                        y1.add((int)(double)y.get(i).get(y.length()-1));
                    }
                    decoding(y1);
                } catch (NumberFormatException e){
                    System.err.println("You did not enter an integer.");
                    continue;
                } catch (IOException e) {
                    System.err.println("You did not enter an integer.");
                    continue;
                }
            } else if (j == 5) {
                stop = true;
            } else {
                System.err.println("You did not enter a correct number.");
                continue;
            }
        }
    }

    public static void part3() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        boolean stop = false;
        while (!stop) {
            System.out.println("\n");
            System.out.println("Which program do you want to run?");
            System.out.println("\n");
            System.out.println("1) power_method");
            System.out.println("2) Back to menu");
            System.out.println("\n");
            System.out.println("Please enter a selection: ");
            int j = 0;
            try {
                j = Integer.parseInt(br.readLine());
            } catch (NumberFormatException e){
                System.err.println("You did not enter an integer.");
                continue;
            } catch (IOException e) {
                System.err.println("You did not enter an integer.");
                continue;
            }

            if (j == 1) {
                System.out.println("\n");
                System.out.println("Please enter the n x n matrix filename(with extension (e.g a.dat)): ");
                String fn = "";
                try {
                    fn = br.readLine();
                    Matrix<Double> m = openMatrix(fn);
                    System.out.println("\n");
                    System.out.println("Please enter the tol: ");
                    double k = 0.0;
                    k = Double.valueOf(br.readLine());
                    System.out.println("\n");
                    power_method(m, k);
                } catch (NumberFormatException e){
                    System.err.println("You did not enter an integer.");
                    continue;
                } catch (IOException e) {
                    System.err.println("You did not enter an integer.");
                    continue;
                }
            } else if (j == 2) {
                stop = true;
            } else {
                System.err.println("You did not enter a correct number.");
                continue;
            }
        }
    }

    public static void lu_fact(Matrix<Double> a) {
        System.out.println("L = " + MathUtils.lu_fact_LOWER(a.clone()));
        System.out.println("U = " + MathUtils.lu_fact_UPPER(a.clone()));
        System.out.println("Error = " + MathUtils.lu_fact_Err(a.clone()));
    }

    public static void qrfg(Matrix<Double> a) {
        System.out.println("R = " + MathUtils.qr_fact_givens_R(a.clone()));
        System.out.println("Q = " + MathUtils.qr_fact_givens_Q(a.clone()));
        System.out.println("Error = " + MathUtils.qr_fact_givens_Err(a.clone()));

    }

    public static void qrfhh(Matrix<Double> a) {
        System.out.println("R = " + MathUtils.qr_fact_househ_R(a.clone()));
        System.out.println("Q = " + MathUtils.qr_fact_househ_Q(a.clone()));
        System.out.println("Error = " + MathUtils.qr_fact_househ_Err(a.clone()));

    }

    public static void solvelub(Matrix<Double> h, Matrix<Double> b) {
        Matrix<Double> hb = h.clone();
        for (int i = 0; i < b.height(); i++) {
            hb.get(i).add(b.get(i).get(0));
        }
        System.out.println("LU x Solution = " + MathUtils.solve_lu_b(hb.clone()));
        System.out.println("Error = " + MathUtils.lu_fact_XErr(h.clone(), hb.clone(), b.clone()));
    }

    public static void solveqrhhb(Matrix<Double> h, Matrix<Double> b) {
        Matrix<Double> hb = h.clone();
        for (int i = 0; i < b.height(); i++) {
            hb.get(i).add(b.get(i).get(0));
        }
        System.out.println("Householder x Solution = " + MathUtils.solve_qr_b(hb.clone()));
        System.out.println("Error = " + MathUtils.qr_fact_househ_XErr(h.clone(), hb.clone(), b.clone()));
    }

    public static void solveqrgb(Matrix<Double> h, Matrix<Double> b) {
        Matrix<Double> hb = h.clone();
        for (int i = 0; i < b.height(); i++) {
            hb.get(i).add(b.get(i).get(0));
        }
        System.out.println("Givens x Solution = " + MathUtils.solve_qrgiv_b(hb.clone()));
        System.out.println("Error = " + MathUtils.qr_fact_givens_XErr(h.clone(), hb.clone(), b.clone()));
    }

    public static void encoding(int n) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Vector<Integer> ds = new Vector<Integer>();
        for (int i = 0; i < n; i++) {
            ds.add(Math.random() > 0.5 ? 1 : 0);
        }
        System.out.println("Input x = " + ds);
        Vector<Integer> y = MathUtils.encoding(ds);
        System.out.println("Output y = " + y);
        System.out.println("\n");
        System.out.println("Do you want to decode y now?");
        System.out.println("\n");
        System.out.println("1) Yes");
        System.out.println("2) No");
        System.out.println("\n");
        System.out.println("Please enter a selection: ");
        int j = 0;
        try {
            j = Integer.parseInt(br.readLine());
        } catch (NumberFormatException e){
            System.err.println("You did not enter an integer.");
        } catch (IOException e) {
            System.err.println("You did not enter an integer.");
        }

        if (j == 1) {
            decoding(y);
        }
    }

    public static void decoding(Vector<Integer> y) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("\n");
        System.out.println("Do you want to use jacobi or gauss seidel to decode?");
        System.out.println("\n");
        System.out.println("1) Jacobi");
        System.out.println("2) Gauss Seidel");
        System.out.println("\n");
        System.out.println("Please enter a selection: ");
        int k = 0;
        try {
            k = Integer.parseInt(br.readLine());
        } catch (NumberFormatException e){
            System.err.println("You did not enter an integer.");
        } catch (IOException e) {
            System.err.println("You did not enter an integer.");
        }

        double tol = 1e-8;
        Vector<Double> x0 = new Vector<Double>();
        for (int i = 0; i < (y.size() / 2); i++) {
            x0.add(0.0);
        }
        if (k == 1) {
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
        } else if (k == 2) {
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
        }
        
    }

    public static void jacobi(Matrix<Double> a0, Vector<Double> ds, Vector<Double> x0, double tol) {
        Vector<Double> x1d = MathUtils.jacobi(a0, ds, x0, tol);
    }

    public static void gauss_seidel(Matrix<Double> a0, Vector<Double> ds, Vector<Double> x0, double tol) {
        Vector<Double> x1d = MathUtils.gauss_seidel(a0, ds, x0, tol);
    }

    public static void power_method(Matrix<Double> h, double tol) {
        System.out.println(MathUtils.power_method(h, tol));
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

    public static void hilbertRun() {
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

        System.out.println("Results printed to part1d.txt");
    }

}