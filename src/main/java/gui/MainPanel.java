package gui;

import core.*;
import gui.mock.MockStudentDataService;
import gui.model.StudentDataViewModel;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.TableModel;
import java.awt.*;
import java.util.Locale;

public class MainPanel {
    private JComboBox<String> langSelect;
    private JTable table1;
    private JPanel mainPanel;
    private JScrollPane scrollPane;
    private JLabel langSelectLabel;
    private JButton saveButton;
    private JButton loadbutton;
    private JButton addNewStudentButton;
    private MainPanel mainPanel1;
    private final IStudentDataService studentDataService = new MockStudentDataService(new StudentDataPersistentStorageService(new FilesystemService()));

    private final IStudentDataPersistentStorageService storageService = new StudentDataPersistentStorageService(new FilesystemService());

    public MainPanel(JFrame frame) {
        Object[][] data = getData();
        TableModel tableModel = new StudentDataViewModel(data, studentDataService);
        langSelect.addItem("PL");
        langSelect.addItem("EN");
        langSelect.setSelectedIndex(1);

        langSelectLabel.setText(LocalisationUtil.getText("langSelectLabel"));
        langSelect.addActionListener(e -> {
            String lang = langSelect.getItemAt(langSelect.getSelectedIndex());
            LocalisationUtil.setLocale(Locale.forLanguageTag(lang.toLowerCase(Locale.ROOT)));
            table1.setModel(new StudentDataViewModel(data, studentDataService));
            langSelectLabel.setText(LocalisationUtil.getText("langSelectLabel"));
            loadbutton.setText(LocalisationUtil.getText("loadButton"));
            saveButton.setText(LocalisationUtil.getText("saveButton"));
        });
        loadbutton.addActionListener(event -> {
            JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

            // invoke the showsSaveDialog function to show the save dialog
            int ACTION_RESULT = fileChooser.showSaveDialog(null);

            // if the user selects a file
            if (ACTION_RESULT == JFileChooser.APPROVE_OPTION) {
                // set the label to the path of the selected file
                String filename = fileChooser.getSelectedFile().getAbsolutePath();
                try {
                    var x = storageService.load(filename);
                    studentDataService.clear();
                    for (StudentData studentData : x) {
                        studentDataService.add(studentData);
                    }

                    table1.setModel(new StudentDataViewModel(getData(), studentDataService));
                } catch (Exception e) {
                    //
                }
            }
        });

        addNewStudentButton.addActionListener(event -> {
            JDialog jDialog = new JDialog(frame, "title", true);
            var x = new JPanel();
            x.add(new JLabel("Test"));
            x.add(new JTextField());

            x.setMinimumSize(new Dimension(500, 500));
            jDialog.setContentPane(x);
            jDialog.setVisible(true);
        });

        saveButton.addActionListener(event -> {
            JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

            // invoke the showsSaveDialog function to show the save dialog
            int ACTION_RESULT = fileChooser.showSaveDialog(null);

            // if the user selects a file
            if (ACTION_RESULT == JFileChooser.APPROVE_OPTION) {
                // set the label to the path of the selected file
                String filename = fileChooser.getSelectedFile().getAbsolutePath();
                try {
                    studentDataService.save(filename);
                } catch (Exception e) {
                    //
                }
            }
        });
        table1.setModel(tableModel);
    }

    private Object[][] getData() {
        Object[][] data;
        try {
            data = StudentDataConverter.convertToViewModel(studentDataService.getAll());
            return data;
        } catch (Exception e) {
            //show error dialog
            return new Object[0][0];
        }
    }

    public static void main(String[] args) {

        JFrame frame = new JFrame("MainPanel");
        MainPanel mainPanel1 = new MainPanel(frame);
        frame.setContentPane(mainPanel1.mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

}
