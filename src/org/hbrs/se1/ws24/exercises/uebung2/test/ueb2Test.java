package org.hbrs.se1.ws24.exercises.uebung2.test;

import org.hbrs.se1.ws24.exercises.uebung2.Container.Container;
import org.hbrs.se1.ws24.exercises.uebung2.Exception.ContainerException;
import org.hbrs.se1.ws24.exercises.uebung2.Member.ConcreteMember;
import org.hbrs.se1.ws24.exercises.uebung2.Member.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ueb2Test {

    private Container container;

    @BeforeEach
    public void setup(){
        container = new Container();
    }

    @Test
    void test1() throws ContainerException {
        Member member1 = new ConcreteMember();
        Member member2 = new ConcreteMember();
        Member member3 = new ConcreteMember();
        Member member4 = new ConcreteMember();
        Member member5 = new ConcreteMember();

        assertEquals(0, container.size());
        container.addMember(null);
        container.addMember(member1);
        assertEquals(1, container.size());
        container.addMember(member2);
        container.addMember(member3);
        container.addMember(member4);
        container.addMember(member5);
        assertEquals(5, container.size());
        container.dump();


    }
    @Test
    void nullTest() {
        Member member1 = new ConcreteMember();
        assertThrows(ContainerException.class, () -> {container.addMember(null);
        });
        assertEquals(0,container.size());

    }

}
