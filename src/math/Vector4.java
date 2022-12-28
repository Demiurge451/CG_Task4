package math;

public class Vector4 {
    private float[] values;

    public Vector4(float x, float y, float z, float w){
        values = new float[]{x,y,z, w};
    }

    public Vector4(float x, float y, float z){
        values = new float[]{x, y, z, 0};
    }

    public Vector4(Vector3 v, float w){
        this(v.getX(), v.getY(), v.getZ(), w);
    }

    public Vector4(Vector3 v){
        this(v, 0f);
    }

    private Vector4(float[] floats){
        this.values = floats;
    }

    public static final float EPS = 1e-8f;
    public Vector4 normalizedW(){
        if (Math.abs(getW()) < EPS)
            return new Vector4(getX(), getY(), getZ(), 0f);
        return new Vector4(getX()/getW(), getY()/ getW(), getZ() / getW(), 1f);
    }

    public Vector3 toVector3(){
        Vector4 v = normalizedW();
        return new Vector3(v.getX(), v.getY(), v.getZ());
    }

    public Vector4 mul(float n ){
        float[]arr= new float[4];
        for (int i = 0; i < 4; i++) {
            arr[i] = at(i) * n;
        }
        return new Vector4(arr);
    }

    public Vector4 add (Vector4 other){
        float[] arr = new float[4];
        for (int i = 0; i < 4; i++) {
            arr[i] = at(i) + other.at(i);
        }
        return new Vector4(arr);
    }

    public float getX(){
        return values[0];
    }

    public float getY(){
        return values[1];
    }

    public float getZ(){
        return values[2];
    }

    public float getW(){ return  values[3];}

    public float at(int idx){
        return values[idx];
    }
}
