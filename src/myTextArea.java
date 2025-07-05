import javax.swing.*;
import java.awt.*;

public class myTextArea {

    JTextArea textArea;
    JScrollPane scroll;
    JColorChooser color;
    boolean textWrap = false;

    myTextArea(){

        textArea = new JTextArea();
        textArea.setBackground(Color.white);
        textArea.setForeground(Color.BLACK);
        textArea.setFont(new Font("Serif", Font.PLAIN, 14));
        textArea.getCaret().setBlinkRate(500);
        textArea.setLineWrap(false);
        textArea.setWrapStyleWord(false);

        scroll = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setBorder(BorderFactory.createEmptyBorder());
    }

    public void clear(){
        textArea.setText("");
    }

    public void createFont(int fontSize){
        textArea.setFont(new Font(textArea.getFont().getFontName(), Font.PLAIN, fontSize));
    }

    public void createFormat(String fontFormat){
        textArea.setFont(new Font(fontFormat, Font.PLAIN, textArea.getFont().getSize()));
    }

    public void createBg(){

        color = new JColorChooser();
        Color color = JColorChooser.showDialog(null, "Color Chooser", Color.white);
        textArea.setBackground(color);
    }

    public void createText(){

        color = new JColorChooser();
        Color color = JColorChooser.showDialog(null, "Color Chooser", Color.black);
        textArea.setForeground(color);
    }

    public void createWrap(){

        if(textWrap == false){
            textWrap = true;
            textArea.setLineWrap(true);
            textArea.setWrapStyleWord(true);
        }
        else{
            textWrap = false;
            textArea.setLineWrap(false);
            textArea.setWrapStyleWord(false);
        }
    }
}
