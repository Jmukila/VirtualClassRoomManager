package VirtualClassManager;

import java.util.Date; // Import statement for Date

public class Assignment {
    private String title;
    private String description;
    private Date dueDate;
    private String submissionContent;

    public Assignment(String title, String description, Date dueDate) {
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setSubmissionContent(String submissionContent) {
        this.submissionContent = submissionContent;
    }

    public String getSubmissionContent() {
        return submissionContent;
    }
}
