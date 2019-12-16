public class Star
{
    public double x;
    public double y;
    public int id;
    public double magnitude;
    public String[] names;

    public Star(){
        x = 0;
        y = 0;
        id = -1;
        magnitude = 0;
        names = new String[0];
    }
    public Star(double x, double y, int id, double magnitude, String[] names){
        this.x = x;
        this.y = y;
        this.id = id;
        this.magnitude = magnitude;
        this.names = names;
    }


}
