import communicationStrategy.CommunicationStrategy;
import communicationStrategy.CommunicationStrategyFactory;
import communicationStrategy.Strategies;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class InitialFrame extends JFrame implements ActionListener {
    private static CommunicationStrategyFactory communicationStrategyFactory = new CommunicationStrategyFactory();
    private int height, width;
    private JLabel label;
    private JComboBox comboBox;
    private JButton button;
    private JPanel panel;
    private ArrayList<CommunicationStrategy> commands;

    public InitialFrame() {
        this.commands = new ArrayList<>();
        //commands.add(new LocalVideoCommand(this));
        setName("Initial Frame");
        setLocation(100, 100);
        this.height = 600;
        this.width = 800;
        setSize(width, height);

        label = new JLabel("Label");

        comboBox = new JComboBox(Strategies.values());

        button = new JButton("Button");
        button.addActionListener(this);

        panel = new JPanel();
        //panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setLayout(new FlowLayout());
        panel.add(label);
        panel.add(comboBox);
        panel.add(button);

        add(panel);
        setVisible(true);
    }

    public JLabel getLabel() {
        return label;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == button) {
            System.out.println("action performed");
            communicationStrategyFactory.getCommunicationStategy((Strategies) comboBox.getSelectedItem());

        }
    }

}
