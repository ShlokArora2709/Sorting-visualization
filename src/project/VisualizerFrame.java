package project;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class VisualizerFrame extends JFrame {

    private final int MAX_SPEED = 1000;
    private final int MIN_SPEED = 1;
    private final int MAX_SIZE = 500;
    private final int MIN_SIZE = 1;
    private final int DEFAULT_SPEED = 20;
    private final int DEFAULT_SIZE = 100;

    private final String[] Sorts = {"Bubble", "Selection", "Insertion", "Merge", "Quick", "Heap"};

    private int sizeModifier;

    private JPanel wrapper;
    private JPanel arrayWrapper;
    private JPanel buttonWrapper;
    private JPanel[] squarePanels;
    private JButton start;
    private JComboBox<String> selection;
    private JSlider speed;
    private JSlider size;
    private JLabel speedVal;
    private JLabel sizeVal;
    private GridBagConstraints c;
    private JCheckBox stepped;

    public VisualizerFrame(){
        super("Sorting Visualizer");

        start = new JButton("Start");
        buttonWrapper = new JPanel();
        arrayWrapper = new JPanel();
        wrapper = new JPanel();
        selection = new JComboBox<>();
        speed = new JSlider(MIN_SPEED, MAX_SPEED, DEFAULT_SPEED);
        size = new JSlider(MIN_SIZE, MAX_SIZE, DEFAULT_SIZE);
        speedVal = new JLabel("Speed: 20 ms");
        sizeVal = new JLabel("Size: 100 values");
        stepped = new JCheckBox("Stepped Values");
        c = new GridBagConstraints();

        for (String s : Sorts) selection.addItem(s);

        arrayWrapper.setLayout(new GridBagLayout());
        wrapper.setLayout(new BorderLayout());

        c.insets = new Insets(0, 1, 0, 1);
        c.anchor = GridBagConstraints.SOUTH;

        start.addActionListener(e -> SortingVisualizer.startSort((String) selection.getSelectedItem()));

        stepped.addActionListener(e -> SortingVisualizer.stepped = stepped.isSelected());

        speed.setMinorTickSpacing(10);
        speed.setMajorTickSpacing(100);
        speed.setPaintTicks(true);

        speed.addChangeListener(e -> {
            speedVal.setText("Speed: " + speed.getValue() + "ms");
            SortingVisualizer.sleep = speed.getValue();
        });

        size.setMinorTickSpacing(10);
        size.setMajorTickSpacing(100);
        size.setPaintTicks(true);

        size.addChangeListener(e -> {
            sizeVal.setText("Size: " + size.getValue() + " values");
            SortingVisualizer.sortDataCount = size.getValue();
            SortingVisualizer.resetArray();
        });

        buttonWrapper.add(stepped);
        buttonWrapper.add(speedVal);
        buttonWrapper.add(speed);
        buttonWrapper.add(sizeVal);
        buttonWrapper.add(size);
        buttonWrapper.add(start);
        buttonWrapper.add(selection);

        wrapper.add(buttonWrapper, BorderLayout.SOUTH);
        wrapper.add(arrayWrapper);

        add(wrapper);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                // Recalculate the sizeModifier when window size changes
                sizeModifier = (int) ((getHeight() * 0.9) / SortingVisualizer.sortDataCount);
            }
        });
    }

    // Initializes the panel array with pre-drawn values
    public void preDrawArray(Integer[] squares) {
        squarePanels = new JPanel[SortingVisualizer.sortDataCount];
        arrayWrapper.removeAll();
        sizeModifier = (int) ((getHeight() * 0.9) / squarePanels.length);
        for (int i = 0; i < SortingVisualizer.sortDataCount; i++) {
            squarePanels[i] = new JPanel();
            squarePanels[i].setPreferredSize(new Dimension(SortingVisualizer.blockWidth, squares[i] * sizeModifier));
            squarePanels[i].setBackground(Color.blue);
            arrayWrapper.add(squarePanels[i], c);
        }
        repaint();
        validate();
    }

    // Re-draws the array during sorting
    public void reDrawArray(Integer[] squares) {
        reDrawArray(squares, -1, -1, -1);
    }

    public void reDrawArray(Integer[] squares, int working) {
        reDrawArray(squares, working, -1, -1);
    }

    public void reDrawArray(Integer[] squares, int working, int comparing) {
        reDrawArray(squares, working, comparing, -1);
    }

    public void reDrawArray(Integer[] squares, int working, int comparing, int reading) {
        arrayWrapper.removeAll();
        for (int i = 0; i < squarePanels.length; i++) {
            squarePanels[i] = new JPanel();
            squarePanels[i].setPreferredSize(new Dimension(SortingVisualizer.blockWidth, squares[i] * sizeModifier));
            if (i == working) {
                squarePanels[i].setBackground(Color.green);
            } else if (i == comparing) {
                squarePanels[i].setBackground(Color.red);
            } else if (i == reading) {
                squarePanels[i].setBackground(Color.yellow);
            } else {
                squarePanels[i].setBackground(Color.blue);
            }
            arrayWrapper.add(squarePanels[i], c);
        }
        repaint();
        validate();
    }
}
