package function;

public class Function7 implements IFunction{


    @Override
    public float compute(float x, float y, float minZ, float maxZ) {
        float res = (float) (x * x * x * x + y * y * y * y * (x * x + y * y));
        return Math.min(maxZ, Math.max(res, minZ));
    }


}
