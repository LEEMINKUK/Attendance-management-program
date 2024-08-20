package com.ohgiraffers.run;

import com.ohgiraffers.control.MainMenu;

import static com.ohgiraffers.view.AsciiArtPrinter.startLogo;

public class KimsFamilyWithLeeAMP {

    public static void main(String[] args) {
        startLogo();
        MainMenu start = new MainMenu();
        start.start();
    }
}
