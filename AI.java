import java.util.ArrayList;

public class AI {
    private Utility util=new Utility();
    private int depth;
    private ArrayList<Board> visited= new ArrayList<Board>();

    public AI() {
        depth=0;
    }

    public Board miniMax_Base(Board curr){
        ArrayList<Board> successors=curr.getChildren();
        Board nextMove=successors.get(0);
        visited.add(nextMove);
        for (int i=1;i<successors.size();i++){
            if(!visitedContains(visited,successors.get(i))){
                visited.add(successors.get(i));
                int min_val=min(successors.get(i));
                if(util.getUtil(nextMove)<min_val){
                    nextMove=successors.get(i);
                }
            }
        }
        visited.clear();
        return nextMove;
    }

    private int max(Board curr){
        if(curr.isTerm()){
            return util.getUtil(curr);
        }
        int ret=Integer.MIN_VALUE;
        ArrayList<Board> moves=curr.getChildren();
        for (Board s: moves) {
            if(!visitedContains(visited,s)) {
                visited.add(s);
                ret = Math.max(ret, min(s));
                s.setUtility(ret);
            }
        }
        return ret;
    }

    private int min(Board curr){
        if(curr.isTerm()){
            return util.getUtil(curr);
        }
        int ret=Integer.MAX_VALUE;
        ArrayList<Board> moves=curr.getChildren();
        for (Board s: moves){
            if(!visitedContains(visited,s)) {
                visited.add(s);
                ret = Math.min(ret, max(s));
                s.setUtility(ret);
            }
        }
        return ret;
    }


    private Boolean visitedContains(ArrayList<Board> visited, Board b){
        if(visited==null)
            return false;
        for(Board board: visited){
            if(b.equals(board))
                return true;
        }
        return false;
    }
}
