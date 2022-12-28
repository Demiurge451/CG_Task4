package models;

import math.Vector3;
import third.IModel;
import third.PolyLine3D;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Box implements IModel {

    private final Vector3 a, b;


    public Box(Vector3 a, Vector3 b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public List<PolyLine3D> getLines() {
        List<PolyLine3D> result = new ArrayList<>();
        result.add(new PolyLine3D(Arrays.asList(new Vector3(a.getX(), a.getY(), a.getZ()),
                new Vector3(a.getX(), b.getY(), a.getZ()),
                new Vector3(b.getX(), b.getY(), a.getZ()),
                new Vector3(b.getX(), a.getY(), a.getZ())), true));
        result.add(new PolyLine3D(Arrays.asList(new Vector3(a.getX(), a.getY(), b.getZ()),
                new Vector3(a.getX(), b.getY(), b.getZ()),
                new Vector3(b.getX(), b.getY(), b.getZ()),
                new Vector3(b.getX(), a.getY(), b.getZ())), true));
        result.add(new PolyLine3D(Arrays.asList(new Vector3(a.getX(), a.getY(), a.getZ()),
                new Vector3(a.getX(), a.getY(), b.getZ()),
                new Vector3(a.getX(), b.getY(), b.getZ()),
                new Vector3(a.getX(), b.getY(), a.getZ())), true));
        result.add(new PolyLine3D(Arrays.asList(new Vector3(b.getX(), a.getY(), a.getZ()),
                new Vector3(b.getX(), a.getY(), b.getZ()),
                new Vector3(b.getX(), b.getY(), b.getZ()),
                new Vector3(b.getX(), b.getY(), a.getZ())), true));
        return result;
    }
}
