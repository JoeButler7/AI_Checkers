public class Utility implements Util {
    @Override
    public int getUtil(Board b) {
        if(b.getBlack().size()==0){
            return 1;
        }
        if(b.getWhite().size()==0){
            return -1;
        }
        if(b.isTurn()){
            if(b.getChildren().isEmpty())
                return -1;
        }
        if(!b.isTurn()){
            if(b.getChildren().isEmpty())
                return 1;
        }
        return 0;
    }
}
