package GrammaticalElement;

import java.util.*;

public interface ActionInterface {
    void act(Stack<GrammaticalInterface> stack, Map<String, String> attrs);
}
