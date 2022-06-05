package gui;

import core.*;
import gui.model.StudentDataViewTableModel;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.TableModel;
import java.util.Locale;

public class MainPanel {
    private final IFilesystemService filesystemService = new FilesystemService();
    private final IStudentDataValidator validatorService = new StudentDataValidator();
    private final IStudentDataService studentDataService = new StudentDataService(validatorService);
    private final IStudentDataPersistentStorageService storageService = new StudentDataPersistentStorageService("a.json", filesystemService);
    private JComboBox<String> langSelect;
    private JTable table1;
    private JPanel mainPanel;
    private JScrollPane scrollPane;
    private JLabel langSelectLabel;
    private JButton saveButton;
    private JButton loadbutton;
    private JButton addNewStudentButton;
    private JTextField albumNoTF;
    private JTextField personTF;
    private JTextField groupTF;
    private JButton addStudentbtn;
    private MainPanel mainPanel1;

    public MainPanel(JFrame frame) {
        Object[][] data = getData();
        table1.getTableHeader().setReorderingAllowed(false);
        TableModel tableModel = new StudentDataViewTableModel(data, studentDataService);
        langSelect.addItem("PL");
        langSelect.addItem("EN");
        langSelect.setSelectedIndex(1);

        langSelectLabel.setText(LocalisationUtil.getText("langSelectLabel"));
        langSelect.addActionListener(e -> {
            String lang = langSelect.getItemAt(langSelect.getSelectedIndex());
            LocalisationUtil.setLocale(Locale.forLanguageTag(lang.toLowerCase(Locale.ROOT)));
            table1.setModel(new StudentDataViewTableModel(data, studentDataService));
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
                IStudentDataPersistentStorageService storageService = new StudentDataPersistentStorageService("filename", filesystemService);
                try {
                    studentDataService.load(storageService);
                    table1.setModel(new StudentDataViewTableModel(getData(), studentDataService));
                }
                catch (Exception e) {
                    //
                }
            }
        });

        saveButton.addActionListener(event -> {
            JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

            // invoke the showsSaveDialog function to show the save dialog
            int ACTION_RESULT = fileChooser.showSaveDialog(null);

            // if the user selects a file
            if (ACTION_RESULT == JFileChooser.APPROVE_OPTION) {
                // set the label to the path of the selected file
                String filename = fileChooser.getSelectedFile().getAbsolutePath();
                IStudentDataPersistentStorageService storageService = new StudentDataPersistentStorageService(filename, filesystemService);
                try {
                    studentDataService.save(storageService);
                }
                catch (Exception e) {
                    //
                }
            }
        });
        table1.setModel(tableModel);

        addStudentbtn.addActionListener(event -> {
            String album = albumNoTF.getText();
            String person = personTF.getText();
            String group = groupTF.getText();

            StudentData studentData = new StudentData(album, person, group);
            try {
                studentDataService.add(studentData);
                table1.setModel(new StudentDataViewTableModel(getData(), studentDataService));
            }
            catch (Exception e) {
                JOptionPane.showMessageDialog(frame, "Błąd podczas dodawania nowego studenta!", "Błąd!", 0);
            }
        });
    }

    public static void main(String[] args) {

        JFrame frame = new JFrame("MainPanel");
        MainPanel mainPanel1 = new MainPanel(frame);
        frame.setContentPane(mainPanel1.mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private Object[][] getData() {
        Object[][] data;
        try {
            data = StudentDataConverter.convertToViewModel(studentDataService.getAll());
            return data;
        }
        catch (Exception e) {
            //show error dialog
            return new Object[0][0];
        }
    }

}
