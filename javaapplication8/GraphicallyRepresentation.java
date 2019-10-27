package javaapplication8;

/**
 *
 * @author Hasib
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class GraphicallyRepresentation extends JFrame implements ActionListener {

    Container con;
    JButton b[][] = new JButton[9][9];
    JButton sb[] = new JButton[9];
    TextField t[] = new TextField[61];
    JMenuBar mbar;
    JMenu game, help, option;
    JMenuItem submit, exit, about, newgame;
    JCheckBoxMenuItem beginner, intermediate, expert;//six,nine,sixteen;
    ButtonGroup g1, g2;
    JFrame nf;
    int level = 0;
    int[][] cp = new int[9][9];
    int[][] ip = new int[9][9];
    boolean mode = true;
    String getnum = "";

    GraphicallyRepresentation(int z) {
        super("Play Sudoku .............(Created by farsim & hasib)");
        setSize(500, 500);
        setResizable(false);
        setLocationRelativeTo(this);
        //setLocationByPlatform(true);
        setLocation(getLocation());

        con = getContentPane();
        con.setLayout(new GridLayout(9, 9));

        level = z;
        Mylogic ob1 = new Mylogic();
        ob1.complet_puzzle();
        if (level == 0) {
            ob1.puzzle();
        } else if (level == 2) {
            ob1.puzzle2();
        } else if (level == 3) {
            ob1.puzzle3();
        }

        int c = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                b[i][j] = new JButton("" + ip[i][j]);
                b[i][j].setFont(new Font("ARIALBD", Font.BOLD, 20));
                b[i][j].setForeground(Color.BLUE);
                if (ip[i][j] == 0) {
                    //b[i][j]=new JButton("");
                    b[i][j].setText("");
                    b[i][j].setBackground(Color.WHITE);
                    b[i][j].addActionListener(this);

                }

                con.add(b[i][j]);

                if (i == 3 || i == 4 || i == 5 || j == 3 || j == 4 || j == 5) {
                    if (2 < i && i < 6 && 2 < j && j < 6) {
                        b[i][j].setBackground(Color.CYAN);
                        continue;
                    }
                    b[i][j].setBackground(Color.pink);

                } else {
                    b[i][j].setBackground(Color.CYAN);
                }
            }
        }

        mbar = new JMenuBar();
        setJMenuBar(mbar);

        game = new JMenu("Game");
        option = new JMenu("Option");
        help = new JMenu("Help");

        about = new JMenuItem("About");
        newgame = new JMenuItem("New Game");
        submit = new JMenuItem("Submit");
        exit = new JMenuItem("Exit");

        beginner = new JCheckBoxMenuItem("Begineer");
        beginner.addItemListener(
                new ItemListener() {

                    public void itemStateChanged(ItemEvent e) {
                        level = 0;
                        recall(0);
                    }
                });
        intermediate = new JCheckBoxMenuItem("Intermediate");
        intermediate.addItemListener(
                new ItemListener() {

                    public void itemStateChanged(ItemEvent e) {
                        level = 2;
                        recall(2);
                    }
                });
        expert = new JCheckBoxMenuItem("Expert");
        expert.addItemListener(
                new ItemListener() {

                    public void itemStateChanged(ItemEvent e) {
                        level = 3;
                        recall(3);
                    }
                });
        g1 = new ButtonGroup();
        g1.add(beginner);
        g1.add(intermediate);
        g1.add(expert);

        JCheckBoxMenuItem safemode = new JCheckBoxMenuItem("Safe Mode");
        safemode.addItemListener(
                new ItemListener() {

                    public void itemStateChanged(ItemEvent e) {
                        if (mode == true) {
                            mode = false;
                        } else {
                            mode = true;
                        }
                    }
                });
        /*six=new JCheckBoxMenuItem("6X6");
        nine=new JCheckBoxMenuItem("9X9");
        sixteen=new JCheckBoxMenuItem("16X16");
        g2=new ButtonGroup();
        g2.add(six);
        g2.add(nine);
        g2.add(sixteen);
         */
        submit.addActionListener(
                new ActionListener() {

                    public void actionPerformed(ActionEvent e) {
                        int r = 0;
                        try {
                            for (int i = 0; i < 9; i++) {
                                for (int j = 0; j < 9; j++) {
                                    if (cp[i][j] != Integer.parseInt(b[i][j].getText())) {
                                        r = 1;
                                        break;
                                    }
                                }
                            }

                            if (r == 0) {
                                JOptionPane.showMessageDialog(GraphicallyRepresentation.this, "You won the Game");
                            } //System.out.println("You won the Game");
                            else //System.out.println("You lose the Game");
                            {
                                JOptionPane.showMessageDialog(GraphicallyRepresentation.this, "You lose the Game");
                            }
                        } catch (NumberFormatException nume) {
                            JOptionPane.showMessageDialog(GraphicallyRepresentation.this, "Empty boxes");
                        }
                    }
                });
        exit.addActionListener(
                new ActionListener() {

                    public void actionPerformed(ActionEvent e) {
                        System.exit(0);
                    }
                });

        about.addActionListener(
                new ActionListener() {

                    public void actionPerformed(ActionEvent e) {
                        JOptionPane.showMessageDialog(GraphicallyRepresentation.this,
                                "FARSiM & HASiB JIgni Dost! SUST CSE 15batch PRODUCT....Our first project of java language ",
                                "About", JOptionPane.PLAIN_MESSAGE);
                    }
                });
        newgame.addActionListener(
                new ActionListener() {

                    public void actionPerformed(ActionEvent e) {
                        recall(level);
                    }
                });

        game.add(newgame);
        game.add(submit);
        game.addSeparator();
        game.add(exit);
        option.add(beginner);
        option.add(intermediate);
        option.add(expert);
        option.addSeparator();
        option.add(safemode);
        /*option.add(six);
        option.add(nine);
        option.add(sixteen);
         */
        mbar.add(game);
        mbar.add(option);
        mbar.add(help);
        mbar.add(about);


        show();

        //ob1.complet_puzzle();

        MyWindowAdapter mwa = new MyWindowAdapter();
        addWindowListener(mwa);

    }

    class Mylogic extends Logic {

        void complet_puzzle() {
            cp = save();

            /*for(int i=0;i<9;i++)
            {
            for(int j=0;j<9;j++)
            System.out.print(cp[i][j]+" ");
            System.out.println();
            }*/
        }

        void puzzle() {
            ip = hide();
            /*System.out.print("\n\n\n"+"nhiding puzzle :\n");
            for(int i=0;i<9;i++)
            {
            for(int j=0;j<9;j++)
            System.out.print(ip[i][j]+"	");
            System.out.println();
            }*/
        }

        void puzzle2() {
            ip = hide2();

        }

        void puzzle3() {
            ip = hide3();

        }
    }

    class MyWindowAdapter extends WindowAdapter {

        public void windowClosing(WindowEvent e) {
            System.exit(0);
        }
    }

    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (e.getSource() == b[i][j]) {
                    String s = selectnum();//JOptionPane.showInputDialog("enter your number");
                    //int c=Integer.parseInt(s);
                    //if(0<c && 10>c)
                    //{
                    b[i][j].setText(s);
                    //b[i][j].setBackground(Color.GRAY);
                    b[i][j].setFont(new Font("ARIALBD", Font.BOLD, 25));
                    if (mode == true) {
                        b[i][j].setForeground(Color.BLUE);
                    } else {
                        if (Integer.parseInt(b[i][j].getText()) != cp[i][j]) {
                            b[i][j].setForeground(Color.RED);
                        } else {
                            b[i][j].setForeground(Color.BLUE);
                        }
                    }
                    //}

                    break;
                }
            }
        }

    }

    String selectnum() {
        nf = new JFrame();
        nf.setSize(400, 100);
        nf.getContentPane().setLayout(new GridLayout(1, 9));
        nf.setLocationRelativeTo(this);

        for (int i = 0; i < 9; i++) {
            sb[i] = new JButton("" + i);
            nf.getContentPane().add(sb[i]);
            sb[i].addActionListener(
                    new ActionListener() {

                        public void actionPerformed(ActionEvent e) {
                            int j;
                            for (j = 0; j < 9; j++) {
                                if (e.getSource() == sb[j]) {
                                    getnum = "" + j;
                                }
                            }
                            nf.dispose();
                            enable();
                        }
                    });
        }
        disable();
        nf.show();
        return getnum;
    }

    void recall(int x) {
        dispose();
        GraphicallyRepresentation rs = new GraphicallyRepresentation(x);
    }
}
	