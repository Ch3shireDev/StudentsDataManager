package gui;
import javax.swing.*;

public class MainWindow extends JFrame  {

    static JFrame frame;
    public static void main(String[] args) {
        frame = new JFrame(LocalisationUtil.getText("window.title"));

        JPanel panel = new JPanel();

        JButton button = new JButton("click");

        // add actionlistener to button

        // add button to panel
        panel.add(button
        );

        frame.add(panel);

        // set the size of frame
        frame.setSize(400, 400);

        frame.setVisible(true);

    }
}
