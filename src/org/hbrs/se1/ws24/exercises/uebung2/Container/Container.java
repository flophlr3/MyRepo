package org.hbrs.se1.ws24.exercises.uebung2.Container;

import org.hbrs.se1.ws24.exercises.uebung2.Exception.ContainerException;
import org.hbrs.se1.ws24.exercises.uebung2.Member.Member;

import java.util.ArrayList;



public class Container {

    private ArrayList<Member> container;

    public Container(){

        this.container = new ArrayList<>();

    }

    private void enthaelt(int id) throws ContainerException {
        for (Member member : container) {
            if (member.getID().equals(id)) {
                throw new ContainerException("Das Member-Objekt mit der ID" + id + " ist bereits vorhanden!");
            }
        }

    }

    public void addMember(Member member) throws ContainerException{
        if(member == null) {
            throw new ContainerException("Member is null");
        }
            if (!container.isEmpty()) {
                enthaelt(member.getID());
            }
            container.add(member);


    }
    
    public String deleteMember(int id){
        for(Member member : container) {
            if(member.getID().equals(id)){
                container.remove(member);
                return "Member [" + member + "] wurde gelöscht";
            }
            return "Member mit der id ["+ id +"] wurde nicht gefunden";
        }
        return "Container ist leer";
    }

    public void dump(){
        for (Member member : container ){
            System.out.print(member.getID() + ", ");
        }
    }

    public int size(){
        return container.size();
    }
}
