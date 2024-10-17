package org.hbrs.se1.ws24.exercises.uebung3.persistence;

import org.hbrs.se1.ws24.exercises.uebung2.Member.Member;

import java.util.List;

public class MemberView {

    public static void dump(List<Member> container){
        for(Member member : container){
            System.out.print(member.getID());
        }
    }

}
