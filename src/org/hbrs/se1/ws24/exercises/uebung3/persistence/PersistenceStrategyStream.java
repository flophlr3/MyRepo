package org.hbrs.se1.ws24.exercises.uebung3.persistence;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

public class PersistenceStrategyStream<E> implements PersistenceStrategy<E> {

    // URL of file, in which the objects are stored
    private String location = "D:/Java/SoftwareEngineering/SoftwareEngineering/test/Test.txt";
    private ObjectInputStream ois = null;
    private FileInputStream fis = null;

    private ObjectOutputStream oos = null;
    private FileOutputStream fos = null;
    private List<E> liste = null;

    // Backdoor method used only for testing purposes, if the location should be
    // changed in a Unit-Test
    // Example: Location is a directory (Streams do not like directories, so try
    // this out ;-)!
    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    /**
     * Method for saving a list of Member-objects to a disk (HDD)
     * Look-up in Google for further help!
     */
    public void save(List<E> member) throws PersistenceException {
        try {
            fos = new FileOutputStream(location);
            oos = new ObjectOutputStream(fos);

            oos.writeObject(member);

            if (oos != null)
                oos.close();
            if (fos != null)
                fos.close();
        } catch (Exception e) {
            throw new PersistenceException(PersistenceException.ExceptionType.SavingFailed,
                    "Fehler beim Speichern der Datei");
        }
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    /**
     * Method for loading a list of Member-objects from a disk (HDD)
     * Some coding examples come for free :-)
     * Take also a look at the import statements above ;-!
     */
    public List<E> load() throws PersistenceException {
        try {
            fis = new FileInputStream(location);
            // Tipp: Use a directory (ends with "/") to implement a negative test case ;-)
            ois = new ObjectInputStream(fis);

            Object obj = ois.readObject();

            if (obj instanceof List<?>) {
                liste = (List) obj;
                return liste;
            }
            // and finally close the streams

            if (ois != null)
                ois.close();
            if (fis != null)
                fis.close();
        } catch (Exception e) {
            throw new PersistenceException(PersistenceException.ExceptionType.LoadingFailed,
                    "Fehler beim Laden der Datei");
        }

        return null;
    }
}
