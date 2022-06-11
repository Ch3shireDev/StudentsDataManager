package gui.model;

import common.LocalizationUtil;
import core.IStudentDataService;
import core.StudentData;
import gui.StudentDataConverter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

import static gui.StudentDataConverter.*;

/**
 * Model danych wyświetlany w tabeli na interfejsie użytkownika
 *
 * @author Karol Ziąbski
 */
public class StudentDataViewTableModel extends DefaultTableModel {

    /**
     * Mapowanie kolumn i setterów aby ułatwić edytowanie danych w kolumnach
     */
    private static final Map<Integer, BiConsumer<StudentData, Object>> fieldMapping = new HashMap<>();

    static {
        fieldMapping.put(GROUP_INDEX, (studentData, group) -> studentData.setGroup(group.toString()));
        fieldMapping.put(COLLOQIUM1_INDEX, (studentData, points) -> studentData.setTest1Points(Integer.parseInt(points.toString())));
        fieldMapping.put(COLLOQIUM2_INDEX, (studentData, points) -> studentData.setTest2Points(Integer.parseInt(points.toString())));
        fieldMapping.put(EXAM_INDEX, (studentData, points) -> studentData.setExamPoints(Integer.parseInt(points.toString())));
        fieldMapping.put(PROJECT_INDEX, (studentData, points) -> studentData.setProjectPoints(Integer.parseInt(points.toString())));
        fieldMapping.put(HOMEWORK_INDEX, (studentData, points) -> studentData.setHomeworkPoints(Integer.parseInt(points.toString())));
        fieldMapping.put(ACTIVITY_INDEX, (studentData, activityPoint) -> studentData.setActivityPoints(Integer.parseInt(activityPoint.toString())));
    }

    /**
     * Wspólny prefix kluczy z ResourceBundle - nagłówków kolumn
     */
    private static final String headersLocalisationsPrefix = "studentTable.header";

    /**
     * Klucze nagłówków kolumn
     */
    private static final String[] headersKeys = {
            "noAlbum",
            "person",
            "group",
            "homework",
            "activity",
            "project",
            "kol1",
            "kol2",
            "exam",
            "sum"
    };

    /**
     * Serwis {@link IStudentDataService}
     */
    private final IStudentDataService service;

    /**
     * Bazowe okno aplikacji aby wyswietlić błąd podczas aktualizacji danych
     */
    private final JFrame frame;

    /**
     * @param frame   Bazowe okno z którego wywoływany jest model.
     * @param data    Dane studenta - do stworzenia tego obiektu można użyć {@link StudentDataConverter}
     * @param service Serwis
     */
    public StudentDataViewTableModel(JFrame frame, Object[][] data, IStudentDataService service) {
        super(data, initHeaders());
        this.service = service;
        this.frame = frame;
    }

    /**
     * Inicjalizacja nagłówków tabeli wykorzystująca tłumaczenia.
     *
     * @return Tablica nagłówkow przetłumaczona na aktualny {@link java.util.Locale} który jest w klasie {@link LocalizationUtil}
     */
    private static String[] initHeaders() {
        List<String> headers = new LinkedList<>();
        for (String headersKey : headersKeys) {
            headers.add(LocalizationUtil.getText(headersLocalisationsPrefix + "." + headersKey));
        }
        return headers.toArray(String[]::new);
    }

    /**
     * Nadpisanie bazowej metody celem zablokowania edycji kolumn: album, grupa, imię i nazwisko
     *
     * @param row    - wiersz w tabeli
     * @param column - kolumna w tabeli
     * @return - zablokowanie kolumny
     */
    @Override
    public boolean isCellEditable(int row, int column) {
        return column > 2;
    }

    /**
     * Metoda wykonujaca się na aktualizację danych w komórce tabeli
     *
     * @param aValue nowa wartość które będzoe wpipsywana do komórki
     * @param row    - wiersz w którym zaszła aktualizacja
     * @param column - kolumna w której zaszła aktualizacja
     */
    @Override
    public void setValueAt(Object aValue, int row, int column) {
        String number = getValueAt(row, NO_ALUBM_INDEX).toString();
        try {
            StudentData data = service.get(number);
            StudentData newData = new StudentData(data);
            fieldMapping.get(column).accept(newData, aValue);
            service.update(newData);
            super.setValueAt(aValue, row, column);
            setDataVector(StudentDataConverter.convertToViewModelData(service.getAll()), initHeaders());
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(frame, e.getMessage(), LocalizationUtil.getText("window.error"), JOptionPane.ERROR_MESSAGE);
        }
    }
}
