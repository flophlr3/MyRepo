package org.hbrs.se1.ws24.exercises.uebung4;

import org.hbrs.se1.ws24.exercises.uebung2.Exception.ContainerException;
import org.hbrs.se1.ws24.exercises.uebung3.persistence.PersistenceException;
import org.hbrs.se1.ws24.exercises.uebung3.persistence.PersistenceStrategyStream;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class main {

    public static final String GREEN = "\033[0;32m";
    public static void main(String[] args) throws ContainerException, PersistenceException {
        // Referenz auf das Container-Objekt holen
        Container container = Container.getInstance();
        // Strategie für Stream-Strategy erzeugen und in den Container einsetzen
        container.setPersistenceStrategie(new PersistenceStrategyStream());
        help();


        Scanner sc = new Scanner(System.in);
        boolean ende = false;

        while (!ende) {
            System.out.println("Was möchtest du machen?");
            System.out.print("> ");
            String befehl = sc.next();
            if (befehl.equals("enter")) {
                try {
                    enter(container);
                } catch (PersistenceException e) {
                    throw new PersistenceException(PersistenceException.ExceptionType.ImplementationNotAvailable, "");
                }

            }
            if (befehl.equals("store")) {
                try {
                    store(container);
                } catch (Exception e) {
                    throw new PersistenceException(PersistenceException.ExceptionType.ImplementationNotAvailable, "");

                }
            }
            if (befehl.equals("load")) {
                try {
                    load(container);
                } catch (Exception e) {
                    throw new PersistenceException(PersistenceException.ExceptionType.ImplementationNotAvailable, "");

                }
            }
            if (befehl.equals("search")) {
                String pn = sc.next();
                try {
                    search(pn, container);
                } catch (Exception e) {
                    throw new PersistenceException(PersistenceException.ExceptionType.ImplementationNotAvailable, "");

                }

            }
            if (befehl.equals("dump")) {
                try {

                    dump(container);
                } catch (Exception e) {
                    throw new PersistenceException(PersistenceException.ExceptionType.ImplementationNotAvailable, "");

                }

            }
            if (befehl.equals("exit")) {
                ende = true;
            }
            if (befehl.equals("help")){
                try {
                    help();
                } catch (Exception e) {
                    throw new PersistenceException(PersistenceException.ExceptionType.ImplementationNotAvailable, "");

                }
            }


        }
        sc.close();

    }
    public static void enter(Container con) throws PersistenceException, ContainerException, org.hbrs.se1.ws24.exercises.uebung3.ContainerException {
        System.out.println("Gib deine Beschreibung ein. Wenn du fertig bist schreibe am ende ENDE");
        Scanner sc1 = new Scanner(System.in);
        // String beschreibung = sc1.nextLine();

        String beschreibung = "";
        boolean ende = false;
        while(!ende){
            String g = sc1.next();
            if(g.equals("ENDE")){
                ende = true;
            }
            else{
                beschreibung = beschreibung + g + " ";
            }
        }
        System.out.println("Gib die Anforderungskriterien ein. Wenn du fertig bist schreib am ende ENDE");
        String anforderungskriterien = "";
        ende = false;
        while(!ende){
            String g = sc1.next();
            if(g.equals("ENDE")){
                ende = true;
            }
            else{
                anforderungskriterien = anforderungskriterien + g + " ";
            }
        }

        System.out.println("Gib einen Mehrwert von 1-5 ein");
        int mehrwert = sc1.nextInt();
        if (mehrwert<1 || mehrwert>5){
            throw new PersistenceException(PersistenceException.ExceptionType.InputMissmatch, "Nur Zahlen von 1-5 sind erlaubt!");
        }
        System.out.println("Gib eine Strafe von 1-5 ein");
        int strafe = sc1.nextInt();
        if (strafe<1 || strafe>5){
            throw new PersistenceException(PersistenceException.ExceptionType.InputMissmatch, "Nur Zahlen von 1-5 sind erlaubt!");
        }
        System.out.println("Gib einen Aufwand von 1-5 ein");
        int aufwand = sc1.nextInt();
        if (aufwand<1 || aufwand>5){
            throw new PersistenceException(PersistenceException.ExceptionType.InputMissmatch, "Nur Zahlen von 1-5 sind erlaubt!");
        }
        System.out.println("Gib ein Risiko von 1-5 ein");
        int risiko = sc1.nextInt();
        if (risiko<1 || risiko>5){
            throw new PersistenceException(PersistenceException.ExceptionType.InputMissmatch, "Nur Zahlen von 1-5 sind erlaubt!");
        }
        System.out.println("Gib das Projekt ein");
        String pr = sc1.next();
        UserStories eins = new UserStories(beschreibung, anforderungskriterien, mehrwert, strafe, aufwand, risiko, pr);
        System.out.println("Deine UserStory mit der ID " + eins.getID() + " wurde erstellt");
        con.addUserStory(eins);


    }

    public static void store(Container con) throws PersistenceException {
        con.store();
    }

    public static void load(Container con) throws PersistenceException{
        con.load();
    }

    public static void search(String name, Container con){
        List<UserStories> liste = con.getCurrentList();
        String nn = name.substring(1, name.lastIndexOf(")"));
        for(UserStories a: liste){
            if (a.getProjektZuordnung().equals(nn)){
                //System.out.println("Projektordner " + nn + ": " + a.getID() + " hat Prioritätslevel: "+ a.getProritätslevel());
                System.out.println("UserStory Nr."+ a.getID() + ", Titel: "+ a.getBeschreibung() + ", Projekt " + a.getProjektZuordnung());
            }

        }
    }




    public static void help(){
        System.out.println("Befehle :");
        System.out.println("enter: legt eine User Story an");
        System.out.println("store: speichert die User Story persistent");
        System.out.println("load: lädt eine User Story aus dem Datenträger");
        System.out.println("dump: sortierte Ausgabe des User Storys nach priorisierung");
        System.out.println("search (Projektname): sucht eine spezifische User Story raus");
        System.out.println("exit: verlassen der Anwendung");
    }
    public static void dump(Container con) {
        List<UserStories> liste = con.getCurrentList();
        Stream<UserStories> stream = liste.stream();
        // SortedSet<UserStory> gg = new TreeSet<>();
        Stream<UserStories> ss = stream.sorted( UserStories::compareTo);

        System.out.printf("%-10s %-30s %-30s %-50s %-50s", "ID", "Projektname", "Prioritätslevel", "Beschreibung", "Akzeptanzkriterium");
        System.out.println();
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        for (UserStories a: ss.toList()) {
            System.out.format("%-10d %-30s %-30s %-50s %-50s", a.getID(), a.getProjektZuordnung(), a.getPrioritaetslevel(), a.getBeschreibung(), a.getAkzeptanzkriterium());
            System.out.println();
        }

    }

}
