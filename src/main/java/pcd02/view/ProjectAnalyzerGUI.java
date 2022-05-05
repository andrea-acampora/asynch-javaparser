package pcd02.view;

import io.vertx.core.eventbus.Message;
import pcd02.controller.Observer;

import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;
import java.util.List;
import javax.swing.*;

public class ProjectAnalyzerGUI {

    private final VisualiserFrame frame;

    public ProjectAnalyzerGUI(){
        this.frame = new VisualiserFrame(300, 300);
    }

    public void start() {
        SwingUtilities.invokeLater(this.frame::start);
    }

    public void addObserver(Observer observer) {
        this.frame.addObserver(observer);
    }

    public void notifyEvent(Message<Object> message) {
        switch (message.body().toString()) {
            case "package":
                this.frame.panel.incrementPackages();
                break;
            case "interface":
                this.frame.panel.incrementInterfaces();
                break;
            case "class":
                this.frame.panel.incrementClasses();
                break;
            case "method":
                this.frame.panel.incrementMethods();
                break;
            case "field":
                this.frame.panel.incrementFields();
                break;
        }
    }

    public class VisualiserFrame extends JFrame implements ActionListener {

        private final VisualiserPanel panel;
        private final JButton startButton;
        private final JButton stopButton;
        private final JFileChooser fileChooser;
        private final List<Observer> observers;


        public VisualiserFrame(int w, int h) {
            this.startButton = new JButton("start");
            this.stopButton = new JButton("stop");
            this.panel = new VisualiserPanel(w,h);
            this.fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            fileChooser.setCurrentDirectory(new java.io.File("."));
            fileChooser.setDialogTitle("Select a project folder");

            this.observers = new LinkedList<>();

            this.stopButton.setEnabled(false);

            setTitle("Project Analyzer");
            setSize(w,h);
            setResizable(false);

            startButton.addActionListener(this);
            stopButton.addActionListener(this);


            JPanel controlPanel = new JPanel();
            JPanel cp = new JPanel();

            controlPanel.add(startButton);
            controlPanel.add(stopButton);

            cp.setLayout(new BorderLayout());
            cp.add(BorderLayout.NORTH, new JPanel());
            cp.add(BorderLayout.CENTER, panel);
            cp.add(BorderLayout.SOUTH,controlPanel);

            addWindowListener(new WindowAdapter(){
                public void windowClosing(WindowEvent ev){
                    System.exit(0);
                }
                public void windowClosed(WindowEvent ev){
                    System.exit(0);
                }
            });
            setContentPane(cp);
        }

        public void start() {
            if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                this.setVisible(true);
            }
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            String cmd = actionEvent.getActionCommand();
            if (cmd.equals("start")){
                observers.forEach(obs -> obs.notifyStart(this.fileChooser.getSelectedFile().getAbsolutePath()));
                this.stopButton.setEnabled(true);
                this.startButton.setEnabled(false);
            } else if (cmd.equals("stop")){
                observers.forEach(Observer::notifyStop);
                this.stopButton.setEnabled(false);
                this.startButton.setEnabled(true);
            }
        }

        public void addObserver(Observer observer) {
            this.observers.add(observer);
        }
    }

    public class VisualiserPanel extends JPanel {

        private JPanel labelPanel;
        private JPanel fieldPanel;

        private JLabel packages;
        private JLabel interfaces;
        private JLabel classes;
        private JLabel methods;
        private JLabel fields;

        private JLabel numPackages;
        private JLabel numInterfaces;
        private JLabel numClasses;
        private JLabel numMethods;
        private JLabel numFields;

        public VisualiserPanel(int w, int h) {
            setSize(w, h);

            this.labelPanel = new JPanel(new GridLayout(5, 1));
            this.fieldPanel = new JPanel(new GridLayout(5, 1));

            add(labelPanel, BorderLayout.WEST);
            add(fieldPanel, BorderLayout.CENTER);

            this.packages = new JLabel("Packages: ");
            this.interfaces = new JLabel("Interfaces: ");
            this.classes = new JLabel("Classes: ");
            this.methods = new JLabel("Methods: ");
            this.fields = new JLabel("Fields: ");

            this.labelPanel.add(packages);
            this.labelPanel.add(interfaces);
            this.labelPanel.add(classes);
            this.labelPanel.add(methods);
            this.labelPanel.add(fields);


            this.numPackages = new JLabel("0");
            this.numInterfaces = new JLabel("0");
            this.numClasses = new JLabel("0");
            this.numMethods = new JLabel("0");
            this.numFields = new JLabel("0");

            this.packages.setLabelFor(this.numPackages);
            this.interfaces.setLabelFor(this.numInterfaces);
            this.classes.setLabelFor(this.numClasses);
            this.methods.setLabelFor(this.numMethods);
            this.fields.setLabelFor(this.numFields);

            this.fieldPanel.add(numPackages);
            this.fieldPanel.add(numInterfaces);
            this.fieldPanel.add(numClasses);
            this.fieldPanel.add(numMethods);
            this.fieldPanel.add(numFields);

        }

        public void incrementPackages() {
            SwingUtilities.invokeLater(() -> this.numPackages.setText(String.valueOf(Integer.parseInt(this.numPackages.getText()) +1)));

        }

        public void incrementInterfaces() {
            SwingUtilities.invokeLater(() -> this.numInterfaces.setText(String.valueOf(Integer.parseInt(this.numInterfaces.getText()) +1)));
        }

        public void incrementClasses() {
            SwingUtilities.invokeLater(() -> this.numClasses.setText(String.valueOf(Integer.parseInt(this.numClasses.getText()) +1)));
        }

        public void incrementMethods() {
            SwingUtilities.invokeLater(() -> this.numMethods.setText(String.valueOf(Integer.parseInt(this.numMethods.getText()) +1)));
        }

        public void incrementFields() {
            SwingUtilities.invokeLater(() -> this.numFields.setText(String.valueOf(Integer.parseInt(this.numFields.getText()) +1)));
        }
    }
}
