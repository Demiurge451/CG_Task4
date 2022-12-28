package function;

public class Function8 implements IFunction {


    @Override
    public float compute(float x, float y, float minZ, float maxZ) {
        float res = (float) (Math.pow(Math.E, ((Math.sin(x) + Math.cos(x)) / (x * x + 1))));
        return Math.min(maxZ, Math.max(res, minZ));
    }


}
