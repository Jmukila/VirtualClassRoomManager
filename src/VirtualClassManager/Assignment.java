package VirtualClassManager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
public class Assignment {
    private String title;
    private String description;
    private Date dueDate; // Ensure this represents the due date for the assignment
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
   

    public void setSubmissionContent(String content) {
        this.submissionContent = content;
    }

    @Override
    public String toString() {
        return "Title: " + title + ", Description: " + description + ", Due Date: " + dueDate;
    }
}
