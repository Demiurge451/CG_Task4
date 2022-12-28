package function;

public class Function2 implements IFunction {

    @Override
    public float compute(float x, float y, float minZ, float maxZ) {
        float res = (float) (Math.pow(x, 3) - Math.pow(y, 2));
        return Math.min(maxZ, Math.max(res, minZ));
    }


}
