import java.util.ArrayList;
import java.util.List;

public class Ant {
    private int locate;
    private double wayLength = 0;
    private List way = new ArrayList<>();

    public Ant() {
    }

    public int getLocate() {
        return locate;
    }

    public void setLocate(int locate) {
        this.locate = locate;
    }

    public double getWayLength() {
        return wayLength;
    }

    public void setWayLength(double wayLength) {
        this.wayLength = wayLength;
    }

    public List getWay() {
        return way;
    }

    public void setWay(List way) {
        this.way = way;
    }
}
