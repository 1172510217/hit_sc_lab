/*
 * Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course
 * staff.
 */
package P2.turtle;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TurtleSoup {

    /**
     * Draw a square.
     * 
     * @param turtle     the turtle context
     * @param sideLength length of each side
     */
    public static void drawSquare(Turtle turtle, int sideLength) {
        turtle.forward(sideLength);//�Ĵ�ǰ���Լ���ת
        turtle.turn(90);
        turtle.forward(sideLength);
        turtle.turn(90);
        turtle.forward(sideLength);
        turtle.turn(90);
        turtle.forward(sideLength);
    }

    /**
     * Determine inside angles of a regular polygon.
     * 
     * There is a simple formula for calculating the inside angles of a polygon;
     * you should derive it and use it here.
     * 
     * @param sides number of sides, where sides must be > 2
     * @return angle in degrees, where 0 <= angle < 360
     */
    public static double calculateRegularPolygonAngle(int sides) {
        return (180 - 360.0 / sides);//�����ڽǺ���ǵĹ�ϵ���Լ���Ǻ͵���360��õ���
    }

    /**
     * Determine number of sides given the size of interior angles of a regular
     * polygon.
     * 
     * There is a simple formula for this; you should derive it and use it here.
     * Make sure you *properly round* the answer before you return it (see
     * java.lang.Math).
     * HINT: it is easier if you think about the exterior angles.
     * 
     * @param angle size of interior angles in degrees, where 0 < angle < 180
     * @return the integer number of sides
     */
    public static int calculatePolygonSidesFromAngle(double angle) {
        double sides = 360 / (180 - angle);
        int res = (int) sides;
        return (sides - res) >= 0.5 ? res + 1 : res;//ע��int�������ͻ���ȥһ��������Ҫ�����ж�
    }

    /**
     * Given the number of sides, draw a regular polygon.
     * 
     * (0,0) is the lower-left corner of the polygon; use only right-hand turns to
     * draw.
     * 
     * @param turtle     the turtle context
     * @param sides      number of sides of the polygon to draw
     * @param sideLength length of each side
     */
    public static void drawRegularPolygon(Turtle turtle, int sides, int sideLength) {
        for (int i = 0; i < sides; i++) {
            turtle.forward(sideLength);
            turtle.turn(180 - calculateRegularPolygonAngle(sides));//forѭ�����ɣ�����Ϊ������ǰ������Ϊ�߳���ƫת�Ƕ�Ϊ���
        }
    }

    /**
     * Given the current direction, current location, and a target location,
     * calculate the Bearing
     * towards the target point.
     * 
     * The return value is the angle input to turn() that would point the turtle in
     * the direction of
     * the target point (targetX,targetY), given that the turtle is already at the
     * point
     * (currentX,currentY) and is facing at angle currentBearing. The angle must be
     * expressed in
     * degrees, where 0 <= angle < 360.
     *
     * HINT: look at http://en.wikipedia.org/wiki/Atan2 and Java's math libraries
     * 
     * @param currentBearing current direction as clockwise from north
     * @param currentX       current location x-coordinate
     * @param currentY       current location y-coordinate
     * @param targetX        target point x-coordinate
     * @param targetY        target point y-coordinate
     * @return adjustment to Bearing (right turn amount) to get to target point,
     *         must be 0 <= angle < 360
     */
    public static double calculateBearingToPoint(double currentBearing, int currentX, int currentY, int targetX,
            int targetY) {
        double angle = 0;
        if (targetX == currentX) {// �����ǰ���Ŀ������ߴ�ֱ��x��
            if (targetY > currentY) {
                angle = 0;
            } else {
                angle = 180;//���������غϵ������������Ϊ͹���㷨���̵棩
            }
        } else {
            angle = Math.atan((double) (targetY - currentY) / (double) (targetX - currentX));
            angle = Math.toDegrees(angle);//������ת��Ϊ�Ƕ�
            if (targetX < currentX) {
                angle -= 180;
            }
            if (angle <= 90) {
                angle = 90 - angle;
            } else {
                angle = 450 - angle;
            }
        }
        angle -= currentBearing;//�õ�ƫת�Ƕ�
        return angle >= 0 ? angle : 360 + angle;//ƫת�Ƕ�һ������0��360�ȷ�Χ��
    }

    /**
     * Given a sequence of points, calculate the Bearing adjustments needed to get
     * from each point
     * to the next.
     * 
     * Assumes that the turtle starts at the first point given, facing up (i.e. 0
     * degrees).
     * For each subsequent point, assumes that the turtle is still facing in the
     * direction it was
     * facing when it moved to the previous point.
     * You should use calculateBearingToPoint() to implement this function.
     * 
     * @param xCoords list of x-coordinates (must be same length as yCoords)
     * @param yCoords list of y-coordinates (must be same length as xCoords)
     * @return list of Bearing adjustments between points, of size 0 if (# of
     *         points) == 0,
     *         otherwise of size (# of points) - 1
     */
    public static List<Double> calculateBearings(List<Integer> xCoords, List<Integer> yCoords) {
        List<Double> result = new ArrayList<>();
        double currentBearing = 0;//��ʼ�Ƕ�Ϊ0
        double angle = 0;
        for (int i = 0; i < xCoords.size() - 1; i++) {
            currentBearing = calculateBearingToPoint(angle, xCoords.get(i), yCoords.get(i), xCoords.get(i + 1),
                    yCoords.get(i + 1));//��ʼ�Ƕ�Ϊ��һ�εĽǶ�
            result.add(currentBearing);
            angle = (angle + currentBearing) % 360;//��ʼ�Ƕ�ʼ����0��360�ȷ�Χ��
        }
        return result;
    }

    /**
     * Given a set of points, compute the convex hull, the smallest convex set that
     * contains all the points
     * in a set of input points. The gift-wrapping algorithm is one simple approach
     * to this problem, and
     * there are other algorithms too.
     * 
     * @param points a set of points with xCoords and yCoords. It might be empty,
     *               contain only 1 point, two points or more.
     * @return minimal subset of the input points that form the vertices of the
     *         perimeter of the convex hull
     * @throws InterruptedException 
     */
    public static Set<Point> convexHull(Set<Point> points) throws InterruptedException {
        Set<Point> convexHull = new HashSet<Point>();
        List<Point> pointsSet = new ArrayList<>();
        convexHull.clear();
        if (points.size() == 0) {
            return convexHull;//�õ������е�Ԫ��
        }
        int count = 0;
        for (Point point : points) {
            pointsSet.add(count, point);
            count++;
        } // Ѱ�������·��ĵ�
        double xleft = pointsSet.get(0).x();// ���ڼ�¼��С��x
        int index = 0;// ���ڼ�¼�����½ǵĵ�
        for (int i = 0; i < count; i++) {
            if ((int)pointsSet.get(i).x() < xleft) {// �ж�xֵ�Ĵ�С���ҵ���С��x�������ڵ�λ��
                xleft = pointsSet.get(i).x();
                index = i;
                continue;
            }
            if ((int)pointsSet.get(i).x() == xleft) {// ���õ�������ѡ��xֵ��ȣ����ж�yֵ��С
                if ((int)pointsSet.get(i).y() < pointsSet.get(i).y()) {
                    index = i;
                }
            }
        }
        convexHull.add(pointsSet.get(index));// ����Ѱ��turn�ĽǶ���С�ĵ㼯
        int indexfinal = index;// ���ڼ�¼ѡ�����һ���㣬ͬʱ����whileѭ���ıȽϳ���
        double angle1 = 0, angle2 = 0;
        int k = 0;// ���ڸ��´�ѡȡ�ĵ�
        Boolean[] flags = new Boolean[count];
        for (int i = 0; i < count; i++) {
            flags[i] = false;
        }
        flags[index] = true;
        do {//Ϊ�˷�����㣬���е���ʼ�ǶȾ���Ϊ0��
            for (int i = 0; i < count; i++) {// Ѱ��һ����ʼ�ļ����
                if (!flags[i] || i == index) {
                    angle1 = calculateBearingToPoint(0, (int) pointsSet.get(indexfinal).x(),
                            (int) pointsSet.get(indexfinal).y(), (int) pointsSet.get(i).x(), (int) pointsSet.get(i).y());
                    k = i;
                    break;
                }
            }
            for (int i = 0; i < count; i++) {
                if (!flags[i] || i == index) {//�Ƚϵõ�һ����С��ƫת�����ڵ��յ�
                    angle2 = calculateBearingToPoint(0, (int) pointsSet.get(indexfinal).x(),
                            (int) pointsSet.get(indexfinal).y(), (int) pointsSet.get(i).x(), (int) pointsSet.get(i).y());
                    if (angle2 < angle1) {
                        angle1 = angle2;
                        k = i;
                        continue;
                    }
                    if (angle2 == angle1) {// ����������ƫת�Ƕ�һ������ѡ��������Ŀǰ���Զ�ĵ�
                        if (Math.abs(pointsSet.get(indexfinal).x() - pointsSet.get(i).x()) > Math
                                .abs(pointsSet.get(k).x() - pointsSet.get(indexfinal).x())) {
                            k = i;
                        }
                    }
                }
            }
            indexfinal = k;
            if (indexfinal != index) {//����յ������㣬�������ٴμ���õ�
                convexHull.add(pointsSet.get(indexfinal));
                System.out.println("�����"+"("+pointsSet.get(indexfinal).x()+","+(pointsSet.get(indexfinal).y())+")");
            }
            flags[indexfinal] = true;
        } while (index != indexfinal);
        return convexHull;
    }

    /**
     * Draw your personal, custom art.
     * 
     * Many interesting images can be drawn using the simple implementation of a
     * turtle. For this
     * function, draw something interesting; the complexity can be as little or as
     * much as you want.
     * 
     * @param turtle the turtle context
     */
    public static void drawPersonalArt(Turtle turtle) {
        for (int i = 0; i < 200; i++) {//�򵥵�forѭ��
            turtle.forward(2 * i);//ǰ�����Ǳ����Ϳ��Ի����ÿ���ͼ��
            turtle.turn(110);//ƫת��Ҫ����
            switch (i % 10) {//����ͨ��switch���ı�ͼ����ɫ
            case 0:
                turtle.color(PenColor.BLACK);
                break;
            case 1:
                turtle.color(PenColor.GRAY);
                break;
            case 2:
                turtle.color(PenColor.RED);
                break;
            case 3:
                turtle.color(PenColor.PINK);
                break;
            case 4:
                turtle.color(PenColor.ORANGE);
                break;
            case 5:
                turtle.color(PenColor.YELLOW);
                break;
            case 6:
                turtle.color(PenColor.GREEN);
                break;
            case 7:
                turtle.color(PenColor.CYAN);
                break;
            case 8:
                turtle.color(PenColor.BLUE);
                break;
            case 9:
                turtle.color(PenColor.MAGENTA);
            }
        }

    }

    /**
     * Main method.
     * 
     * This is the method that runs when you run "java TurtleSoup".
     * 
     * @param args unused
     */
    public static void main(String args[]) {
        DrawableTurtle turtle = new DrawableTurtle();

         drawSquare(turtle, 40);
        // drawRegularPolygon(turtle, 100, 8);
        drawPersonalArt(turtle);

        // draw the window
        turtle.draw();
        // Set<Point> points = new HashSet<Point>();
        // Point p11 = new Point(1, 1);
        // points.add(p11);
        // convexHull(points);
    }

}
