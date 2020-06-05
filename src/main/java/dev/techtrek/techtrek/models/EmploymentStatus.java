package dev.techtrek.techtrek.models;

public enum EmploymentStatus {
    HIRED("Hired"),
    AVAILABLE("Available for hire");

    private final String displayValue;

    private EmploymentStatus(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}