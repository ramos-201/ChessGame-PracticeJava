package com.andresramos.chessgamepracticejava.models;

import com.andresramos.chessgamepracticejava.utils.enums.ColorRectangle;

public class Rectangle {
    
    private final ColorRectangle colorRectangle;
    
    public Rectangle(ColorRectangle colorRectangle) {
        this.colorRectangle = colorRectangle;
    }

    public ColorRectangle getColorRectangle() {
        return colorRectangle;
    }
}
