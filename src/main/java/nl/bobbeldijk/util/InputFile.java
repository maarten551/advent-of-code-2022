package nl.bobbeldijk.util;

public enum InputFile {
    DAY1("day-1"),
    DAY2("day-2"),
    DAY3("day-3"),
    DAY4("day-4"),
    DAY5("day-5"),
    DAY6("day-6"),
    DAY7("day-7"),
    DAY8("day-8"),
    DAY9("day-9"),
    DAY10("day-10"),
    DAY11("day-11");

    private final String filename;

    InputFile(String filename) {
        this.filename = filename;
    }

    public String getFilename() {
        return filename;
    }
}
