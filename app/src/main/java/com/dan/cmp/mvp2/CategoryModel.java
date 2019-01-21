package com.dan.cmp.mvp2;

public class CategoryModel {

    String word, translate, language, cardTheme, key;

    public CategoryModel(){

    }

    public CategoryModel(String word, String translate, String language, String cardTheme, String key) {
        this.word = word;
        this.translate = translate;
        this.language = language;
        this.cardTheme = cardTheme;
        this.key = key;
    }
}
