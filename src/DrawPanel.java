import function.FuncEnum;
import function.FunctionFactory;
import function.IFunction;
import math.Matrix4;
import math.Matrix4Factories;
import models.Function;
import screen.ScreenConverter;
import third.Camera;
import third.Scene;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class DrawPanel extends JPanel {
    public DrawPanel(LayoutManager layout, boolean isDoubleBuffered) {
        super(layout, isDoubleBuffered);
        initialize();
    }

    public DrawPanel(LayoutManager layout) {
        super(layout);
        initialize();
    }

    public DrawPanel(boolean isDoubleBuffered) {
        super(isDoubleBuffered);
        initialize();
    }

    public DrawPanel() {
        initialize();
    }


    private ScreenConverter sc;
    private Camera camera;
    private Scene scene;
    private CameraController cameraController;
    private Timer timer;
    private boolean isRotate = false;
    private Function function;
    private float speed;


    public void setRotate(boolean rotate) {
        this.isRotate = rotate;
        timer.stop();
    }

    public void rotate() {
        if (isRotate)
            timer.stop();
        else
            timer.start();
        isRotate = !isRotate;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }


    public Function getFunction() {
        return function;
    }

    public void setFunction(IFunction function) {
        this.function.setFunction(function);
        repaint();
    }

    private void initialize() {
        sc = new ScreenConverter(800, 600, -3, 3, 6, 6);
        camera = new Camera();
        scene = new Scene();
        cameraController = new CameraController(camera, sc);
        cameraController.setRepaintListener(this::repaint);
        speed = 1;
        int delay = 25;
        FunctionFactory factory = new FunctionFactory();
        function = new Function(factory.create(FuncEnum.FUNC1.getStr()), -2, 2, -2, 2, -2, 2, 0.1f);
        scene.getModels().add(function);
        ActionListener taskPerformer = e -> {
            double radianSpeedY = speed * Math.PI / 180;
            double radianSpeedX = speed * Math.PI / 180;
            Matrix4 ry = Matrix4Factories.rotation((float) radianSpeedY, Matrix4Factories.Axis.Y);
            Matrix4 rx = Matrix4Factories.rotation((float) radianSpeedX, Matrix4Factories.Axis.X);

            camera.modifyRotation(ry.mul(rx));
            repaint();
        };
        timer = new Timer(delay, taskPerformer);
        addMouseListener(cameraController);
        addMouseMotionListener(cameraController);
        addMouseWheelListener(cameraController);
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        sc.setSize(getWidth(), getHeight());
        BufferedImage bi = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D gr = bi.createGraphics();
        gr.setColor(Color.WHITE);
        gr.fillRect(0, 0, getWidth(), getHeight());
        gr.setColor(Color.BLACK);
        scene.draw(gr, sc, camera);
        g.drawImage(bi, 0, 0, null);
        gr.dispose();
    }
}
