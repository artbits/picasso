/**
 * Copyright 2024 Zhang Guanhu
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

import java.awt.*;

public final class Options {

    boolean coordinates;
    int x;
    int y;
    int width;
    int height;
    int radius;
    float scale;
    float alpha;
    float quality;
    Color color;
    Font font;


    Options() {

    }


    public Options coordinates(boolean coordinates) {
        this.coordinates = coordinates;
        return this;
    }


    public Options location(int x, int y) {
        this.x = x;
        this.y = y;
        return this;
    }


    public Options width(int width) {
        if (width < 0) {
            throw new RuntimeException("Parameter error!   width ∈ [0, +∞)");
        }
        this.width = width;
        return this;
    }


    public Options height(int height) {
        if (height < 0) {
            throw new RuntimeException("Parameter error!   height ∈ [0, +∞)");
        }
        this.height = height;
        return this;
    }


    public Options radius(int radius) {
        if (radius < 0) {
            throw new RuntimeException("Parameter error!   radius ∈ [0, +∞)");
        }
        this.radius = radius;
        return this;
    }


    public Options scale(float scale) {
        if (scale < 0) {
            throw new RuntimeException("Parameter error!   scale ∈ [0, +∞)");
        }
        this.scale = scale;
        return this;
    }


    public Options alpha(float alpha) {
        if (alpha < 0 || alpha > 1) {
            throw new RuntimeException("Parameter error!   alpha ∈ [0, 1]");
        }
        this.alpha = alpha;
        return this;
    }


    public Options quality(float quality) {
        if (quality < 0 || quality > 1) {
            throw new RuntimeException("Parameter error!   quality ∈ [0, 1]");
        }
        this.quality = quality;
        return this;
    }


    public Options color(Color color) {
        this.color = color;
        return this;
    }


    public Options font(String name, int style, int size) {
        this.font = new Font(name, style, size);
        return this;
    }


    public Options font(Font font) {
        this.font = font;
        return this;
    }

}
