package dimitrijestefan.mosis.elfak;


import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class MyPlace {




    public String name;
    public String description;
    public String longitude;
    public String latitude;

    @Exclude
    String key;
    public MyPlace(){}

    public MyPlace(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public MyPlace(String name){
        this(name,"");
    }

    @Override
    public String toString(){
        return this.name;
    }


}
