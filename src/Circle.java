
public class Circle extends Shape {

    int x = 3;
    
    public Circle() {
        super(0);
        System.out.println("In Circle constructor");
        foo();
    }
    
    public void foo() {
        System.out.println(x);
    }
}
