
import com.gramatictosvg.SvgManager;
import java.awt.Desktop;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class main {

    public static void main(String[] args) throws IOException {

        ArrayList<String> words = new ArrayList<>();

        words.add("E");
        words.add("+");
        words.add("-");

        String gramatic = "E+E+E+E+EE--E+EEE+E";

        ArrayList<String> rules = new ArrayList<>();

        rules.add("E-EE-EEE-EE-E+EE++E+++EEE--EE--E");

        String output = "";

        for (int i = 0; i < 12; i++) {
            output += gramatic.replaceAll("E", rules.get(0));
        }
        
        String svgFileContent = SvgManager.ReturnSvgHtmlTag(output);
        
        StringSelection selection = new StringSelection(svgFileContent);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(selection, selection);
        File file = new File("SVGFile.svg");
        
        FileWriter fw = new FileWriter("SVGFile.txt");
        fw.write(svgFileContent);
        fw.close();
        
        FileWriter fw2 = new FileWriter("SVGFile.svg");
        fw2.write(svgFileContent);
        fw2.close();
        
        
        
        Desktop.getDesktop().browse(file.toURI());
    }

}
