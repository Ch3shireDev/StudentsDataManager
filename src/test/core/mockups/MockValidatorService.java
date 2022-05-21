package mockups;

import core.IStudentDataValidator;
import core.StudentData;

/**
 * Mockowy serwis walidujący dane.
 */
public class MockValidatorService implements IStudentDataValidator {

    public boolean isUsed = false;

    @Override
    public boolean validate(StudentData studentData) {
        isUsed = true;
        return true;
    }

    @Override
    public boolean validateAlbum(String album) {
        return false;
    }

    @Override
    public boolean validateName(String name) {
        return false;
    }

    @Override
    public boolean validateGroup(String group) {
        return false;
    }

    @Override
    public boolean validateHomeworkPoints(int homeworkPoints) {
        return false;
    }

    @Override
    public boolean validateActivityPoints(int activityPoints) {
        return false;
    }

    @Override
    public boolean validateProjectPoints(int projectPoints) {
        return false;
    }

    @Override
    public boolean validateTest1Points(int test1Points) {
        return false;
    }

    @Override
    public boolean validateTest2Points(int test2Points) {
        return false;
    }

    @Override
    public boolean validateExamPoints(int examPoints) {
        return false;
    }
}
