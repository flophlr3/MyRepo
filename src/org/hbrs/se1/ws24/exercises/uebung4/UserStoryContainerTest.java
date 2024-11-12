package org.hbrs.se1.ws24.exercises.uebung4;


import org.hbrs.se1.ws24.exercises.uebung3.ContainerException;
import org.hbrs.se1.ws24.exercises.uebung3.persistence.PersistenceException;
import org.junit.jupiter.api.*;

class ContainerTest {
    private static Container con;
    private UserStories UserStories1;
    private UserStories UserStories2;

    @BeforeEach
    @Order(0)
    void init() throws PersistenceException {
        con = Container.getInstance();
        UserStories1 = new UserStories("t", "t", 2, 0, 1, 1, "t");
        UserStories2 = new UserStories("b", "b", 1, 3, 4, 1, "b");
        con.deleteAllMembers();
    }
    @Test
    @Order(1)
    void containerEmptyTest(){
        Assertions.assertEquals(0,con.size());
    }

    @Test
    @Order(2)
    void addOneUserStories() throws ContainerException {
        con.addUserStory(UserStories2);
        System.out.println(con.size());
        Assertions.assertEquals(1,con.size());
    }
    @Test
    @Order(3)
    void addTwoUserStories() throws ContainerException{
        con.addUserStory(UserStories1);
        con.addUserStory(UserStories2);
        System.out.println(con.size());
        Assertions.assertEquals(2,con.size());
    }
    @Test
    @Order(4)
    void addAndDeleteStory() throws ContainerException {
        con.addUserStory(UserStories1);
        con.deleteMember(UserStories1.getID());
        Assertions.assertEquals(0,con.size());
        System.out.println(con.size());
    }
    @Test
    @Order(5)
    void calcPrio(){
        UserStories u = new UserStories(null, null, 1, 1, 1, 1, null);
        Assertions.assertEquals(1,u.getPrioritätslevel());
    }

}
