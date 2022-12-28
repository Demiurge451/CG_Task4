package function;

public class Function6 implements IFunction {


    @Override
    public float compute(float x, float y, float minZ, float maxZ) {
        float res = (float) (Math.abs(y * y * y * y - y * y * y + x * x - x));
        return Math.min(maxZ, Math.max(res, minZ));
    }

}
