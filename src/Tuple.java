public class Tuple<E> {
    E x;
    E y;
    public Tuple(E x, E y){
        this.x = x;
        this.y = y;
    }

    public E getX() {
        return x;
    }

    public void setX(E x) {
        this.x = x;
    }

    public E getY() {
        return y;
    }

    public void setY(E y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
