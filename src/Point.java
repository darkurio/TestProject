
public class Point {
    private int x = -1;
    private int y = -1;
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public int getX() {
        return this.x;
    }
    public int getY() {
        return this.y;
    }
    public boolean equals(Object o) {
        if (o == null)
            return false;
        if (o == this)
            return true;
        if (o.getClass() != getClass())
            return false;
        
        Point oP = (Point)o;
        return (this.x == oP.x &&
                this.y == oP.y);
    }
}
