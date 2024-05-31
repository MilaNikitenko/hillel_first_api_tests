package entity;

/**
 * Record class representing a Colleague.
 * This class is a simple immutable data holder for a colleague with a name and job.
 * @param name the name of the colleague
 * @param job  the job of the colleague
 */

public record Colleague (String name, String job) { }
