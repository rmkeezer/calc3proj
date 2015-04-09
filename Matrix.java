import java.util.ArrayList;

public class Matrix<T> {

	private Vector<Vector<T>> data;

	public Matrix() {
		data = new Vector<Vector<T>>();
		data.add(new Vector<T>());
	}

	public Matrix(ArrayList<ArrayList<T>> d) {
		for (ArrayList<T> v : d) {
			data.add(new Vector<T>(v));
		}
	}

	public Matrix(Vector<Vector<T>> d) {
		data = new Vector<Vector<T>>();
		for (int i = 0; i < d.size(); i++) {
			Vector<T> v = new Vector<T>();
			for (int j = 0; j < d.get(i).size(); j++) {
				v.add(d.get(i).get(j));
			}
			data.add(v);
		}
	}

	public void add(ArrayList<T> v) {
		data.add(new Vector<T>(v));
	}

	public void add(Vector<T> v) {
		data.add(v);
	}

	public void add(int i, Vector<T> v) {
		data.add(i, v);
	}

	public void remove(ArrayList<T> v) {
		data.remove(new Vector<T>(v));
	}

	public void remove(int i) {
		data.remove(i);
	}

	public Vector<T> get(int i) {
		return data.get(i);
	}

	public void insert(int i, Vector<T> v) {
		data.insert(i, v);
	}

	public int size() {
		return height() > length() ? height() : length();
	}

	public int height() {
		if(data == null) {
			return 0;
		}
		return data.size();
	}

	public int length() {
		if(data == null || data.get(0) == null) {
			return 0;
		}
		return data.get(0).size();
	}

	public String toString() {
		String output = "{\n";
		for (int i = 0; i < height(); i++) {
			output += data.get(i).toString() + "\n";
		}
		output += "}";
		return output;
	}

	public Matrix<T> clone() {
		return new Matrix(data);
	}

}