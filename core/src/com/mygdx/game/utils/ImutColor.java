package com.mygdx.game.utils;

import com.badlogic.gdx.graphics.Color;

public class ImutColor extends Color {
    public ImutColor(float r, float g, float b, float a) {
        this.r = clamp(r, 0, 1);
        this.g = clamp(g, 0, 1);
        this.b = clamp(b, 0, 1);
        this.a = clamp(a, 0, 1);

    }
    public ImutColor(float r, float g, float b) {
        this.r = clamp(r, 0, 1);
        this.g = clamp(g, 0, 1);
        this.b = clamp(b, 0, 1);
        this.a = 1;
    }

    public ImutColor withAlpha(float a){
        return new ImutColor(r, g, b, a);
    }

    @Override
    public ImutColor mul(Color color) {
        return new ImutColor(r * color.r, g * color.g, b * color.b, a * color.a);
    }

    @Override
    public ImutColor mul(float value) {
        return new ImutColor(r * value, g * value, b * value, a * value);
    }

    @Override
    public ImutColor add(Color color) {
        return new ImutColor(r + color.r, g + color.g, b + color.b, a + color.a);
    }

    @Override
    public ImutColor sub(Color color) {
        return new ImutColor(r - color.r, g - color.g, b - color.b, a - color.a);
    }

    @Override
    public ImutColor clamp() {
        return new ImutColor(clamp(r, 0, 1), clamp(g, 0, 1), clamp(b, 0, 1), clamp(a, 0, 1));
    }

    @Override
    public ImutColor add(float r, float g, float b, float a) {
        return new ImutColor(this.r + r, this.g + g, this.b + b, this.a + a);
    }

    @Override
    public ImutColor sub(float r, float g, float b, float a) {
        return new ImutColor(this.r - r, this.g - g, this.b - b, this.a - a);
    }

    @Override
    public Color mul(float r, float g, float b, float a) {
        return new Color(this.r * r, this.g * g, this.b * b, this.a * a);
    }

    @Override
    public ImutColor premultiplyAlpha() {
        return new ImutColor(r * a, g * a, b * a, a);
    }

    private static float clamp(float value, float min, float max) {
        return Math.max(min, Math.min(max, value));
    }

}
