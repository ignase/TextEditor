import java.awt.event.*;
import java.awt.*;

import javax.swing.*;
import javax.swing.text.*;

public class TextEditor {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        MarcoProcesadorTextos mimarco=new MarcoProcesadorTextos();
        mimarco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

class MarcoProcesadorTextos extends JFrame{


    public MarcoProcesadorTextos(){
        setBounds(400,200,650,520);
        LaminaProcesadorTextos milamina=new LaminaProcesadorTextos();
        add(milamina);

        setVisible(true);
    }
}

class LaminaProcesadorTextos extends JPanel{

    JTextPane miarea;
    JMenu fuente, estilo, tamagno;
    Font letras;
    JToolBar barra;

    public LaminaProcesadorTextos(){

        setLayout(new BorderLayout());
        JPanel laminamenu=new JPanel();
        JMenuBar mibarra=new JMenuBar();

        //---------------------------------------------------------
        fuente=new JMenu("Fuente");
        estilo=new JMenu("Estilo");
        tamagno=new JMenu("Tamaño");

        configura_menu("Arial","fuente","Arial",9,10,"");
        configura_menu("Courier","fuente","Courier",9,10,"");
        configura_menu("Verdana","fuente","Verdana",9,10,"");

        //--------------------------------------------------------

        configura_menu("Negrita","estilo","",Font.BOLD,1,"bin/graficos/negrita.gif");
        configura_menu("Cursiva","estilo","",Font.ITALIC,1,"bin/graficos/cursiva.gif");

        ButtonGroup tamagno_letra=new ButtonGroup();

        JRadioButtonMenuItem doce=new JRadioButtonMenuItem("12");
        JRadioButtonMenuItem dieciseis=new JRadioButtonMenuItem("16");
        JRadioButtonMenuItem veinte=new JRadioButtonMenuItem("20");
        JRadioButtonMenuItem veinticuatro=new JRadioButtonMenuItem("24");

        tamagno_letra.add(doce);
        tamagno_letra.add(dieciseis);
        tamagno_letra.add(veinte);
        tamagno_letra.add(veinticuatro);

        doce.addActionListener(new StyledEditorKit.FontSizeAction("cambia_tamaño", 12));
        dieciseis.addActionListener(new StyledEditorKit.FontSizeAction("cambia_tamaño", 16));
        veinte.addActionListener(new StyledEditorKit.FontSizeAction("cambia_tamaño", 20));
        veinticuatro.addActionListener(new StyledEditorKit.FontSizeAction("cambia_tamaño", 24));

        tamagno.add(doce);
        tamagno.add(dieciseis);
        tamagno.add(veinte);
        tamagno.add(veinticuatro);

        //-----------------------------------------------------------

        mibarra.add(fuente);
        mibarra.add(estilo);
        mibarra.add(tamagno);

        laminamenu.add(mibarra);
        add(laminamenu,BorderLayout.NORTH);

        miarea=new JTextPane();
        add(miarea,BorderLayout.CENTER);

        JPopupMenu emergente=new JPopupMenu();

        JMenuItem negritaE=new JMenuItem("Negrita");
        JMenuItem cursivaE=new JMenuItem("Cursiva");
        negritaE.addActionListener(new StyledEditorKit.BoldAction());
        cursivaE.addActionListener(new StyledEditorKit.ItalicAction());

        emergente.add(negritaE);
        emergente.add(cursivaE);

        miarea.setComponentPopupMenu(emergente);

        //-------------------------------------------------------------------------------------------------

        barra=new JToolBar();

        configura_barra("src/imagenes/negrita.png").addActionListener(new StyledEditorKit.BoldAction());
        configura_barra("src/imagenes/cursiva.png").addActionListener(new StyledEditorKit.ItalicAction());
        configura_barra("src/imagenes/subrayar.png").addActionListener(new StyledEditorKit.UnderlineAction());

        barra.addSeparator();

        configura_barra("src/imagenes/azul.jpg").addActionListener(new StyledEditorKit.ForegroundAction("Pone_azul", Color.BLUE));
        configura_barra("src/imagenes/rojo.jpg").addActionListener(new StyledEditorKit.ForegroundAction("Pone_rojo", Color.RED));
        configura_barra("src/imagenes/verde.jpg").addActionListener(new StyledEditorKit.ForegroundAction("Pone_verde", Color.GREEN));

        barra.addSeparator();

        configura_barra("src/imagenes/alineacion-izquierda.png").addActionListener(new StyledEditorKit.AlignmentAction("Izquierda", 0));
        configura_barra("src/imagenes/alinear-centro.png").addActionListener(new StyledEditorKit.AlignmentAction("Centrado", 1));
        configura_barra("src/imagenes/alineacion-derecha.png").addActionListener(new StyledEditorKit.AlignmentAction("Derecha", 2));
        configura_barra("src/imagenes/texto-justificado.png").addActionListener(new StyledEditorKit.AlignmentAction("Justificado", 3));

        barra.setOrientation(1);
        add(barra, BorderLayout.WEST);
    }

    public JButton configura_barra(String ruta){

        JButton boton=new JButton(new ImageIcon(ruta));
        barra.add(boton);
        return boton;
    }

    public void configura_menu(String rotulo, String menu, String tipo_letra, int estilos, int tam, String ruta_icono){

        JMenuItem elem_menu=new JMenuItem(rotulo,new ImageIcon(ruta_icono));

        if(menu=="fuente"){
            fuente.add(elem_menu);

            if(tipo_letra=="Arial"){
                elem_menu.addActionListener(new StyledEditorKit.FontFamilyAction("cambia_letra","Arial"));

            }else if(tipo_letra=="Courier"){
                elem_menu.addActionListener(new StyledEditorKit.FontFamilyAction("cambia_letra","Courier"));

            }else if(tipo_letra=="Verdana"){
                elem_menu.addActionListener(new StyledEditorKit.FontFamilyAction("cambia_letra","Verdana"));
            }

        }else if(menu=="estilo"){
            estilo.add(elem_menu);

            if(estilos==Font.BOLD){
                elem_menu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,InputEvent.CTRL_DOWN_MASK));
                elem_menu.addActionListener(new StyledEditorKit.BoldAction());

            }else if(estilos==Font.ITALIC){
                elem_menu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_K,InputEvent.CTRL_DOWN_MASK));
                elem_menu.addActionListener(new StyledEditorKit.ItalicAction());
            }

        }else if(menu=="tamaño"){
            tamagno.add(elem_menu);
            elem_menu.addActionListener(new StyledEditorKit.FontSizeAction("cambia_tamaño", tam));
        }
    }
}
