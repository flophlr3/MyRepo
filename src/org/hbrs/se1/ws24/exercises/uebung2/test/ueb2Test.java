package org.hbrs.se1.ws24.exercises.uebung2.test;

import org.hbrs.se1.ws24.exercises.uebung2.Container.Container;
import org.hbrs.se1.ws24.exercises.uebung2.Exception.ContainerException;
import org.hbrs.se1.ws24.exercises.uebung2.Member.ConcreteMember;
import org.hbrs.se1.ws24.exercises.uebung2.Member.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

class ueb2Test {

    // für den Prinstream Test
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    private Container container;

    @BeforeEach
    public void setup(){
        container = Container.getInstance();
        ConcreteMember.resetCounter();

    }

    @Test
    void addAndDeleteMembers() throws ContainerException {

        Member member1 = new ConcreteMember(1);
        Member member2 = new ConcreteMember(1);
        Member member3 = new ConcreteMember(1);
        Member member4 = new ConcreteMember(1);
        Member member5 = new ConcreteMember(1);

        assertEquals(0, container.size());
        container.addMember(member1);
        assertEquals(1, container.size());
        container.addMember(member2);
        assertEquals(2, container.size());
        container.addMember(member3);
        assertEquals(3, container.size());
        container.addMember(member4);
        assertEquals(4, container.size());
        container.addMember(member5);
        assertEquals(5, container.size());

        container.deleteMember(5);
        assertEquals(4, container.size());
        container.deleteMember(4);
        assertEquals(3, container.size());
        container.deleteMember(3);
        assertEquals(2, container.size());
        container.deleteMember(2);
        assertEquals(1, container.size());
    }

    @Test
    void nullTest() {
        // "null" Member hinzufügen
        assertThrows(   ContainerException.class, () -> {   container.addMember(null);  }   );
        assertEquals(0,container.size());


    }

    @Test
    void deleteMembers() throws ContainerException {

        Member member1 = new ConcreteMember(1);
        Member member2 = new ConcreteMember(1);
        Member member3 = new ConcreteMember(1);
        Member member4 = new ConcreteMember(1);
        Member member5 = new ConcreteMember(1);
        // Member hinzufügen
        container.addMember(member1);
        container.addMember(member2);
        container.addMember(member3);
        container.addMember(member4);
        container.addMember(member5);

        // Ausgabe für den ersten Dump
        System.setOut(new PrintStream(outputStreamCaptor));
        container.getCurrentList();
        assertEquals("1, 2, 3, 4, 5,", outputStreamCaptor.toString().trim());

        // Member löschen
        container.deleteMember(3);

        outputStreamCaptor.reset(); //Stream zurückzusetzen

        // Ausgabe für den zweiten Dump
        System.setOut(new PrintStream(outputStreamCaptor));
        container.getCurrentList();
        assertEquals("1, 2, 4, 5,", outputStreamCaptor.toString().trim());
    }

    @Test
    void doppeltHinzufuegen() throws ContainerException{
        Member member1 = new ConcreteMember(1);
        container.addMember(member1);
        assertThrows(   ContainerException.class, () -> {   container.addMember(member1);  }   );

    }



}

