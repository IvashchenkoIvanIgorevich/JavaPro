public class Employee {

    public enum Position {
        ENGINEER("Engineer"),
        QA("Quality Assurance"),
        PM("Project Manager"),
        BA("Business Analytics");

        private final String display;

        Position(String display) {
            this.display = display;
        }

        public String getDisplay() {
            return display;
        }
    }

    private final String name;
    private final String lastName;
    private final String surname;
    private final Position position;
    private final String email;
    private final String phoneNumber;
    private final int age;

    public Employee(String name, String lastName, String surname, Position position, String email, String phoneNumber,
                    int age) {
        this.name = name;
        this.lastName = lastName;
        this.surname = surname;
        this.position = position;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getSurname() {
        return surname;
    }

    public Position getPosition() {
        return position;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public int getAge() {
        return age;
    }

    public String getFullName() {
        return String.format("Last name: %s, Name: %s, Surname: %s", lastName, name, surname);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", surname='" + surname + '\'' +
                ", position=" + position +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", age=" + age +
                '}';
    }
}
