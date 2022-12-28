package function;

public class Function5 implements IFunction {

    @Override
    public float compute(float x, float y, float minZ, float maxZ) {
        if (y <= 0 || x * x * x + 2 == 0) {
            throw new IllegalArgumentException();
        }
        float res = (float) (1 / (x * x * x + 2) + Math.log(y));
        return Math.min(maxZ, Math.max(res, minZ));
    }



}
