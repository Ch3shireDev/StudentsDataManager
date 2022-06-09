package core.mockups;

import core.IStudentDataValidator;
import core.StudentData;

import java.util.Collection;

/**
 * Mockowy serwis walidujący dane.
 */
public class MockValidatorService implements IStudentDataValidator {

    public boolean isUsed = false;

    @Override
    public Collection<String> getMessages(StudentData studentData) {
        return null;
    }

    @Override
    public boolean validate(StudentData studentData) {
        isUsed = true;
        return true;
    }

    @Override
    public boolean validateAlbum(String album) {
        return true;
    }

    @Override
    public boolean validateName(String name) {
        return true;
    }

    @Override
    public boolean validateGroup(String group) {
        return true;
    }

    @Override
    public boolean validateHomeworkPoints(int homeworkPoints) {
        return true;
    }

    @Override
    public boolean validateActivityPoints(int activityPoints) {
        return true;
    }

    @Override
    public boolean validateProjectPoints(int projectPoints) {
        return true;
    }

    @Override
    public boolean validateTest1Points(int test1Points) {
        return true;
    }

    @Override
    public boolean validateTest2Points(int test2Points) {
        return true;
    }

    @Override
    public boolean validateExamPoints(int examPoints) {
        return true;
    }
}
