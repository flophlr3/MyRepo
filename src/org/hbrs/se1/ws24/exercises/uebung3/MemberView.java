package org.hbrs.se1.ws24.exercises.uebung3;

import java.util.List;

import org.hbrs.se1.ws24.exercises.uebung2.Member.Member;

public class MemberView {
    public void dump(List<Member> memberList) {
        for (Member member : memberList) {
            System.out.println(member.toString());
        }
    }
}
