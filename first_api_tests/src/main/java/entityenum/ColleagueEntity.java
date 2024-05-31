package entityenum;

/**
 * Enum representing various attributes of a Colleague entity.
 */

public enum ColleagueEntity {
    NAME ("Wizard"),
    JOB ("does something incredibly important");

    private final String fieldName;
    ColleagueEntity(String fieldName) {
        this.fieldName = fieldName;
    }
    public String getFieldName() {
        return fieldName;
    }
}
