package Action;

import java.util.*;

public class Action implements Attributable { 
    public Map<String, String> attrs = new HashMap<String, String>();
    public String getAttr(String key) {
        return attrs.get(key);
    }

    public void setAttr(String key, String value) {
        attrs.put(key, value);
    }
}
public class WhileAct extends Action implements Actionable {
    public void act(ArrayList<Object> stack){
        stack.get(stack.size() - 3).setAttr("teste", "false");
    } 
}
