import communicationStrategy.CommunicationStrategy;
import communicationStrategy.CommunicationStrategyFactory;
import communicationStrategy.Strategies;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class InitialFrame extends JFrame implements ActionListener {
    private final static int height = 600, width = 600;
    private static CommunicationStrategyFactory communicationStrategyFactory = new CommunicationStrategyFactory();
    private ArrayList<CommunicationStrategy> strategies;
    private JPanel panel;
    private JPanel panel2;
    private JPanel intervalPanel;
    private JPanel mainPanel;
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
        this.strategies = new ArrayList<>();
        //strategies.add(new LocalVideoCommand(this));
        setLocation(100, 100);
        setSize(width, height);

        setLayout(new BorderLayout());
        label = new JLabel("Request Type:");
        comboBox = new JComboBox(Strategies.values());
        comboBox.setSelectedIndex(-1);
        selectButton = new JButton("Select");
        selectButton.addActionListener(this);
        comboBox.addActionListener(this);

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
        addressLabel = new JLabel("address");
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


        add(mainPanel, BorderLayout.NORTH);
        mainPanel.setVisible(true);
        setVisible(true);
    }

    public JLabel getLabel() {
        return label;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == selectButton) {

            if(comboBox.getSelectedItem() == Strategies.LocalVideo) {
                System.out.println("local video combo");

                panel.add(addressLabel);
                panel.updateUI();
            }

            System.out.println("action performed");
            communicationStrategyFactory.getCommunicationStategy((Strategies) comboBox.getSelectedItem());

        }

        if(e.getSource() == comboBox) {
            Strategies strategy = (Strategies) comboBox.getSelectedItem();
            String address = "";
            switch (strategy) {
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
