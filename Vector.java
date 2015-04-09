import java.util.ArrayList;
import java.util.List;
import java.text.DecimalFormat;

public class Vector<T> {

	public static int DEC = 6;
	private List<T> data;

	public Vector() {
		data = new ArrayList<T>();
	}

	public Vector(List<T> d) {
		data = d;
	}

	public void add(T e) {
		data.add(e);
	}

	public void add(int i, T e) {
		data.add(i, e);
	}

	public void remove(T e) {
		data.remove(e);
	}

	public void remove(int i) {
		data.remove(i);
	}

	public T get(int i) {
		return data.get(i);
	}

	public void insert(int i, T e) {
		data.set(i, e);
	}

	public int size() {
		return data.size();
	}

	public List<T> getData() {
		return data;
	}

	public String toString() {
		String output = "[ ";
		String dec = "#.";
		for (int i = 0; i < DEC; i++) {
			dec += "0";
		}
		DecimalFormat df = new DecimalFormat(dec); 
		if (data.get(0) instanceof Integer) {
			df = new DecimalFormat("#"); 
		}
		for (T e : data) {
			output += df.format(e) + " ";
		}
		output += "]";
		return output;
	}

}