package math;

public class Vector3 {
    private float[] values;

    public Vector3(float x, float y, float z){
        values = new float[]{x,y,z};
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

    public float at(int idx){
        return values[idx];
    }

    public float length(){
        return (float) Math.sqrt(getX() * getX() + getY() * getY() + getZ() * getZ());
    }
}
