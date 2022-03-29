package org.cnolan.parser;

import java.util.Objects;

public class ValidationIssue {
    private String message;


    public ValidationIssue() {
    }

    public ValidationIssue(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof ValidationIssue)) {
            return false;
        }
        ValidationIssue validationIssue = (ValidationIssue) o;
        return Objects.equals(message, validationIssue.message);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(message);
    }

    @Override
    public String toString() {
        return "{" +
            " message='" + getMessage() + "'" +
            "}";
    }

}
