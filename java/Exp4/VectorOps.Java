import java.util.Vector;

public class VectorOps {

    private Vector<Double> vector;

    // Constructor
    public VectorOps() {
        vector = new Vector<>(2, 1); // initial size 2, increment 1
    }

    public void addElement(double value) {
        vector.add(value);
    }

    private int len() { //helper function so private
        return vector.size();
    }

//  From  here obj is second vector

    public VectorOps add(VectorOps obj) throws VectorException {
        if (this.len() != obj.len()) {
            throw new VectorException("Vector lengths are not equal. Cannot perform addition.");
        }

        VectorOps result = new VectorOps();
        for (int i = 0; i < this.len(); i++) {
            result.addElement(this.vector.get(i) + obj.vector.get(i));
        }
        return result;
    }

    public VectorOps diff(VectorOps obj) throws VectorException {
        if (this.len() != obj.len()) {
            throw new VectorException("Vector lengths are not equal. Cannot perform subtraction.");
        }

        VectorOps result = new VectorOps();
        for (int i = 0; i < this.len(); i++) {
            result.addElement(this.vector.get(i) - obj.vector.get(i));
        }
        return result;
    }

    public double dot(VectorOps obj) throws VectorException {
        if (this.len() != obj.len()) {
            throw new VectorException("Vector lengths are not equal. Cannot perform dot product.");
        }

        double sum = 0;
        for (int i = 0; i < this.len(); i++) {
            sum += this.vector.get(i) * obj.vector.get(i);
        }
        return sum;
    }

    // Display vector
    public void display() {
        System.out.println(vector);
    }
}