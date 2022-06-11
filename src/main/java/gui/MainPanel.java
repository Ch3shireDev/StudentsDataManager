package gui;

import common.LocalizationUtil;
import core.*;
import gui.model.StudentDataViewTableModel;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.TableModel;
import java.awt.*;
import java.util.Locale;


/**
 *
 * */
public class MainPanel {
    private final IFilesystemService filesystemService = new FilesystemService();
    private final IStudentDataValidator validatorService = new StudentDataValidator();
    private final IStudentDataService studentDataService = new StudentDataService(validatorService);
    private JComboBox<String> langSelect;
    private JTable table1;
    private JPanel mainPanel;
    private JScrollPane scrollPane;
    private JLabel langSelectLabel;
    private JButton saveButton;
    private JButton loadbutton;
    private JLabel albumLabel;
    private JTextField albumNoTF;
    private JLabel personLabel;
    private JTextField personTF;
    private JLabel groupLabel;
    private JTextField groupTF;
    private JButton addStudentbtn;
    private JPanel langSelectPanel;
    private JPanel actionButtonsPanel;
    private JPanel studentFormPanel;

    public MainPanel(JFrame frame) {

        try {
            setupUi();
        } catch (Exception e) {
            showError(frame, e);
        }
        TableModel tableModel = null;
        try {
            tableModel = new StudentDataViewTableModel(StudentDataConverter.convertToViewModelData(studentDataService.getAll()), studentDataService);
        } catch (Exception e) {
            showError(frame, e);
        }
        table1.getTableHeader().setReorderingAllowed(false);

        langSelect.addActionListener(event -> {
            String lang = langSelect.getItemAt(langSelect.getSelectedIndex());
            LocalizationUtil.setLocale(Locale.forLanguageTag(lang.toLowerCase(Locale.ROOT)));
            try {
                table1.setModel(new StudentDataViewTableModel(StudentDataConverter.convertToViewModelData(studentDataService.getAll()), studentDataService));
            } catch (Exception e) {
                showError(frame, e);
            }
            langSelectLabel.setText(LocalizationUtil.getText("langSelectLabel"));
            loadbutton.setText(LocalizationUtil.getText("loadButton"));
            saveButton.setText(LocalizationUtil.getText("saveButton"));
        });
        loadbutton.addActionListener(event -> {
            JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

            // invoke the showsSaveDialog function to show the save dialog
            int ACTION_RESULT = fileChooser.showSaveDialog(null);

            // if the user selects a file
            if (ACTION_RESULT == JFileChooser.APPROVE_OPTION) {
                // set the label to the path of the selected file
                String filename = fileChooser.getSelectedFile().getAbsolutePath();
                IStudentDataPersistentStorageService storageService = new StudentDataPersistentStorageService(filename, filesystemService);
                try {
                    studentDataService.load(storageService);
                    table1.setModel(new StudentDataViewTableModel(StudentDataConverter.convertToViewModelData(studentDataService.getAll()), studentDataService));
                } catch (Exception e) {
                    showError(frame, e);
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
                } catch (Exception e) {
                    showError(frame, e);
                }
            }
        });
        if (tableModel != null)
            table1.setModel(tableModel);

        addStudentbtn.addActionListener(event -> {
            String album = albumNoTF.getText();
            String person = personTF.getText();
            String group = groupTF.getText();

            StudentData studentData = new StudentData(album, person, group);
            try {
                studentDataService.add(studentData);
                table1.setModel(new StudentDataViewTableModel(StudentDataConverter.convertToViewModelData(studentDataService.getAll()), studentDataService));
            } catch (Exception e) {
                showError(frame, e);
            }
        });
    }

    private void showError(JFrame frame, Exception e) {
        JOptionPane.showMessageDialog(frame, e.getMessage(), "Błąd!", 0);
    }

    private void setupUi() throws Exception {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setMinimumSize(new Dimension(1200, 1000));
        initActionButtonsPanel();
        initLanguageSelectPanel();
        initNewStudentFormPanel();
        initTable();

        mainPanel.add(actionButtonsPanel);
        mainPanel.add(langSelectPanel);

        JPanel tableAndFormPanel = new JPanel();
        tableAndFormPanel.setLayout(new BoxLayout(tableAndFormPanel, BoxLayout.Y_AXIS));
        tableAndFormPanel.add(studentFormPanel);
        tableAndFormPanel.add(scrollPane);
        mainPanel.add(tableAndFormPanel);
    }

    /**
     * Inicjalizacja panelu zawierającego scrollowaną tabelę z danhymi studentów
     */
    private void initTable() throws Exception {
        TableModel tableModel = new StudentDataViewTableModel(StudentDataConverter.convertToViewModelData(studentDataService.getAll()), studentDataService);

        table1 = new JTable(tableModel);
        table1.setAutoCreateRowSorter(true);
        scrollPane = new JScrollPane(table1);
        scrollPane.setEnabled(true);
    }

    /**
     * Inicjalizacja panelu zawierającego przycisk typu ComboBox do zmiany języka aplikacji
     */
    private void initLanguageSelectPanel() {
        langSelectPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        langSelect = new JComboBox<>(new String[]{
                Locale.forLanguageTag("pl-PL").getLanguage(),
                Locale.forLanguageTag("en-EN").getLanguage(),
        });
        langSelect.setSelectedIndex(Locale.getDefault().equals(Locale.ENGLISH) ? 1 : 0);
        langSelectLabel = new JLabel(LocalizationUtil.getText("langSelectLabel"));
        langSelectPanel.add(langSelectLabel);
        langSelectPanel.add(langSelect);
    }

    /**
     * Inicjalizacja panelu zawierającego przyciski do załadowania pliku z danymi i eksportu danych
     */
    private void initActionButtonsPanel() {
        actionButtonsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        loadbutton = new JButton(LocalizationUtil.getText("loadButton"));
        saveButton = new JButton(LocalizationUtil.getText("saveButton"));
        actionButtonsPanel.add(loadbutton);
        actionButtonsPanel.add(saveButton);
    }

    /**
     * Inicjalizacja panelu zawierającego prosty formularz z kontolkami do wprowadzenia nowego studenta
     */
    private void initNewStudentFormPanel() {
        studentFormPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        addStudentbtn = new JButton(LocalizationUtil.getText("addStudentBtn"));

        albumLabel = new JLabel(LocalizationUtil.getText("studentTable.header.noAlbum"));
        albumNoTF = new JTextField(10);
        groupLabel = new JLabel(LocalizationUtil.getText("studentTable.header.group"));
        groupTF = new JTextField(10);
        personLabel = new JLabel(LocalizationUtil.getText("studentTable.header.person"));
        personTF = new JTextField(10);
        studentFormPanel.add(albumLabel);
        studentFormPanel.add(albumNoTF);
        studentFormPanel.add(personLabel);
        studentFormPanel.add(personTF);
        studentFormPanel.add(groupLabel);
        studentFormPanel.add(groupTF);
        studentFormPanel.add(addStudentbtn);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Student Data Manager");
        MainPanel mainPanel1 = new MainPanel(frame);
        frame.setContentPane(mainPanel1.mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
