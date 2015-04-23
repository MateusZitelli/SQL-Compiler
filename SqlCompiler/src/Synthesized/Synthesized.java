package Synthesized;

import java.util.*;

import GrammaticalElement.*;

public class Synthesized extends GrammaticalElement implements GrammaticalInterface{ 
    @Override
    public boolean isSynthesized () {
        return true;
    }

    public Synthesized (ActionInterface action){
        super(action);
    }
}

