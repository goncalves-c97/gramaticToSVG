package com.gramatictosvg;

import java.util.Random;

public class SvgManager {

    private static int totalWidth = 1000;
    private static int totalHeigth = 1000;
    
    private static final String docHtmlTag = "<!DOCTYPE html>";
    private static final String htmlOpeningTag = "<html>";
    private static final String bodyOpeningTag = "<body>";
    private static final String htmlClosingTag = "</html>";
    private static final String bodyClosingTag = "</body>";
    
    private static final String svgOpeningTag = "<svg xmlns=\"http://www.w3.org/2000/svg\" height=\"" + totalWidth + "\" width=\"" + totalHeigth + "\">";
    private static final String svgClosingTag = "</svg>";

    
    public static String ReturnSvgHtmlTag(String outputInstructions) {
        int x1 = totalWidth / 2;
        int y1 = totalHeigth / 2;
        int x2 = x1;
        int y2 = y1;
        int red = 255;
        int green = 0;
        int blue = 0;
        int strokeWidth = 2;
        char direction = 'N';
        int stepLength = 20;
        
        Random rand = new Random();
        
        String svgOutput = docHtmlTag + "\n";
        svgOutput += htmlOpeningTag + "\n";
        svgOutput += bodyOpeningTag + "\n";
        svgOutput += svgOpeningTag + "\n";

        for (Character c : outputInstructions.toCharArray()) {
            if (c == '-' || c == '+') {
                direction = GetNextDirectionBySignal(c, direction);
            }
            
            //stepLength = rand.nextInt(20);
            red = rand.nextInt(255);
            green = rand.nextInt(255);
            blue = rand.nextInt(255);
            
            switch (direction) {
                case 'N':
                    y2 = y1 + stepLength;
                    break;
                case 'E':
                    x2 = x1 + stepLength;
                    break;
                case 'S':
                    y2 = y1 - stepLength;
                    break;
                case 'W':
                    x2 = x1 - stepLength;
                    break;
            }
            
            //svgOutput += "<line x1 = \"" + x1 + "\" y1 = \"" + y1 + "\" x2 = \"" + x2 + "\" y2 = \"" + y2 + "\" style = \"stroke:rgb(" + red + "," + green + "," + blue + ");stroke-width:" + strokeWidth + "\" />\n";
            svgOutput += "<line x1 = \"" + x1 + "\" y1 = \"" + y1 + "\" x2 = \"" + x2 + "\" y2 = \"" + y2 + "\" style = \"stroke:rgb(" + red + "," + green + "," + blue + ");stroke-width:" + strokeWidth + "\" />\n";
            //svgOutput += "<line x1 = \"" + x1 + "\" y1 = \"" + y1 + "\" x2 = \"" + x2 + "\" y2 = \"" + y2 + "\" />\n";
            x1 = x2;
            y1 = y2;
        }
        
        svgOutput += svgClosingTag;
        
        svgOutput += bodyClosingTag + "\n";
        svgOutput += htmlClosingTag + "\n";
        
        return svgOutput;
    }

    private static Character GetNextDirectionBySignal(Character signal, Character direction) {
        char[] directions = {'N', 'E', 'S', 'W'};
        int actualPosition = 0;

        switch (direction) {
            case 'N':
                actualPosition = 0;
                break;
            case 'E':
                actualPosition = 1;
                break;
            case 'S':
                actualPosition = 2;
                break;
            case 'W':
                actualPosition = 3;
                break;
        }

        int clockwise = 0;

        if (signal == '+') {
            clockwise = 1;
        } else if (signal == '-') {
            clockwise = -1;
        }

        if (actualPosition + clockwise > 3) {
            return 'N';
        }

        if (actualPosition + clockwise < 0) {
            return 'W';
        }

        return directions[actualPosition + clockwise];
    }
}
