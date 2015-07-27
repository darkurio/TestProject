
public interface MyInterface extends MyInterface2 {

    public static int FUEZA = 10; // Automatically final
    public int LA = 10; // Automatically static and final

    public abstract void myInterfaceMethod();
    public int outputInfo();
}
