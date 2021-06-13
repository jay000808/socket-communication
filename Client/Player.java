import java.io.*;
import java.net.*;
import java.util.*;

public class Player {
    public static String name;
    public static int point;

    Player(String name, int point){
        this.name = name;
        this.point = point;
    }

    public static String getName() {
        return name;
    }

    public int getPoint() {
        return point;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public void setPoint(int point) {
        this.point = point;
    }

    public static int incrementPoint(){
        return point++;
    }
}
