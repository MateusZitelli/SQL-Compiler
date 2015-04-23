package Action;

import java.util.*;


class WhileAct extends Action implements Actionable {
    public void act(ArrayList<Object> stack){
        stack.get(stack.size() - 3).setAttr("teste", "false");
    } 
}

public enum Actions {
    whileAct(WhileAct);

    private Actions();
}
