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

    public static final String filename = "";

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


        Slide[] photoSlides = new Slide[slideLength];
        for (int i = 0; i < slideLength; i++) {
            Slide slide = new Slide(Orientation.HORIZONTAL, photos[i], null, photos[i].tags);
            photoSlides[i] = slide;
        }
        slides = new Slide[slideLength];
        slides[0] = photoSlides[0];

        int threshE = (int) Math.ceil(slideLength / Math.exp(1));
        //solution
        main:
        for (int i = 0; i < slideLength - 1; i++) {
            int threshIndex = threshE + i;
            if (threshIndex > slideLength) {
                threshIndex = slideLength;
            }

            Slide thresh = photoSlides[i + 1];
            int threshScore = photoSlides[i].interestScore(thresh);
            for (int j = i + 1; j < threshIndex; j++) {
                if (photoSlides[i].interestScore(photoSlides[j + 1]) > threshScore) {
                    thresh = photoSlides[j + 1];
                    threshScore = photoSlides[j].interestScore(photoSlides[j + 1]);
                }
            }

            for (int j = threshIndex; j < slideLength; j++) {
                if (photoSlides[i].interestScore(photoSlides[j + 1]) >= threshScore) {
                    slides[i + 1] = photoSlides[j + 1];
                    continue main;
                }
            }
            slides[i + 1] = thresh;
        }
        writeSolution();
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
