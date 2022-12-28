package models;

import math.Vector3;
import third.IModel;
import third.PolyLine3D;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Line3D implements IModel {
    private Vector3 p1, p2;

    public Line3D(Vector3 p1, Vector3 p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    public Vector3 getP1() {
        return p1;
    }

    public void setP1(Vector3 p1) {
        this.p1 = p1;
    }

    public Vector3 getP2() {
        return p2;
    }

    public void setP2(Vector3 p2) {
        this.p2 = p2;
    }

    @Override
    public List<PolyLine3D> getLines() {
        ArrayList<Vector3> points = new ArrayList<>();
        points.add(p1);
        points.add(p2);
        PolyLine3D p = new PolyLine3D(points,false);
        LinkedList<PolyLine3D> answer = new LinkedList<>();
        answer.add(p);
        return answer;
    }
}
