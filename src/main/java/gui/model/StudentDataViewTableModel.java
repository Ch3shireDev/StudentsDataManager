package gui.model;

import core.IStudentDataService;
import core.StudentData;
import common.LocalizationUtil;
import gui.StudentDataConverter;

import javax.swing.table.DefaultTableModel;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

import static gui.StudentDataConverter.*;
/**
 * Model danych wyświetlany w tabeli na interfejsie użytkownika
 * */
public class StudentDataViewTableModel extends DefaultTableModel {

    /**
     * Mapowanie kolumn i setterów aby ułatwić edytowanie danych w kolumnach
     * */
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

    private static final String headersLocalisationsPrefix = "studentTable.header";
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
    private IStudentDataService service;

    /**
     * TODO: Dodać opis.
     * @param data
     * @param service
     */
    public StudentDataViewTableModel(Object[][] data, IStudentDataService service) {
        super(data, initHeaders());
        this.service = service;
    }

    /**
     * TODO: Dodać opis.
     * @return
     */
    private static String[] initHeaders() {
        List<String> headers = new LinkedList<>();
        for (int i = 0; i < headersKeys.length; i++) {
            headers.add(LocalizationUtil.getText(headersLocalisationsPrefix + "." + headersKeys[i]));
        }
        return headers.toArray(String[]::new);
    }

    /**
     * TODO: Dodać opis.
     * @param row
     * @param column
     * @return
     */
    @Override
    public boolean isCellEditable(int row, int column) {
        return column > 2;
    }

    /**
     * TODO: Dodać opis.
     * @param row
     * @param column
     */
    @Override
    public void fireTableCellUpdated(int row, int column) {
        String number = getValueAt(row, NO_ALUBM_INDEX).toString();
        String newvalue = getValueAt(row, column).toString();

        try {
            StudentData data = service.get(number);
            fieldMapping.get(column).accept(data, newvalue);
            service.update(data);

            setDataVector(StudentDataConverter.convertToViewModelData(service.getAll()), initHeaders());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        super.fireTableCellUpdated(row, column);
    }
    }
