import java.util.ArrayList;

public class AI {
    private Utility util=new Utility();
    private int depth;
    private ArrayList<Board> visited= new ArrayList<Board>();
    private int Alpha, Beta;

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

    public Board Alpha_Beta(Board curr){
        ArrayList<Board> successors=curr.getChildren();
        Board nextMove=successors.get(0);
        Alpha=Integer.MIN_VALUE;
        Beta=Integer.MAX_VALUE;
        visited.add(nextMove);
        for (int i=1;i<successors.size();i++){
            if(!visitedContains(visited,successors.get(i))){
                visited.add(successors.get(i));
                int min_val=AB_Min(successors.get(i), Alpha, Beta);
                if(util.getUtil(nextMove)<min_val){
                    nextMove=successors.get(i);
                }
            }
        }
        visited.clear();
        return nextMove;
    }

    private int AB_Max(Board curr, int Alpha, int Beta){
        if(curr.isTerm()){
            return util.getUtil(curr);
        }
        int ret=Integer.MIN_VALUE;
        ArrayList<Board> moves=curr.getChildren();
        for(Board b: moves){
            if(!visitedContains(visited,b)) {
                ret = Math.min(ret, AB_Min(b, Alpha, Beta));
                visited.add(b);
                if (ret >= Beta)
                    return ret;
            }
            Alpha=Math.max(Alpha, ret);
        }
        return ret;
    }
    private int AB_Min(Board curr, int Alpha, int Beta){
        if(curr.isTerm()){
            return util.getUtil(curr);
        }
        int ret=Integer.MAX_VALUE;
        ArrayList<Board> moves=curr.getChildren();
        for(Board b: moves){
            if(!visitedContains(visited,b)) {
                ret = Math.min(ret, AB_Max(b, Alpha, Beta));
                visited.add(b);
                if (ret <= Alpha)
                    return ret;
            }

            Beta=Math.min(Beta, ret);
        }
        return ret;
    }

    public Board ABH(Board curr){
        ArrayList<Board> successors=curr.getChildren();
        depth=0;
        Board nextMove=successors.get(0);
        Alpha=Integer.MIN_VALUE;
        Beta=Integer.MAX_VALUE;
        visited.add(nextMove);
        for (int i=1;i<successors.size();i++){
            if(!visitedContains(visited,successors.get(i))){
                visited.add(successors.get(i));
                int min_val=AB_Min(successors.get(i), Alpha, Beta);
                if(util.getUtil(nextMove)<min_val){
                    nextMove=successors.get(i);
                }
            }
        }
        visited.clear();
        return nextMove;
    }
    private int ABH_Max(Board curr, int Alpha, int Beta){
        if(curr.isTerm()){
            return util.getUtil(curr);
        }
        else if(depth==5){
            return Heuristic(curr);
        }
        int ret=Integer.MIN_VALUE;
        ArrayList<Board> moves=curr.getChildren();
        for(Board b: moves){
            if(!visitedContains(visited,b)) {
                ret = Math.min(ret, ABH_Min(b, Alpha, Beta));
                depth++;
                visited.add(b);
                if (ret >= Beta)
                    return ret;
            }
            Alpha=Math.max(Alpha, ret);
        }
        return ret;
    }
    private int ABH_Min(Board curr, int Alpha, int Beta){
        if(curr.isTerm()){
            return util.getUtil(curr);
        }
        else if(depth==5){
            return Heuristic(curr);
        }
        int ret=Integer.MAX_VALUE;
        ArrayList<Board> moves=curr.getChildren();
        for(Board b: moves){
            if(!visitedContains(visited,b)) {
                depth++;
                ret = Math.min(ret, ABH_Max(b, Alpha, Beta));
                visited.add(b);
                if (ret <= Alpha)
                    return ret;
            }

            Beta=Math.min(Beta, ret);
        }
        return ret;
    }

    private int Heuristic(Board b){
        int ret=0;
        int opposition=0;
        if(b.isTurn()){
            for(Piece p: b.getWhite()){
                if(p.getColor()==Color.WhiteKing)
                    ret+=3;
                if(p.getColor()==Color.White)
                    ret+=2;
            }
            for(Piece p: b.getBlack()){
                if(p.getColor()==Color.BlackKing)
                    opposition+=3;
                if(p.getColor()==Color.Black)
                    opposition+=2;
            }
            ret-=opposition;
        }
        else {
            for(Piece p: b.getWhite()){
                if(p.getColor()==Color.WhiteKing)
                    opposition+=3;
                if(p.getColor()==Color.White)
                    opposition+=2;
            }
            for(Piece p: b.getBlack()){
                if(p.getColor()==Color.BlackKing)
                    ret+=3;
                if(p.getColor()==Color.Black)
                    ret+=2;
            }
            ret-=opposition;
            ret*=-1;
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
