package setup;

public class EmployeeModel {
    String firstname;
    String lastname;
    String username;
    String password;

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public EmployeeModel(String firstName, String lastName, String username, String password){
        this.firstname=firstName;
        this.lastname=lastName;
        this.username=username;
        this.password=password;
    }
    public EmployeeModel(){

    }
}
