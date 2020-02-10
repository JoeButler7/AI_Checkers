import java.util.ArrayList;

public class Move implements Actions {
    private Coord start;
    private Coord Result;
    private ArrayList<Coord> capList;
    private int caps;

    public Move(Coord start, Coord result, ArrayList<Coord> capList, int caps) {
        this.start = start;
        Result = result;
        this.capList = capList;
        this.caps=caps;
    }

    @Override
    public boolean isLegal(Board b) {
            Board temp=b.getBoardFromMove(this);
            return arrListContains(temp, b.getChildren());
    }

    private boolean arrListContains(Board b, ArrayList<Board> boards){
        for(Board temp: boards){
            if(b.equals(temp))
                return true;
        }
        return false;
    }

    //getters and setter
    public Coord getStart() {
        return start;
    }

    public void setStart(Coord start) {
        this.start = start;
    }

    public Coord getResult() {
        return Result;
    }

    public void setResult(Coord result) {
        Result = result;
    }

    public ArrayList<Coord> getCapList() {
        return capList;
    }

    public void setCapList(ArrayList<Coord> capList) {
        this.capList = capList;
    }

    public int getCaps() {
        return caps;
    }

    public void setCaps(int caps) {
        this.caps = caps;
    }
}
