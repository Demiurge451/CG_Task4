package function;

public class Function4 implements IFunction {

    @Override
    public float compute(float x, float y, float minZ, float maxZ) {
        float res = (float) (Math.log(x * x + 1 ) / (y * y + 2));
        return Math.min(maxZ, Math.max(res, minZ));
    }


}
