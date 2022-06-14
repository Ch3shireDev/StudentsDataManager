package gui;

import common.LocalizationUtil;
import core.*;
import gui.model.StudentDataViewTableModel;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Locale;


/**
 * Główna klasa startowa uruchamiająca aplikację okienkową.
 *
 * @author Karol Ziąbski
 */
public class MainPanel {
    /**
     * Serwis obsługujący system plików.
     */
    private final IFilesystemService filesystemService = new FilesystemService();
    /**
     * Serwis obsługujący walidację danych studentów.
     */
    private final IStudentDataValidator validatorService = new StudentDataValidator();

    /**
     * Serwis obsługujący dane studentów.
     */
    private final IStudentDataService studentDataService = new StudentDataService(validatorService);

    /**
     * Okno nadrzędne bieżącej ramki.
     */
    private final JFrame parentFrame;
    /**
     * Formatka obsługująca wybór języka.
     */
    private JComboBox<String> langSelect;
    /**
     * Tabela danych studentów.
     */
    private JTable table;
    /**
     * Główny panel, na którym znajdują się wszystkie komponenty.
     */
    private JPanel mainPanel;
    /**
     * Przewijany panel, na którym zaczepiona jest tablica z danymi studentów.
     */
    private JScrollPane scrollPane;
    /**
     * Podpis selekcji języków.
     */
    private JLabel langSelectLabel;
    /**
     * Przycisk zapisu danych do pliku.
     */
    private JButton saveButton;
    /**
     * Przycisk odczytu danych z pliku.
     */
    private JButton loadButton;
    /**
     * Etykieta panelu albumu nowego studenta.
     */
    private JLabel albumLabel;
    /**
     * Formatka wpisywania danych albumu nowego studenta.
     */
    private JTextField albumNoTF;
    /**
     * Etykieta panelu imienia i nazwiska nowego studenta.
     */
    private JLabel personLabel;
    /**
     * Formatka wpisywania imienia i nazwiska nowego studenta.
     */
    private JTextField personTF;
    /**
     * Etykieta panelu grupy nowego studenta.
     */
    private JLabel groupLabel;
    /**
     * Formatka wpisywania grupy nowego studenta.
     */
    private JTextField groupTF;
    /**
     * Przycisk dodawania nowego studenta.
     */
    private JButton addStudentBtn;
    /**
     * Panel formularza danych nowego studenta.
     */
    private JPanel studentFormPanel;

    /**
     * Konstruktor głównej klasy inicjalizujący kontrolki.
     *
     * @param frame - okno bazowe.
     */
    public MainPanel(JFrame frame) {
        this.parentFrame = frame;

        try {

            frame.setMinimumSize(new Dimension(800, 600));
            mainPanel = getMainPanel();
            /**
             * Panel przycisków odczytu i zapisu danych.
             */
            JPanel actionButtonsPanel = getActionButtonsPanel();
            /**
             * Panel selekcji języka.
             */
            JPanel langSelectPanel = getLanguageSelectPanel();
            studentFormPanel = getNewStudentFormPanel();

            table = new JTable(getTableModel());
            table.setAutoCreateRowSorter(true);
            scrollPane = new JScrollPane(table);
            scrollPane.setEnabled(true);

            mainPanel.add(actionButtonsPanel);
            mainPanel.add(langSelectPanel);
            mainPanel.add(getTableAndFormPanel());

            table.setModel(getTableModel());
            table.getTableHeader().setReorderingAllowed(false);
            langSelect.addActionListener(this::onLangSelect);
            loadButton.addActionListener(this::onLoadButtonClick);
            saveButton.addActionListener(this::onSaveButtonClick);
            addStudentBtn.addActionListener(this::onAddButtonClick);
        }
        catch (Exception e) {
            showError(frame, e);
        }
    }

