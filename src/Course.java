public enum Course {
    ENGI("engi"), CPSC("cpsc"), CHEM("chem");

    private String value = null;

    private Course(String value) {
        this.value = value;
    }

    public String toString() {
        return this.value;
    }
}
