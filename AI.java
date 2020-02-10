import java.util.ArrayList;

public class AI {
    private Utility util=new Utility();
    private int depth;

    public AI() {
        depth=0;
    }

    public Board miniMax_Base(Board curr){
        ArrayList<Board> successors=curr.getChildren();
        Board nextMove=successors.get(0);
        for (int i=1;i<successors.size();i++){
            int min_val=min(successors.get(i));
            if(util.getUtil(nextMove)<min_val){
                nextMove=successors.get(i);
            }
        }
        return nextMove;
    }

    private int max(Board curr){
        if(curr.isTerm()){
            return util.getUtil(curr);
        }
        int ret=Integer.MIN_VALUE;
        ArrayList<Board> moves=curr.getChildren();
        for (Board s: moves) {
            ret = Math.max(ret, min(s));
            s.setUtility(ret);
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
            ret=Math.min(ret,max(s));
            s.setUtility(ret);
        }
        return ret;
    }
}
