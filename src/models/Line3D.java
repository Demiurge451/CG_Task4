package models;

import math.Vector3;
import third.IModel;
import third.PolyLine3D;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Line3D implements IModel {
    private Vector3 point1, point2;

    public Line3D(Vector3 point1, Vector3 point2) {
        this.point1 = point1;
        this.point2 = point2;
    }

    public Vector3 getPoint1() {
        return point1;
    }

    public void setPoint1(Vector3 point1) {
        this.point1 = point1;
    }

    public Vector3 getPoint2() {
        return point2;
    }

    public void setPoint2(Vector3 point2) {
        this.point2 = point2;
    }

    @Override
    public List<PolyLine3D> getLines() {
        ArrayList<Vector3> points = new ArrayList<>();
        points.add(point1);
        points.add(point2);
        PolyLine3D p = new PolyLine3D(points,false);
        LinkedList<PolyLine3D> answer = new LinkedList<>();
        answer.add(p);
        return answer;
    }
}
