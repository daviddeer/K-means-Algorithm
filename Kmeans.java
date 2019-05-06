// import Point;
// import Cluster;

import java.util.Map;
import java.util.Random;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Scanner;
import java.io.IOException;
import java.util.Map.Entry;

public class Kmeans {
    public static void main(String[] args) {
        String FILE_ADDRESS = "iris.txt";// ���������ļ���ַ
        File input = new File(FILE_ADDRESS);
        double J = 0;// ƽ�����J
        Map<Integer, Point> data = new HashMap<Integer, Point>();

        Integer number = 1;
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(input));
            String tempString = null;
            // һ�ζ���һ�У�ֱ������nullΪ�ļ�����
            while ((tempString = reader.readLine()) != null) {
                String[] attr = tempString.split("\t");
                Point point = new Point();
                point.a1 = Double.parseDouble(attr[0]);
                point.a2 = Double.parseDouble(attr[1]);
                point.a3 = Double.parseDouble(attr[2]);
                point.a4 = Double.parseDouble(attr[3]);
                data.put(number, point);
                number++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }

        System.out.println("Please input the number of the cluster that you want: ");
        Scanner sc = new Scanner(System.in);
        int clusterNumber = sc.nextInt();
        sc.close();

        Cluster[] clusters = new Cluster[clusterNumber];
        for (int i = 0; i < clusterNumber; ++i) {
            clusters[i] = new Cluster();
        }

        // ��ԭ���ݼ������ѡȡk����ͬ�����ݶ�����Ϊk���ص�����
        Integer[] ran = new Integer[clusterNumber];
        for (int i = 0; i < clusterNumber; ++i) {
            ran[i] = (int) (Math.random() * data.size());
            Point point = new Point(data.get(ran[i]));
            clusters[i].setCenter(point);
            clusters[i].points.put(ran[i], point);
        }

        // ��������
        double Jtemp = -1;
        while (Jtemp != J) {
            J = Jtemp;
            for(int i = 0; i < clusterNumber; ++i) {
                clusters[i].clear();
            }
            for (Entry<Integer, Point> entry : data.entrySet()) {
                double[] distances = new double[clusterNumber];// ���ÿ�����ݶ��󵽸����صľ���
                for (int i = 0; i < clusterNumber; ++i) {
                    distances[i] = calDistance(entry.getValue(), clusters[i].center);
                }
                int num = findShortestDistance(distances);
                clusters[num].points.put(entry.getKey(), entry.getValue());
            }
            for (int i = 0; i < clusterNumber; ++i) {
                clusters[i].calCenter();
            }
            Jtemp = calJ(clusters);
        }

        // ��ӡ���---------------------------------
        for (int i = 0; i < clusterNumber; ++i) {
            System.out.println("--------------------��" + i + "�еĵ���---------------------");
            for (Entry<Integer, Point> entry : clusters[i].points.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue().a1 + " " + entry.getValue().a2 + " "
                        + entry.getValue().a3 + " " + entry.getValue().a4);
            }
        }
        for(int i = 0; i < clusterNumber; ++i) {
            System.out.print(clusters[i].points.size() + " ");
        }
        System.out.println();
    }

    // ����������ľ���
    public static double calDistance(Point point1, Point point2) {
        return (Math.sqrt(Math.pow(point1.a1 - point2.a1, 2) + Math.pow(point1.a2 - point2.a2, 2)
                + Math.pow(point1.a3 - point2.a3, 2) + Math.pow(point1.a4 - point2.a4, 2)));
    }

    // �ҳ���̾����Ӧ�Ĵصı��
    public static int findShortestDistance(double[] distances) {
        double shortest = distances[0];
        int num = 0;
        for (int i = 1; i < distances.length; ++i) {
            if (shortest > distances[i]) {
                shortest = distances[i];
                num = i;
            }
        }
        return num;
    }

    // ����ƽ�����
    public static double calJ(Cluster[] clusters) {
        double J = 0;
        for (int i = 0; i < clusters.length; ++i) {
            double temp = 0;
            for (Integer num : clusters[i].points.keySet()) {
                temp += Math.pow(calDistance(clusters[i].points.get(num), clusters[i].center), 2);
            }
            J += temp;
        }
        return J;
    }
}