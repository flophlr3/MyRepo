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

    public String deleteMember(int id) {
        if (container.isEmpty()) {
            return "Container ist leer";
        }

        for (Member member : container) {
            if (member.getID() == id) { // Vergleich mit == statt equals
                container.remove(member);
                return "Member [" + member.getID() + "] wurde gelöscht"; // Hier die ID zurückgeben
            }
        }

        return "Member mit der ID [" + id + "] wurde nicht gefunden";
    }

    public void dump() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < container.size(); i++) {
            sb.append(container.get(i).getID());
            if (i < container.size() - 1) {
                sb.append(", ");
            }
        }
        System.out.print(sb.toString() + ","); // Komma am Ende der Ausgabe hinzufügen
    }

    public int size(){
        return container.size();
    }
}
