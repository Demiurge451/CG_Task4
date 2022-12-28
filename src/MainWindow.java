import function.FuncEnum;
import function.FunctionFactory;

import javax.swing.*;

public class MainWindow extends JDialog{
    private DrawPanel drawPanel;
    private JPanel contentPane;
    private JSpinner spinnerMinX;
    private JSpinner spinnerMaxX;
    private JSpinner spinnerMinY;
    private JSpinner spinnerMaxY;
    private JSpinner spinnerMinZ;
    private JSpinner spinnerMaxZ;
    private JComboBox<String> comboBoxFunction;
    private JButton rotateButton;
    private JSpinner spinnerSpeed;
    private JSpinner spinnerStep;

    public MainWindow() {
        setContentPane(contentPane);
        setModal(true);

        initializeComboBox();
        initializeSpinners();
        drawPanel.setFocusable(true);

        FunctionFactory functionFactory = new FunctionFactory();

        rotateButton.addActionListener(e -> drawPanel.rotate());
        comboBoxFunction.addItemListener(e -> {
            drawPanel.setRotate(false);
            drawPanel.getFunction().setFunction(functionFactory.create(e.getItem().toString()));
            drawPanel.repaint();
        });

        spinnerMinX.addChangeListener(e -> {
            drawPanel.getFunction().setStartX(Float.parseFloat(spinnerMinX.getValue().toString()));
            drawPanel.repaint();
        });

        spinnerMaxX.addChangeListener(e -> {
            drawPanel.getFunction().setFinishX(Float.parseFloat(spinnerMaxX.getValue().toString()));
            drawPanel.repaint();
        });

        spinnerMinY.addChangeListener(e -> {
            drawPanel.getFunction().setStartY(Float.parseFloat(spinnerMinY.getValue().toString()));
            drawPanel.repaint();
        });

        spinnerMaxY.addChangeListener(e -> {
            drawPanel.getFunction().setFinishY(Float.parseFloat(spinnerMaxY.getValue().toString()));
            drawPanel.repaint();
        });

        spinnerMinZ.addChangeListener(e -> {
            drawPanel.getFunction().setStartZ(Float.parseFloat(spinnerMinZ.getValue().toString()));
            drawPanel.repaint();
        });

        spinnerMaxZ.addChangeListener(e -> {
            drawPanel.getFunction().setFinishZ(Float.parseFloat(spinnerMaxZ.getValue().toString()));
            drawPanel.repaint();
        });

        spinnerSpeed.addChangeListener(e -> {
            drawPanel.setSpeed(Float.parseFloat(spinnerSpeed.getValue().toString()));
            drawPanel.repaint();
        });

        spinnerStep.addChangeListener(e -> {
            drawPanel.getFunction().setStep(Float.parseFloat(spinnerStep.getValue().toString()));
            drawPanel.repaint();
        });

        addMouseWheelListener(e -> repaint());
    }

    public void initializeSpinners() {
        spinnerMinX.setModel(new SpinnerNumberModel(-2, -10, 10, 0.1));
        spinnerMinY.setModel(new SpinnerNumberModel(-2, -10, 10, 0.1));
        spinnerMinZ.setModel(new SpinnerNumberModel(-2, -10, 10, 0.1));
        spinnerMaxX.setModel(new SpinnerNumberModel(2, -10, 10, 0.1));
        spinnerMaxY.setModel(new SpinnerNumberModel(2, -10, 10, 0.1));
        spinnerMaxZ.setModel(new SpinnerNumberModel(2, -10, 10, 0.1));
        spinnerStep.setModel(new SpinnerNumberModel(0.1, 0.04, 1, 0.01));
        spinnerSpeed.setModel(new SpinnerNumberModel(1, 0.1, 10, 1));
    }
    public void initializeComboBox() {
        for (FuncEnum func : FuncEnum.values()) {
            comboBoxFunction.addItem(func.getStr());
        }
    }
    public static void main(String[] args) {
        MainWindow dialog = new MainWindow();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
