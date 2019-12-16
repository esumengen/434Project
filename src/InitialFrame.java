

import communicationStrategy.Types;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class InitialFrame extends JFrame implements ActionListener {
    private final static int height = 600, width = 600;

    private JTabbedPane tabbedPane;
    private JPanel mainPanel;
    private JPanel historyPanel;
    private JPanel panel;
    private JPanel panel2;
    private JPanel intervalPanel;
    private JLabel label;
    private JComboBox comboBox;
    private JButton selectButton;
    private JLabel addressLabel;
    private JButton requestButton;
    private JLabel intervalLabel;
    private JTextField start;
    private JTextField end;
    private JFileChooser fileChooser;

    public InitialFrame() {
        initialize();


    }

    private void initialize() {
        setLocation(100, 100);
        setSize(width, height);

        setLayout(new BorderLayout());
        label = new JLabel("Request Type:");
        comboBox = new JComboBox(Types.values());
        comboBox.setSelectedIndex(-1);
        selectButton = new JButton("Select");
        selectButton.addActionListener(this);
        comboBox.addActionListener(this);

        tabbedPane = new JTabbedPane();

        createMainPanel();

        tabbedPane.addTab("Create Request", mainPanel);

        add(tabbedPane, BorderLayout.NORTH);


        //add(mainPanel, BorderLayout.NORTH);
        mainPanel.setVisible(true);
        setVisible(true);
    }

    private void createMainPanel() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(0, 1));
        //mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        panel = new JPanel();
        //panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setLayout(new GridLayout(1, 2));
        panel.add(label);
        panel.add(comboBox);
        //panel.add(selectButton);

        mainPanel.add(panel);

        fileChooser = new JFileChooser();

        panel2 = new JPanel();
        addressLabel = new JLabel("Address");
        requestButton = new JButton("Request");
        requestButton.addActionListener(this);
        intervalLabel = new JLabel("Interval");
        start = new JTextField("start");
        end = new JTextField("end");
        panel2.setLayout(new GridLayout(1, 2));
        panel2.add(addressLabel);
        panel2.add(requestButton);

        intervalPanel = new JPanel();
        intervalPanel.setLayout(new GridLayout(1, 2));
        intervalPanel.add(intervalLabel);
        JPanel textFieldPanel = new JPanel();
        textFieldPanel.add(start);
        textFieldPanel.add(end);
        intervalPanel.add(textFieldPanel);

        mainPanel.add(panel2);
        mainPanel.add(intervalPanel);
    }

    private void createHistoryPanel() {
        historyPanel = new JPanel();
    }

    public JLabel getLabel() {
        return label;
    }

    public String[] getInterval() {
        String[] interval = { start.getText(), end.getText() };

        return interval;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == requestButton) {

            panel.add(addressLabel);
            panel.updateUI();
            SecondPanel secondPanel = new SecondPanel((Types) comboBox.getSelectedItem(), this);
            tabbedPane.addTab(secondPanel.getRequestID(), secondPanel);
            tabbedPane.updateUI();
        }

        if(e.getSource() == comboBox) {
            Types type = (Types) comboBox.getSelectedItem();
            String address = "";
            switch (type) {
                case LocalVideo:
                    fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                    fileChooser.showOpenDialog(panel);
                    address = fileChooser.getSelectedFile().toString();
                    addressLabel.setText(address);
                    mainPanel.updateUI();
                case S3Video:
                    break;
                case LocalImageFolder:
                    fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                    fileChooser.showOpenDialog(panel);
                    address = fileChooser.getSelectedFile().toString();
                    addressLabel.setText(address);
                    mainPanel.updateUI();
                default:
                    break;

            }

        }
    }

}
