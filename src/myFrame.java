import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class myFrame extends JFrame implements ActionListener {

    JMenuBar menuBar;
    JMenu menuFile, menuFormat, font, size, menucolor;
    JMenuItem exitItem, newItem, saveAsItem, saveItem, loadItem;
    JMenuItem wrap,size1, size2, size3, size4, size5;
    JMenuItem font1, font2, font3, font4;
    JMenuItem background, text;
    myTextArea field;
    JFileChooser file;

    myFrame(){
        createNew();
    }

    public void createNew(){

        menuBar = new JMenuBar();
        menuFile = new JMenu("File");
        menuFormat = new JMenu("Format");
        font = new JMenu("Font");
        size = new JMenu("Font Size");
        menucolor = new JMenu("Color");
        newItem = new JMenuItem("New");
        saveAsItem = new JMenuItem("SaveAs");
        saveItem = new JMenuItem("Save");
        loadItem = new JMenuItem("Load");
        exitItem = new JMenuItem("Exit");
        size1 = new JMenuItem("10");
        size2 = new JMenuItem("14");
        size3 = new JMenuItem("18");
        size4 = new JMenuItem("22");
        size5 = new JMenuItem("26");
        font1 = new JMenuItem("Serif");
        font2 = new JMenuItem("Monospaced");
        font3 = new JMenuItem("Consolas");
        font4 = new JMenuItem("Times New Roman");
        background = new JMenuItem("Background");
        text = new JMenuItem("Text");
        wrap = new JMenuItem("Text Wrap: Off");
        field = new myTextArea();

        newItem.addActionListener(this);
        loadItem.addActionListener(this);
        saveAsItem.addActionListener(this);
        saveItem.addActionListener(this);
        exitItem.addActionListener(this);
        font1.addActionListener(this);
        font2.addActionListener(this);
        font3.addActionListener(this);
        font4.addActionListener(this);
        size1.addActionListener(this);
        size2.addActionListener(this);
        size3.addActionListener(this);
        size4.addActionListener(this);
        size5.addActionListener(this);
        background.addActionListener(this);
        text.addActionListener(this);
        wrap.addActionListener(this);

        this.setTitle("Notepad");
        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        menuBar.setBackground(Color.lightGray);
        menuBar.add(menuFile);
        menuBar.add(menuFormat);
        menuBar.add(menucolor);

        newItem.setBackground(Color.lightGray);
        saveItem.setBackground(Color.lightGray);
        saveAsItem.setBackground(Color.lightGray);
        loadItem.setBackground(Color.lightGray);
        exitItem.setBackground(Color.lightGray);
        font.setBackground(Color.lightGray);
        wrap.setBackground(Color.lightGray);
        size.setBackground(Color.lightGray);
        font1.setBackground(Color.lightGray);
        font2.setBackground(Color.lightGray);
        font3.setBackground(Color.lightGray);
        font4.setBackground(Color.lightGray);
        size1.setBackground(Color.lightGray);
        size2.setBackground(Color.lightGray);
        size3.setBackground(Color.lightGray);
        size4.setBackground(Color.lightGray);
        size5.setBackground(Color.lightGray);
        background.setBackground(Color.lightGray);
        text.setBackground(Color.lightGray);

        menuFile.add(newItem);
        menuFile.add(saveItem);
        menuFile.add(saveAsItem);
        menuFile.add(loadItem);
        menuFile.add(exitItem);

        menuFormat.add(wrap);
        menuFormat.add(font);
        menuFormat.add(size);

        menucolor.add(background);
        menucolor.add(text);

        font.add(font1);
        font.add(font2);
        font.add(font3);
        font.add(font4);

        size.add(size1);
        size.add(size2);
        size.add(size3);
        size.add(size4);
        size.add(size5);

        this.setJMenuBar(menuBar);
        this.add(field.scroll);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == newItem){
            field.clear();
            this.setTitle("Notepad");
        }

        if(e.getSource() == loadItem){
            file = new JFileChooser();
            file.setCurrentDirectory(new File("."));

            int response = file.showOpenDialog(this);

            if(response ==  file.APPROVE_OPTION){

                String filepath = file.getSelectedFile().getAbsolutePath();
                String fileName = file.getSelectedFile().getName();

                try(BufferedReader reader = new BufferedReader(new FileReader(filepath))){

                    field.clear();
                    this.setTitle(fileName);
                    String line;

                    while((line = reader.readLine()) != null){

                        field.textArea.append(line + "\n");
                    }

                    reader.close();
                }

                catch (IOException a){
                    JOptionPane.showMessageDialog(null, "Something went wrong!", "error!", JOptionPane.ERROR_MESSAGE);
                }
            }
        }

        if(e.getSource() == saveItem){

            String filePath = "C:\\Users\\GSage\\Documents\\";
            String fileName = "Notepad.txt";
            File file = new File(filePath + fileName);

                if(file.exists()){

                    int result = JOptionPane.showConfirmDialog(null,
                            "File already exists! Want to Overwrite?",
                            "Warning!",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.WARNING_MESSAGE);

                    if(result != JOptionPane.YES_OPTION){
                        return;
                    }
                    else{

                        try(FileWriter writer = new FileWriter(file)){

                            writer.write(field.textArea.getText());
                            writer.close();
                        }

                        catch (IOException a){
                            JOptionPane.showMessageDialog(null, "Something went wrong!", "error!", JOptionPane.ERROR_MESSAGE);
                        }

                    }

                }
                else{

                    try(FileWriter writer = new FileWriter(file)){

                        writer.write(field.textArea.getText());
                        writer.close();
                    }
                    catch (IOException a){
                        JOptionPane.showMessageDialog(null, "Something went wrong!", "error!", JOptionPane.ERROR_MESSAGE);
                    }
                }
        }

        if(e.getSource() == saveAsItem){

            file = new JFileChooser();
            file.setCurrentDirectory(new File("."));

            int response = file.showSaveDialog(this);

            if(response ==  file.APPROVE_OPTION){

                File filepath = new File(file.getSelectedFile().getAbsolutePath());

                try(FileWriter writer = new FileWriter(filepath)){

                    writer.write(field.textArea.getText());
                    writer.close();
                }

                catch (IOException a){
                    JOptionPane.showMessageDialog(null, "Something went wrong!", "error!", JOptionPane.ERROR_MESSAGE);
                }
            }

        }

        if(e.getSource() == exitItem){
            System.exit(0);
        }

        if(e.getSource() == size1){
            field.createFont(10);
        }

        if(e.getSource() == size2){
            field.createFont(14);
        }

        if(e.getSource() == size3){
            field.createFont(18);
        }

        if(e.getSource() == size4){
            field.createFont(22);
        }

        if(e.getSource() == size5){
            field.createFont(26);
        }

        if(e.getSource() == font1){
            field.createFormat("Serif");
        }

        if(e.getSource() == font2){
            field.createFormat("Monospaced");
        }

        if(e.getSource() == font3){
            field.createFormat("Consolas");
        }

        if(e.getSource() == font4){
            field.createFormat("Times New Roman");
        }

        if(e.getSource() == background){
            field.createBg();
        }

        if(e.getSource() == text){
            field.createText();
        }

        if(e.getSource() == wrap){

            if(wrap.getText() == "Text Wrap: Off"){
                field.createWrap();
                wrap.setText("Text Wrap: On");
            }
            else if(wrap.getText() == "Text Wrap: On"){
                field.createWrap();
                wrap.setText("Text Wrap: Off");
            }
        }
    }
}