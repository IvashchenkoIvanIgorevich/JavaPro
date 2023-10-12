package homework.collection.phonebook;

public class Note {

    private final String name;
    private final String phoneNumber;

    public Note(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Note{" +
                "name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