    /**
     * Metoda uruchamiająca działania aplikacji.
     *
     * @param args Parametry startowe (opcjonalne)
     */
    public static void main(String[] args) {
        JFrame frame = new JFrame("Student Data Manager");
        MainPanel mainPanel1 = new MainPanel(frame);
        frame.setContentPane(mainPanel1.mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Zdarzenie wywoływane przy przełączeniu języka.
     *
     * @param event Zdarzenie.
     */
    private void onLangSelect(ActionEvent event) {
        try {
            String lang = langSelect.getItemAt(langSelect.getSelectedIndex());
            LocalizationUtil.setLocale(Locale.forLanguageTag(lang.toLowerCase(Locale.ROOT)));
            table.setModel(getTableModel());
            langSelectLabel.setText(LocalizationUtil.getText("langSelectLabel"));
            loadButton.setText(LocalizationUtil.getText("loadButton"));
            saveButton.setText(LocalizationUtil.getText("saveButton"));
            albumLabel.setText(LocalizationUtil.getText("studentTable.header.noAlbum"));
            personLabel.setText(LocalizationUtil.getText("studentTable.header.person"));
            groupLabel.setText(LocalizationUtil.getText("studentTable.header.group"));
            addStudentBtn.setText(LocalizationUtil.getText("addStudentBtn"));
        }
        catch (Exception e) {
            showError(parentFrame, e);
        }
    }

    /**
     * Zdarzenie wywoływane przy wciśnięciu przycisku LoadButton.
     *
     * @param event Zdarzenie.
     */
    private void onLoadButtonClick(ActionEvent event) {
        try {
            JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

            // invoke the showsSaveDialog function to show the save dialog
            int ACTION_RESULT = fileChooser.showSaveDialog(null);

            if (ACTION_RESULT != JFileChooser.APPROVE_OPTION) return;
            String filename = getFileName(fileChooser);
            IStudentDataPersistentStorageService storageService = new StudentDataPersistentStorageService(filename, filesystemService);
            studentDataService.load(storageService);
            table.setModel(getTableModel());

        }
        catch (Exception e) {
            showError(parentFrame, e);
        }

    }

    /**
     * Zdarzenie wywoływane w przypadku kliknięcia SaveButton.
     *
     * @param event Zdarzenie.
     */
    private void onSaveButtonClick(ActionEvent event) {
        try {
            JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

            // invoke the showsSaveDialog function to show the save dialog
            int ACTION_RESULT = fileChooser.showSaveDialog(null);

            if (ACTION_RESULT != JFileChooser.APPROVE_OPTION) return;

            // set the label to the path of the selected file
            String filename = getFileName(fileChooser);

            IStudentDataPersistentStorageService storageService = new StudentDataPersistentStorageService(filename, filesystemService);
            studentDataService.save(storageService);

        }
        catch (Exception e) {
            showError(parentFrame, e);
        }
    }

    /**
     * Pobiera nazwę pliku z okna dialogowego fileChooser.
     *
     * @param fileChooser Klasa obsługująca okno dialogowe.
     * @return Nazwa pliku z rozszerzeniem .json.
     */
    private String getFileName(JFileChooser fileChooser) {
        String filename = fileChooser.getSelectedFile().getAbsolutePath();
        if (filename.endsWith(".json")) return filename;
        return filename.concat(".json");
    }

    /**
     * Zdarzenie wywoływane w przypadku wciśnięcia przycisku AddButton.
     *
     * @param event Zdarzenie.
     */
    private void onAddButtonClick(ActionEvent event) {
        try {
            String album = albumNoTF.getText();
            String person = personTF.getText();
            String group = groupTF.getText();
            if (isInputDataBlank(album, person, group)) return;
            StudentData studentData = new StudentData(album, person, group);
            studentDataService.add(studentData);
            table.setModel(getTableModel());
            clearInputData();
        }
        catch (Exception e) {
            showError(parentFrame, e);
        }

    }

    /**
     * Czyści wejściowe dane studenta.
     */
    private void clearInputData() {
        albumNoTF.setText("");
        personTF.setText("");
        groupTF.setText("");
    }

    /**
     * Sprawdza, czy wejściowe dane studenta są całkowicie puste.
     *
     * @param album  Numer albumu studenta.
     * @param person Imię i nazwisko studenta.
     * @param group  Numer grupy zajęciowej studenta.
     * @return Prawda, jeśli dane są puste, fałsz w przeciwnym wypadku.
     */
    private boolean isInputDataBlank(String album, String person, String group) {
        return album.isBlank() && person.isBlank() && group.isBlank();
    }

    /**
     * Metoda zwracająca model danych studenta.
     *
     * @return Model danych studenta.
     * @throws Exception Wyjątek w przypadku błędu komunikacji z serwisem danych studenta bądź przygotowywaniem widoku danych.
     */
    private StudentDataViewTableModel getTableModel() throws Exception {
        return new StudentDataViewTableModel(parentFrame, getData(), studentDataService);
    }

    /**
     * Metoda pomocnicza dla klasy okna. Zwraca dane studentów w postaci dwuwymiarowej tablicy dostosowanej do JTable.
     *
     * @return Dane studentów.
     * @throws Exception Wyjątek w przypadku błędu operacji.
     */
    Object[][] getData() throws Exception {
        return StudentDataConverter.convertToViewModelData(studentDataService.getAll());
    }

    /**
     * Metoda wyświetlająca okno typu dialog z informacją o błędzie.
     *
     * @param frame     - bazowe okno, względem którego wyświetla się komunikat o błędzie.
     * @param exception - Wyjątek, który został zgłoszony przez aplikację.
     */
    private void showError(JFrame frame, Exception exception) {
        JOptionPane.showMessageDialog(frame, exception.getMessage(), LocalizationUtil.getText("window.error"), JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Funkcja zwracająca panel z tabelą.
     * @return Panel z tabelą.
     */
    private JPanel getTableAndFormPanel() {
        JPanel tableAndFormPanel = new JPanel();
        tableAndFormPanel.setLayout(new BoxLayout(tableAndFormPanel, BoxLayout.Y_AXIS));
        tableAndFormPanel.add(studentFormPanel);
        tableAndFormPanel.add(scrollPane);
        return tableAndFormPanel;
    }

    /**
     * Funkcja zwracająca główny panel.
     * @return Główny panel.
     */
    private JPanel getMainPanel() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setMinimumSize(new Dimension(1400, 1200));
        mainPanel.setMaximumSize(getMaximumHeightSize());
        return mainPanel;
    }

    /**
     * Inicjalizacja panelu zawierającego przycisk typu ComboBox do zmiany języka aplikacji.
     */
    private JPanel getLanguageSelectPanel() {
        JPanel langSelectPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        langSelect = new JComboBox<>(new String[]{
                Locale.forLanguageTag("pl-PL").getLanguage(),
                Locale.forLanguageTag("en-EN").getLanguage(),
        });
        langSelect.setSelectedIndex(Locale.getDefault().equals(Locale.forLanguageTag("pl-PL")) ? 0 : 1);
        langSelectLabel = new JLabel(LocalizationUtil.getText("langSelectLabel"));
        langSelectPanel.add(langSelectLabel);
        langSelectPanel.add(langSelect);
        langSelectPanel.setMaximumSize(getMaximumHeightSize());
        return langSelectPanel;
    }

    /**
     * Inicjalizacja panelu zawierającego przyciski do załadowania pliku z danymi i eksportu danych.
     * @return Panel z przyciskami do zapisu i odczytu danych.
     */
    private JPanel getActionButtonsPanel() {
        JPanel actionButtonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        loadButton = new JButton(LocalizationUtil.getText("loadButton"));
        saveButton = new JButton(LocalizationUtil.getText("saveButton"));
        actionButtonsPanel.add(loadButton);
        actionButtonsPanel.add(saveButton);
        actionButtonsPanel.setMaximumSize(getMaximumHeightSize());
        return actionButtonsPanel;
    }

    /**
     * Inicjalizacja panelu zawierającego prosty formularz z kontrolkami do wprowadzenia nowego studenta.
     * @return Panel z formatką dodawania nowego studenta.
     */
    private JPanel getNewStudentFormPanel() {

        JPanel studentFormPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        studentFormPanel.setMaximumSize(getMaximumHeightSize());

        albumLabel = new JLabel(LocalizationUtil.getText("studentTable.header.noAlbum"));
        albumLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        albumNoTF = new JTextField(10);
        groupLabel = new JLabel(LocalizationUtil.getText("studentTable.header.group"));
        groupTF = new JTextField(10);
        groupLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        personLabel = new JLabel(LocalizationUtil.getText("studentTable.header.person"));
        personTF = new JTextField(10);
        personLabel.setHorizontalAlignment(SwingConstants.RIGHT);


        addStudentBtn = new JButton(LocalizationUtil.getText("addStudentBtn"));

        studentFormPanel.add(albumLabel);
        studentFormPanel.add(albumNoTF);
        studentFormPanel.add(personLabel);
        studentFormPanel.add(personTF);
        studentFormPanel.add(groupLabel);
        studentFormPanel.add(groupTF);
        studentFormPanel.add(addStudentBtn);

        return  studentFormPanel;
    }

    /**
     * Zwraca wymiary panelu o dowolnej szerokości i maksymalnej wysokości pojedynczego wiersza.
     * @return Wymiary jednolinijkowego panelu.
     */
    private Dimension getMaximumHeightSize() {
        int maxLineHeight = 40;
        return new Dimension(Integer.MAX_VALUE, maxLineHeight);
    }
}
