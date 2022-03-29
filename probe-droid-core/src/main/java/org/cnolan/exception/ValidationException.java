package org.cnolan.exception;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.cnolan.parser.ValidationIssue;

public class ValidationException extends RuntimeException {
    private List<ValidationIssue> issues = new ArrayList<>();


    public ValidationException() {
    }

    public ValidationException(List<ValidationIssue> issues) {
        this.issues = issues;
    }

    public List<ValidationIssue> getIssues() {
        return this.issues;
    }

    public void setIssues(List<ValidationIssue> issues) {
        this.issues = issues;
    }

    public ValidationException issues(List<ValidationIssue> issues) {
        setIssues(issues);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof ValidationException)) {
            return false;
        }
        ValidationException validationException = (ValidationException) o;
        return Objects.equals(issues, validationException.issues);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(issues);
    }

    @Override
    public String toString() {
        return "{" +
            " issues='" + getIssues() + "'" +
            "}";
    }

}
