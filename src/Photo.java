import java.util.Set;

public class Photo {

    Orientation orient;
    Set<String> tags;

    public Photo(Orientation orient, Set<String> tags) {
        this.orient = orient;
        this.tags = tags;
    }
}
