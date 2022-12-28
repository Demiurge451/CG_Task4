package math;

public class Matrix4Factories {
    public static Matrix4 rotation(double angle, int idx) {
        Matrix4 result = Matrix4.one();
        if (idx < 0 || idx > 2)
            return result;
        int a1 = (idx + 1) % 3;
        int a2 = (idx + 2) % 3;

        result.setAt(a1, a1, (float) Math.cos(angle));
        result.setAt(a1, a2, (float) Math.sin(angle));
        result.setAt(a2, a1, -(float) Math.sin(angle));
        result.setAt(a2, a2, (float) Math.cos(angle));

        return result;
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

    public static Matrix4 rotation(float angle, Axis axis) {
        return rotation(angle, axis == Axis.X ? 0 : axis == Axis.Y ? 1 : 2);
    }

    public static Matrix4 scale(float x, float y, float z) {
        Matrix4 result = Matrix4.one();
        result.setAt(0, 0, x);
        result.setAt(1, 1, y);
        result.setAt(2, 2, z);
        return result;
    }

    public static Matrix4 scale(float scale) {
        return scale(scale, scale, scale);
    }

    public static Matrix4 translate(float dx, float dy, float dz) {
        Matrix4 result = Matrix4.one();
        result.setAt(0, 3, dx);
        result.setAt(1, 3, dy);
        result.setAt(2, 3, dz);
        return result;
    }

    public static Matrix4 translate(Vector3 vector) {
        return translate(vector.getX(), vector.getY(), vector.getZ());
    }

    public static Matrix4 centralProjection(float coefficient, int idx) {
        Matrix4 result = Matrix4.one();
        if (idx < 0 || idx > 2)
            return result;
        result.setAt(3, idx, 1 / coefficient);
        return result;
    }

    public static Matrix4 centralProjection(float coefficient, Axis axis) {
        return centralProjection(coefficient, axis == Axis.X ? 0 : axis == Axis.Y ? 1 : 2);
    }
}
