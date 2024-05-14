package models.condecorados;

public class Condecorado {

    private String firstName;
    private String lastName;
    private String rank;
    private String gradeMerit;
    private String award;

    public Condecorado(String firstName, String lastName, String rank, String gradeMerit, String award) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.rank = rank;
        this.gradeMerit = gradeMerit;
        this.award = award;
    }

    public Condecorado() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getGradeMerit() {
        return gradeMerit;
    }

    public void setGradeMerit(String gradeMerit) {
        this.gradeMerit = gradeMerit;
    }

    public String getAward() {
        return award;
    }

    public void setAward(String award) {
        this.award = award;
    }

    @Override
    public String toString() {
        return "Condecorados{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", rank='" + rank + '\'' +
                ", gradeMerit='" + gradeMerit + '\'' +
                ", award='" + award + '\'' +
                '}';
    }
}
