public class Child extends Parent {

    public Child() {
        // Implicitly calls parent constructor; or explicitly call with super()
        super();
        // someMethod();
        // staticMethod();
    }

    public static void staticMethod() {
        System.out.println("Child staticMethod");
    }

    public void instanceMethod() {
        staticMethod();
        System.out.println("Child instanceMethod");
    }
}
