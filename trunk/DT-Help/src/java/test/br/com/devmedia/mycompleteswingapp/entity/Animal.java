package test.br.com.devmedia.mycompleteswingapp.entity;

public class Animal implements java.io.Serializable {

    private Integer id;
    private Customers owner;
    private String name;
    private String breed;
    private int age;
    private AnimalType type;

    public Animal(Integer id, Customers owner, String name, String breed, int age, AnimalType type) {
        this.id = id;
        this.owner = owner;
        this.name = name;
        this.breed = breed;
        this.age = age;
        this.type = type;
    }

    public Animal() {
    }

    public Animal(Integer id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Customers getOwner() {
        return owner;
    }

    public void setOwner(Customers owner) {
        this.owner = owner;
    }

    public AnimalType getType() {
        return type;
    }

    public void setType(AnimalType type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Animal other = (Animal) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

}
