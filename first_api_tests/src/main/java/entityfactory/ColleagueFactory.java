package entityfactory;

import entity.Colleague;
import entityenum.ColleagueEntity;

/**
 * Factory class for creating instances of {@link Colleague}.
 * This class provides a method to create a {@link Colleague} object
 * with predefined name and job values obtained from the {@link ColleagueEntity} enum.
 */

public class ColleagueFactory {
    public static Colleague createColleague(){
        return new Colleague(ColleagueEntity.NAME.getFieldName(),
                ColleagueEntity.JOB.getFieldName());
    }
}
