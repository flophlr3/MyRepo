package org.hbrs.se1.ws24.exercises.uebung3;

import java.util.List;

import org.hbrs.se1.ws24.exercises.uebung2.Member.ConcreteMember;
import org.hbrs.se1.ws24.exercises.uebung2.Container.Container;
import org.hbrs.se1.ws24.exercises.uebung2.Exception.ContainerException;
import org.hbrs.se1.ws24.exercises.uebung2.Member.Member;
import org.hbrs.se1.ws24.exercises.uebung3.persistence.PersistenceException;

public class Client {

    public void start() {
        Container container = Container.getInstance();
        // MemberObjekt in Container hinzufügen
        try {
            container.addMember(new ConcreteMember(1));
            container.addMember(new ConcreteMember(2));
            container.addMember(new ConcreteMember(3));
            container.addMember(new ConcreteMember(4));
        } catch (ContainerException e) {
            e.printStackTrace();
        }

        // Abspeichern der Objekte
        try {
            container.store();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Liste aus den Container
        List<Member> liste = container.getCurrentList();

        // Memberview erstellen
        MemberView memberView = new MemberView();
        memberView.dump(liste);

        // Liste Laden
        try {
            container.load();
        } catch (PersistenceException e) {
            e.printStackTrace();
        }

        // Liste wieder aus den Container
        liste = container.getCurrentList();

        // liste ausgeben
        memberView = new MemberView();
        memberView.dump(liste);
    }
}
