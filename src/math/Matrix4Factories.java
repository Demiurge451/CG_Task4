package math;

public class Matrix4Factories {
    public static Matrix4 rotation(double a, int idx){
        Matrix4 r = Matrix4.one();
        if (idx < 0 || idx > 2)
            return r;
        int a1 = (idx + 1) % 3;
        int a2 = (idx + 2) % 3;

        r.setAt(a1,a1, (float) Math.cos(a));
        r.setAt(a1,a2, (float) Math.sin(a));
        r.setAt(a2,a1, -(float) Math.sin(a));
        r.setAt(a2,a2, (float) Math.cos(a));

        return r;
    }

    public enum Axis {
        X("X"),
        Y("Y"),
        Z("Z");
        private final String string;

        Axis(String string) {
            this.string = string;
        }

        public String getString() {
            return string;
        }
    }
    public static Matrix4 rotation(float a, Axis q){
        return rotation(a, q == Axis.X ? 0 : q == Axis.Y ? 1 : 2);
    }

    public static Matrix4 scale(float x, float y, float z){
        Matrix4 r = Matrix4.one();
        r.setAt(0,0,x);
        r.setAt(1,1,y);
        r.setAt(2,2,z);
        return r;
    }

    public static Matrix4 scale(float s){
        return scale(s,s,s);
    }

    public static Matrix4 translate(float dx, float dy, float dz){
        Matrix4 r = Matrix4.one();
        r.setAt(0,3,dx);
        r.setAt(1,3,dy);
        r.setAt(2,3,dz);
        return r;
    }

    public static Matrix4 translate(Vector3 v){
        return translate(v.getX(), v.getY(), v.getZ());
    }

    public static  Matrix4 centralProjection(float coef, int idx){
        Matrix4 r = Matrix4.one();
        if (idx < 0 || idx > 2)
            return r;
        r.setAt(3, idx, 1 / coef);
        return r;
    }

    public static Matrix4 centralProjection(float coef, Axis a){
        return centralProjection(coef, a == Axis.X ? 0 : a == Axis.Y ? 1 : 2);
    }
}
