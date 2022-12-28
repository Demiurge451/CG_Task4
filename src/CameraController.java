import math.Matrix4;
import math.Matrix4Factories;
import math.Vector3;
import math.Vector4;
import screen.ScreenConverter;
import screen.ScreenPoint;
import third.Camera;

import javax.swing.*;
import java.awt.event.*;

public class CameraController implements MouseListener, MouseMotionListener, MouseWheelListener {
    public interface RepaintListener {
        void shouldRepaint();
    }

    private RepaintListener repaintListener = null;

    public void setRepaintListener(RepaintListener repaintListener) {
        this.repaintListener = repaintListener;
    }

    protected void onRepaint() {
        if (repaintListener != null)
            repaintListener.shouldRepaint();
    }

    private final Camera camera;
    private final ScreenConverter sc;

    private boolean leftFlag, rightFlag;
    private ScreenPoint prev;

    public CameraController(Camera camera, ScreenConverter sc) {
        this.camera = camera;
        this.sc = sc;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (SwingUtilities.isLeftMouseButton(e))
            leftFlag = true;
        if (SwingUtilities.isRightMouseButton(e))
            rightFlag = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (SwingUtilities.isLeftMouseButton(e))
            leftFlag = false;
        if (SwingUtilities.isRightMouseButton(e))
            rightFlag = false;

        if (!(leftFlag || rightFlag))
            prev = null;
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (prev == null && (leftFlag || rightFlag)) {
            prev = new ScreenPoint(e.getX(), e.getY());
        }
        ScreenPoint cur = new ScreenPoint(e.getX(), e.getY());
        if (prev != null) {
            int dx = cur.getX() - prev.getX();
            int dy = cur.getY() - prev.getY();
            if (leftFlag) {
                double radianSpeedY = dx * Math.PI / 180;
                double radianSpeedX = dy * Math.PI / 180;

                Matrix4 ry = Matrix4Factories.rotation((float) radianSpeedY, Matrix4Factories.Axis.Y);
                Matrix4 rx = Matrix4Factories.rotation((float) radianSpeedX, Matrix4Factories.Axis.X);

                camera.modifyRotation(ry.mul(rx));
            }
            if (rightFlag) {
                Vector4 z = new Vector4(sc.s2r(new ScreenPoint(0, 0)), 0);
                Vector4 c = new Vector4(sc.s2r(new ScreenPoint(dx, dy)), 0);

                Vector3 delta = c.add(z.mul(-1)).toVector3();
                camera.modifyTranslate(Matrix4Factories.translate(delta));
            }
        }

        prev = cur;
        onRepaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (prev == null && (leftFlag || rightFlag)) {
            prev = new ScreenPoint(e.getX(), e.getY());
        }
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        int clicks = e.getWheelRotation();
        float step = 0.05f;
        float coefficient = 1 + (clicks > 0 ? step : -step);

        camera.modifyScale(Matrix4Factories.scale(coefficient));
    }
}
