package third;

import math.Matrix4;
import math.Matrix4Factories;
import math.Vector3;
import math.Vector4;

public class Camera implements ICamera{
    //translate, rotate, scale, projection;
    private Matrix4 t, r, s, p;

    public Camera() {
        t = Matrix4.one();
        r = Matrix4.one();
        s = Matrix4.one();
        p = Matrix4Factories.centralProjection(10,Matrix4Factories.Axis.Z);//Matrix4.one();
    }

    @Override
    public Vector3 w2c(Vector3 v) {
        return  p.mul(
                    t.mul(
                        r.mul(
                            s.mul(new Vector4(v,1)
                            )
                        )
                    )
            ).toVector3();
    }
    public void modifyRotation(Matrix4 m){
        r = m.mul(r);
    }

    public void modifyTranslate(Matrix4 m){
        t = m.mul(t);
    }

    public void modifyScale(Matrix4 m) {
        s = m.mul(s);
    }
}
