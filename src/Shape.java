
public abstract class Shape {

    int x = 5;
    
    private Shape() {
        System.out.println("In Shape constructor");
        foo();
    }
    
    public Shape(int y) {
        
    }
    
    public abstract void foo();
}
