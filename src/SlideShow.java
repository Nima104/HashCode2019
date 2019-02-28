import java.io.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class SlideShow {

    Slide[] slides;

    public SlideShow() {

    }

    public int totalInterestScore() {
        int sum = 0;

        for (int i = 0; i < slides.length - 1; i++) {
            sum += slides[i].interestScore(slides[i+1]);
        }

        return sum;
    }

    public static void main(String[] args) throws IOException {
        //initialization of photos
        String filename = "";
        BufferedReader fileReader = new BufferedReader(new FileReader(new File(filename)));
        Photo[] photos = new Photo[Integer.parseInt(fileReader.readLine())];

        String line;
        Orientation orient; Set<String> tags;
        for (int i = 0; i < photos.length; i++) {
            line = fileReader.readLine();
            String[] info = line.split("\\s+");

            if (info[0].equals("H")) {
                orient = Orientation.HORIZONTAL;
            } else {
                orient = Orientation.VERTICAL;
            }

            tags = new HashSet<>(Arrays.asList(info).subList(2, info.length));

            photos[i] = new Photo(orient, tags);
        }


        //actual code stuff
    }
}
