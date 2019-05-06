public class Point {
    //一个记录里面四个属性
    double a1;
    double a2;
    double a3;
    double a4;

    public Point() {
        
    }

    public Point(Point point) {
        this.a1 = point.a1;
        this.a2 = point.a2;
        this.a3 = point.a3;
        this.a4 = point.a4;
    }

    public Point(double a1, double a2, double a3, double a4) {
        this.a1 = a1;
        this.a2 = a2;
        this.a3 = a3;
        this.a4 = a4;
    }
}