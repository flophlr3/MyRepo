package org.hbrs.se1.ws24.exercises.uebung2.Member;


public class ConcreteMember implements Member {

    private static int counter = 0;
    private int id;


    public ConcreteMember(){
        this.id = ++counter;
    }


    @Override
    public Integer getID() {
        return this.id;
    }
}
