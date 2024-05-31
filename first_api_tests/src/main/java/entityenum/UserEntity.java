package entityenum;
import static utils.EmailGenerator.generateRandomEmail;

/**
 * Enum representing various attributes of a User entity.
 */

public enum UserEntity {
    EMAIL(generateRandomEmail()),
    PASSWORD( "Qwe12rt3");
    private final String fieldName;
    UserEntity(String fieldName) {
        this.fieldName = fieldName;
    }
    public String getFieldName() {
        return fieldName;
    }
}
