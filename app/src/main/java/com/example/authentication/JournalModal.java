package com.example.authentication;

public class JournalModal {
    private int id;
    private String entry;
    private String entry1;
    private String date;

    // creating getter and setter methods
    public String getentry() { return entry; }

    public void setentry(String entry)
    {
        this.entry = entry;
    }

    public String getentry1()
    {
        return entry1;
    }

    public void setentry1(String entry1)
    {
        this.entry1 = entry1;
    }

    public String getdate() { return date; }

    public void setdate(String date)
    {
        this.date = date;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public JournalModal(String entry, String entry1, String date) {
        this.entry = entry;
        this.entry1 = entry1;
        this.date = date;
    }

}

