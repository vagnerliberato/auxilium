
package test.br.com.devmedia.mycompleteswingapp.entity;

public enum AnimalType {
    DOG("Dog"), CAT("Cat");
    private String description;
    
    AnimalType(String description){
        this.description = description;        
    }
    
    public String getDescription(){
        return description;
    }

}
