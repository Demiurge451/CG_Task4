package third;

import math.Vector3;
import screen.ScreenConverter;
import screen.ScreenPoint;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Scene {
    private final List<IModel> models = new ArrayList<>();

    public List<IModel> getModels() {
        return models;
    }

    public void draw(Graphics2D gr, ScreenConverter sc, Camera c){
        Comparator<PolyLine3D> comparator = new Comparator<PolyLine3D>() {
            private static final float EPS = 1e-8f;
            @Override
            public int compare(PolyLine3D a, PolyLine3D b) {
                float d = b.avgZ() - a.avgZ();
                if(Math.abs(d) < EPS)
                    return 0;
                return d < 0 ? -1 : 1;
            }
        };
        List<PolyLine3D> toDraw = new ArrayList<>();
        for(IModel m : models){
            for (PolyLine3D p : m.getLines()){
                List<Vector3> points = new ArrayList<>();
                for(Vector3 v: p.getPoints()){
                    points.add(c.w2c(v));
                }
                toDraw.add(new PolyLine3D(points, p.isClosed(), p.getColor() ));
            }
        }
        toDraw.sort(comparator);
        for (PolyLine3D p : toDraw){
            drawPolyLine3D(gr, sc, p);
        }
    }

    private void drawPolyLine3D(Graphics2D gr, ScreenConverter sc, PolyLine3D p){

        List<ScreenPoint> sp = new ArrayList<>();
        for (Vector3 v : p.getPoints())
            sp.add(sc.realToScreen(v));
        if(p.isClosed()){
            Polygon polygon = new Polygon();
            for (ScreenPoint screenPoint : sp) {
                polygon.addPoint(screenPoint.getX(), screenPoint.getY());
            }
            gr.setColor(p.getColor());
            gr.fillPolygon(polygon);
            sp.add(sp.get(0));
        }
        gr.setColor(Color.black);
        for (int i = 0; i < sp.size() - 1; i++) {
            gr.drawLine(sp.get(i).getX(), sp.get(i).getY(), sp.get(i + 1).getX(), sp.get(i + 1).getY());
        }

    }

}
