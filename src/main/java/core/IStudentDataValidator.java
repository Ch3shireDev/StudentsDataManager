package core;

public interface IStudentDataValidator {
    boolean validate(StudentData studentData);

    boolean validateAlbum(String album);

    boolean validateName(String name);

    boolean validateGroup(String group);

    boolean validateHomeworkPoints(int homeworkPoints);

    boolean validateActivityPoints(int activityPoints);

    boolean validateProjectPoints(int projectPoints);

    boolean validateTest1Points(int test1Points);

    boolean validateTest2Points(int test2Points);

    boolean validateExamPoints(int examPoints);
}
