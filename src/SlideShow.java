import java.io.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class SlideShow {

    static Slide[] slides;

    public static int totalInterestScore() {
        int sum = 0;

        for (int i = 0; i < slides.length - 1; i++) {
            sum += slides[i].interestScore(slides[i+1]);
        }

        return sum;
    }

    public static final String filename = "a_example";

    public static void main(String[] args) throws IOException {
        //initialization of photos
        BufferedReader fileReader = new BufferedReader(new FileReader(new File("data/" + filename + ".txt")));
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

            photos[i] = new Photo(orient, tags, i + 1);
        }

        //actual code stuff
        int horizontal = 0;
        for (Photo photo : photos){
            if (photo.orient.equals(Orientation.HORIZONTAL)) {
                horizontal += 1;
            }
        }
        int vertical = photos.length - horizontal;
        int slideLength = horizontal + (vertical / 2);

        slides = new Slide[slideLength];
        for (int i = 0; i < slideLength; i++) {
            Slide slide = new Slide(Orientation.HORIZONTAL, photos[i], null, photos[i].tags);
            slides[i] = slide;
        }

        //solution
        int maxIterations = slides.length * 4;
        int currentInterest = totalInterestScore();
        for (int i = 0; i < maxIterations; i++) {
            swap();
            if(totalInterestScore() < currentInterest) {
                reverseSwap();
            }
        }
        writeSolution();
    }

    static int index1;
    static int index2;

    public static void swap() {
        index1 = (int) (Math.random() * slides.length);
        index2 = (int) (Math.random() * slides.length);

        Slide temp = slides[index1];
        slides[index1] = slides[index2];
        slides[index2] = slides[index1];
    }

    public static void reverseSwap() {
        Slide temp = slides[index1];
        slides[index1] = slides[index2];
        slides[index2] = slides[index1];
    }

    //IO stuff
    public static void writeSolution() throws IOException {
        File file = new File("solutions/" + filename + "_sol" + ".txt");
        FileWriter writer = new FileWriter(file);

        writer.write(slides.length + "\n");
        for (int i = 0; i < slides.length; i++) {
            if (slides[i].orient.equals(Orientation.HORIZONTAL)) {
                writer.write(slides[i].photo1.id + "\n");
            } else {
                writer.write(slides[i].photo1.id + " " + slides[i].photo2.id + "\n");
            }
        }
        writer.flush();
    }
}
