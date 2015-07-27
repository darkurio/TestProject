public class CourseTypeSafe {

    private String name = null;

    public static final CourseTypeSafe ENGI = new CourseTypeSafe("engi");
    public static final CourseTypeSafe CPSC = new CourseTypeSafe("cpsc");
    public static final CourseTypeSafe CHEM = new CourseTypeSafe("chem");

    private CourseTypeSafe(String name) {
        this.name = name;
    }

    public String toString() {
        return this.name;
    }
}
