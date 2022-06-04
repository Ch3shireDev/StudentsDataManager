package gui.model;

public class SubjectPoint {
    private String name;
    private Object value;

    public SubjectPoint(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }
}
