package math;

public class Matrix4 {
    private final float[] matrix;


    public Matrix4(float[][] matrix){
        this.matrix = new float[16];
        for (int i = 0; i < 4; i++) {
            System.arraycopy(matrix[i], 0, this.matrix, i * 4, 4);
        }
    }

    private Matrix4(float[] arr){
        matrix = arr;
    }

    public float getAt(int row, int col){
        return matrix[row * 4 + col];
    }

    public void setAt(int row, int col, float value){
        matrix[row * 4 + col] = value;
    }

    public static Matrix4 zero(){
        return new Matrix4(new float[16]);
    }

    public static Matrix4 one(){
        Matrix4 matrix = zero();
        for (int i = 0; i < 4; i++) {
            matrix.setAt(i,i,1);
        }
        return matrix;
    }

    public Matrix4 multiply(float value){
        float[] arr = new float[matrix.length];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = matrix[i] * value;
        }
        return new Matrix4(arr);
    }

    public Vector4 multiply(Vector4 vector){
        float[] arr = new float[4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                arr[i] += getAt(i, j) * vector.at(j);
            }
        }
        return new Vector4(arr[0], arr[1], arr[2], arr[3]);
    }

    public Matrix4 multiply(Matrix4 matrix){
        Matrix4 result = zero();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 4; k++) {
                    result.setAt(i,j, result.getAt(i,j) + this.getAt(i,k) * matrix.getAt(k,j));
                }
            }
        }
        return result;
    }
}
