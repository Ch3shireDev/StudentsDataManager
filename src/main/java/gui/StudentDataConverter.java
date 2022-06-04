package gui;

import core.StudentData;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class StudentDataConverter {
    public static final int NO_ALUBM_INDEX = 0;
    public static final int PERSON_INDEX = 1;
    public static final int GROUP_INDEX = 2;
    public static final int COLLOQIUM1_INDEX = 3;
    public static final int COLLOQIUM2_INDEX = 4;
    public static final int EXAM_INDEX = 5;
    public static final int SUM_INDEX = 6;
    public static final int PROJECT_INDEX = 7;
    public static final int HOMEWORK_INDEX = 8;
    public static final int ACTIVITY_INDEX = 9;

    private  static final Map<Integer, Function<StudentData, Object>> fieldMapping = new HashMap<>();
    static {
        fieldMapping.put(NO_ALUBM_INDEX, StudentData::getAlbum);
        fieldMapping.put(PERSON_INDEX, StudentData::getName);
        fieldMapping.put(GROUP_INDEX, StudentData::getGroup);
        fieldMapping.put(COLLOQIUM1_INDEX, StudentData::getTest1Points);
        fieldMapping.put(COLLOQIUM2_INDEX, StudentData::getTest2Points);
        fieldMapping.put(EXAM_INDEX, StudentData::getExamPoints);
        fieldMapping.put(SUM_INDEX, StudentData::getSum);
        fieldMapping.put(PROJECT_INDEX, StudentData::getProjectPoints);
        fieldMapping.put(HOMEWORK_INDEX, StudentData::getHomeworkPoints);
        fieldMapping.put(ACTIVITY_INDEX, StudentData::getActivityPoints);
    }

    public static Object[][] convertToViewModel(Collection<StudentData> studentDataCollection){
        Object[][] x = new Object[studentDataCollection.size()][9];
        StudentData[] studentData = studentDataCollection.toArray(StudentData[]::new);

        for (int i = 0; i < x.length; i++) {
            for (int j = 0; j < 9; j++) {
                x[i][j] = fieldMapping.get(j).apply(studentData[i]);
            }
        }
        return x;
    }
}
