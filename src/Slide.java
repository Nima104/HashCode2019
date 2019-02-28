import java.util.HashSet;
import java.util.Set;

public class Slide {

    Orientation orient;
    Photo photo1;
    Photo photo2;
    Set<String> tags;

    public Slide(Orientation orient, Photo photo1, Photo photo2, Set<String> tags) {
        if (orient.equals(Orientation.VERTICAL) && photo2 != null) {
            throw new RuntimeException("Vertical photos only have 1 photo");
        }

        this.orient = orient;
        this.photo1 = photo1;
        this.photo2 = photo2;
        this.tags = tags;
    }

    public int interestScore(Slide other) {
        Set<String> tags1 = new HashSet<>(tags);
        tags1.retainAll(other.tags);

        Set<String> tags2 = new HashSet<>(tags);
        tags2.removeAll(other.tags);

        Set<String> tags3 = new HashSet<>(other.tags);
        tags3.removeAll(tags);

        return min(tags1.size(), tags2.size(), tags3.size());
    }

    private static int min(int... nums) {
        int min = nums[0];

        for (Integer n : nums) {
            if (n < min) {
                min = n;
            }
        }

        return min;
    }
}
