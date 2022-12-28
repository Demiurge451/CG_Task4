package third;

import math.Vector3;

import java.awt.*;
import java.util.List;

public class PolyLine3D {
    private List<Vector3> points;
    private boolean closed;
    private Color color;


    public PolyLine3D(List<Vector3> points, boolean closed, Color color) {
        this.points = points;
        this.closed = closed;
        this.color = color;
    }

    public PolyLine3D(List<Vector3> points, boolean closed) {
        this(points, closed, Color.lightGray);
    }

    public List<Vector3> getPoints() {
        return points;
    }

    public void setPoints(List<Vector3> points) {
        this.points = points;
    }

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public float avgZ(){
        if (points == null || points.size() == 0)
            return 0;
        float sum = 0;
        for (Vector3 v: points
             ) {
            sum += v.getZ();
        }
        return sum / points.size();
    }
}
