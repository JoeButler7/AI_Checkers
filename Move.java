import java.util.ArrayList;

public class Move implements Actions {
    private Coord start;
    private Coord Result;
    private ArrayList<Coord> capList;

    public Move(Coord start, Coord result, ArrayList<Coord> capList) {
        this.start = start;
        Result = result;
        this.capList = capList;
    }

    @Override
    public boolean isLegal(Board b) {
        if(capList.isEmpty()){
            if(Math.abs(start.getRow()-Result.getRow())!=1)
                return false;
            if(Math.abs(start.getColumn()-Result.getColumn())!=1)
                return false;
            if(b.getBoard()[Result.getColumn()][Result.getRow()].getColor()!=Color.empty)
                return false;
            return true;
        }
        return true;
    }

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
}
