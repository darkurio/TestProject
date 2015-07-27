
public class InterfaceImplementation implements MyInterface2, MyInterface, MyInterface3 {

    @Override
    public void myInterfaceMethod() {
        
    }

    public int outputInfo() {
        System.out.println(MyInterface3.FUEZA);
        System.out.println(MyInterface.LA);
        return 2;
    }
}
