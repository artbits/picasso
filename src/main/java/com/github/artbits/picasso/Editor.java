/**
 * Copyright 2023 Zhang Guanhu
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.artbits.picasso;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Iterator;

final class Editor {


    static BufferedImage handle(BufferedImage bufferedImage, Options options) {
        if (options.width != 0 || options.height != 0) {
            bufferedImage = Editor.cut(bufferedImage, options.width, options.height);
        }
        if (options.radius != 0) {
            bufferedImage = Editor.radius(bufferedImage, options.radius);
        }
        if (options.scale != 0) {
            bufferedImage = Editor.scale(bufferedImage, options.scale);
        }
        if (options.alpha != 0) {
            bufferedImage = Editor.alpha(bufferedImage, options.alpha);
        }
        if (options.quality != 0) {
            bufferedImage = Editor.compress(bufferedImage, options.quality);
        }
        return bufferedImage;
    }


    static BufferedImage cut(BufferedImage bufferedImage, int width, int height) {
        if (width < 0) {
            throw new RuntimeException("Parameter error!   width ∈ [0, +∞)");
        }
        if (height < 0) {
            throw new RuntimeException("Parameter error!   height ∈ [0, +∞)");
        }
        width = (width > 0) ? width : bufferedImage.getWidth();
        height = (height > 0) ? height : bufferedImage.getHeight();
        BufferedImage image = new BufferedImage(width, height, bufferedImage.getType());
        Graphics2D graphics = image.createGraphics();
        graphics.drawImage(bufferedImage, 0, 0, width, height, null);
        graphics.dispose();
        return image;
    }


    static BufferedImage radius(BufferedImage bufferedImage, int radius) {
        if (radius < 0) {
            throw new RuntimeException("Parameter error!   radius ∈ [0, +∞)");
        }
        BufferedImage image = new BufferedImage(bufferedImage.getWidth(), bufferedImage.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = image.createGraphics();
        RoundRectangle2D rectangle = new RoundRectangle2D.Float(0, 0, image.getWidth(), image.getHeight(), radius, radius);
        graphics.setClip(rectangle);
        graphics.drawImage(bufferedImage, 0, 0, null);
        graphics.dispose();
        return image;
    }


    static BufferedImage scale(BufferedImage bufferedImage, float scale) {
        if (scale < 0) {
            throw new RuntimeException("Parameter error!   scale ∈ [0, +∞)");
        }
        int width = (int) (bufferedImage.getWidth() * scale);
        int height = (int) (bufferedImage.getHeight() * scale);
        BufferedImage image = new BufferedImage(width, height, bufferedImage.getType());
        Graphics2D graphics = image.createGraphics();
        graphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics.drawImage(bufferedImage, 0, 0, width, height, null);
        graphics.dispose();
        return image;
    }


    static BufferedImage alpha(BufferedImage bufferedImage, float alpha) {
        if (alpha < 0 || alpha > 1) {
            throw new RuntimeException("Parameter error!   alpha ∈ [0, 1]");
        }
        BufferedImage image = new BufferedImage(bufferedImage.getWidth(), bufferedImage.getHeight(), bufferedImage.getType());
        Graphics2D graphics = image.createGraphics();
        graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        graphics.drawImage(bufferedImage, 0, 0, bufferedImage.getWidth(), bufferedImage.getHeight(), null);
        graphics.dispose();
        return image;
    }


    static BufferedImage compress(BufferedImage bufferedImage, float quality) {
        if (quality < 0 || quality > 1) {
            throw new RuntimeException("Parameter error!   quality ∈ [0, 1]");
        }
        try (ByteArrayOutputStream stream = new ByteArrayOutputStream()) {
            Iterator<ImageWriter> iterator = ImageIO.getImageWritersByFormatName("JPEG");
            if (iterator.hasNext()) {
                ImageWriter writer = iterator.next();
                ImageWriteParam param = writer.getDefaultWriteParam();
                param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
                param.setCompressionQuality(quality);
                ImageOutputStream ios = ImageIO.createImageOutputStream(stream);
                writer.setOutput(ios);
                writer.write(null, new IIOImage(bufferedImage, null, null), param);
                ios.close();
                writer.dispose();
                ByteArrayInputStream is = new ByteArrayInputStream(stream.toByteArray());
                BufferedImage image = ImageIO.read(is);
                is.close();
                return image;
            }
            return bufferedImage;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    static FontMetrics getFontMetrics(Font font) {
        Graphics2D graphics = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB).createGraphics();
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        FontMetrics metrics = graphics.getFontMetrics(font);
        graphics.dispose();
        return metrics;
    }


}