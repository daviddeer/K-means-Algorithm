import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;

public class Cluster {
    Map<Integer, Point> points = new HashMap<Integer, Point>();//��Ŵ��еĵ�
    Point center = new Point();//������

    public Cluster() {

    }

    //��ʼ���ô�����
    public void setCenter(Point point) {
        this.center = new Point(point);
    }

    //���ݴ��еĵ���������
    public void calCenter() {
        double a1 = 0;
        double a2 = 0;
        double a3 = 0;
        double a4 = 0;
        for(Integer num: this.points.keySet()) {
            a1+=this.points.get(num).a1;
            a2+=this.points.get(num).a2;
            a3+=this.points.get(num).a3;
            a4+=this.points.get(num).a4;
        }
        a1/=this.points.size();
        a2/=this.points.size();
        a3/=this.points.size();
        a4/=this.points.size();
        Point point = new Point(a1, a2, a3, a4);
        this.setCenter(point);
    }

    //�����
    public void clear() {
        for(Iterator<Map.Entry<Integer, Point>> it = this.points.entrySet().iterator() ; it.hasNext();){
            Map.Entry<Integer, Point> item = it.next();
            it.remove();
        }
    }
}