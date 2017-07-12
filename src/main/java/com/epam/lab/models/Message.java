package com.epam.lab.models;


public class Message {
    private String to;
    private String cc;
    private String bcc;

    private String subject;
    private String message;

    public Message(String to, String cc, String bcc, String subject, String message) {
        this.to = to;
        this.cc = cc;
        this.bcc = bcc;
        this.subject = subject;
        this.message = message;
    }

    public Message() {
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public String getBcc() {
        return bcc;
    }

    public void setBcc(String bcc) {
        this.bcc = bcc;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Message message1 = (Message) o;

        if (!to.equals(message1.to)) return false;
        if (!cc.equals(message1.cc)) return false;
        if (!bcc.equals(message1.bcc)) return false;
        if (!subject.equals(message1.subject)) return false;
        return message.equals(message1.message);
    }

    @Override
    public int hashCode() {
        int result = to.hashCode();
        result = 31 * result + cc.hashCode();
        result = 31 * result + bcc.hashCode();
        result = 31 * result + subject.hashCode();
        result = 31 * result + message.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Message{" +
                "to='" + to + '\'' +
                ", cc='" + cc + '\'' +
                ", bcc='" + bcc + '\'' +
                ", subject='" + subject + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
