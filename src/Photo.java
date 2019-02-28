import java.util.Set;

public class Photo {

    Orientation orient;
    Set<String> tags;
    int id;

    public Photo(Orientation orient, Set<String> tags, int id) {
        this.orient = orient;
        this.tags = tags;
        this.id = id;
    }
}
