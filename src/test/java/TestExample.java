import com.github.artbits.picasso.Picasso;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.File;

public final class TestExample {


    @Test
    public void test_cp_file() {
        Picasso picasso = Picasso.init(new File("material/cp-bg.png"))
                .addImage("https://avatars.githubusercontent.com/u/109500550?v=4", o -> {
                    o.x = 85;
                    o.y = 84;
                    o.width = 86;
                    o.height = 86;
                    o.radius = 86;
                })
                .addImage(new File("material/cp-frame.png"), o -> {
                    o.x = 85;
                    o.y = 75;
                })
                .addText("Microsoft", o -> {
                    o.x = 85;
                    o.y = 165;
                    o.color = Color.magenta;
                    o.font = new Font("consolas", Font.ITALIC, 16);
                })
                .addImage("https://avatars.githubusercontent.com/u/14957082?v=4", o -> {
                    o.x = 590;
                    o.y = 84;
                    o.width = 86;
                    o.height = 86;
                    o.radius = 86;
                })
                .addImage(new File("material/cp-frame.png"), o -> {
                    o.x = 590;
                    o.y = 75;
                })
                .addText("OpenAI", o -> {
                    o.x = 590;
                    o.y = 165;
                    o.color = Color.magenta;
                    o.font = new Font("consolas", Font.ITALIC, 16);
                });

        File file = picasso.toFile("output/cp-" + System.currentTimeMillis() + ".png");
        System.out.println(file.getPath());
    }


    @Test
    public void test_compress() {
        String url = "https://avatars.githubusercontent.com/u/109500550?v=4";
        String path = "output/compress-" + System.currentTimeMillis() + ".png";
        Picasso.init(url).toFile(path, o -> o.quality = 0.1f);
    }

}
