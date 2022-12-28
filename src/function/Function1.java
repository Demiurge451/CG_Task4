package function;

public class Function1 implements IFunction{



    @Override
    public float compute(float x, float y, float minZ, float maxZ) {
        float res = (float) (Math.pow(Math.E, Math.sin(x * 3)) - Math.cos(y * y));
        return Math.min(maxZ, Math.max(res, minZ));
    }

}

