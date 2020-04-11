package me.kisoft.covid19.models;

public class ICPCEntry {
    private String code;
    private String title;
    private ICPCType type;

    public ICPCEntry() {
    }

    public ICPCEntry(String code, String title, ICPCType type) {
        this.code = code;
        this.title = title;
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ICPCType getType() {
        return type;
    }

    public void setType(ICPCType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return title;
    }
}
