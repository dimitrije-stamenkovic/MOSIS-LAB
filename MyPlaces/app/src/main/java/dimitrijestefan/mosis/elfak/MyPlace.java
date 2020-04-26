package dimitrijestefan.mosis.elfak;

import androidx.annotation.NonNull;

public class MyPlace {


    String name;
    String description;


    public MyPlace(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString(){
        return this.name;
    }


}
