import com.github.artbits.picasso.Picasso;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.File;

public final class TestExample {


    @Test
    public void test_cp_file() {
        File file1 = new File("material/cp-bg.png");
        File file2 = new File("material/cp-frame.png");
        String avatar1 = "https://avatars.githubusercontent.com/u/109500550?v=4";
        String avatar2 = "https://avatars.githubusercontent.com/u/14957082?v=4";
        String name1 = "Microsoft";
        String name2 = "OpenAI";
        Font font = new Font("consolas", Font.ITALIC, 16);

        Picasso picasso = Picasso.init(file1)
                .addImage(avatar1, o -> o.location(85, 84).width(86).height(86).radius(86))
                .addImage(file2, o -> o.location(85, 75))
                .addText(name1, o -> o.location(85, 175).font(font).color(Color.magenta))
                .addImage(avatar2, o -> o.location(590, 84).width(86).height(86).radius(86))
                .addImage(file2, o -> o.location(590, 75))
                .addText(name2, o -> o.location(590, 175).font(font).color(Color.magenta));

        File file = picasso.toFile("output/cp-" + System.currentTimeMillis() + ".png");
        System.out.println(file.getPath());
    }


    @Test
    public void test_compress() {
        String url = "https://avatars.githubusercontent.com/u/109500550?v=4";
        String path = "output/compress-" + System.currentTimeMillis() + ".png";
        Picasso.init(url).toFile(path, o -> o.quality(0.1f));
    }

}
