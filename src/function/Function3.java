package function;

public class Function3 implements IFunction {

    @Override
    public float compute(float x, float y, float minZ, float maxZ) {
        float res = (float) (Math.signum(x)*Math.pow(Math.abs(x), 1.0/3) * Math.sin(y));
        return Math.min(maxZ, Math.max(res, minZ));
    }

}
