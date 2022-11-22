
import java.awt.*;

import javax.lang.model.util.ElementScanner6;
import javax.swing.*;
import java.awt.event.*;
import java.util.Random;
import java.awt.Color.*;

public class chompGame extends JFrame {
    private JPanel topPanel, middlePanel, bottomPanel;
    private JRadioButton small, medium, large;
    private JCheckBox reset = new JCheckBox("Reset");
    private ButtonGroup group = new ButtonGroup();
    public JLabel status = new JLabel();
    public int prevx, prevy;
    public int row, col;
    public int xc, yr;
    public boolean currrow, currcol = false;
    private gridSquare[][] barray;
    public static final Color BROWN_COLOR = new Color(102, 51, 0);
    private Random rand = new Random();
    public String turn = "";
    public boolean over,resetover = false;
    public boolean userdel, pcdel;
    boolean selected = false;
    public String winner = "";
    public JLabel s = new JLabel();
    public boolean pc = false;

    // static main method
    public static void main(String[] args) {
        chompGame g = new chompGame();
    }

    public chompGame(int x,int y)
    {
         // main game JFrame name
         super("Game Of Chomp");
         setSize(500, 100);
         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
         // intialise panels
         topPanel = new JPanel();
         middlePanel = new JPanel();
         bottomPanel = new JPanel();
 
         // sets initial status
         setStatus("initial");
         over = false;
 
         // buttons declaration
         reset.addActionListener(e -> resetEvent());
         small = new JRadioButton("Small");
         small.addActionListener(e -> smallEvent());
         medium = new JRadioButton("Medium");
         medium.addActionListener(e -> mediumEvent());
         large = new JRadioButton("Large");
         large.addActionListener(e -> largeEvent());
 
         // button group
         group.add(reset);
         group.add(small);
         group.add(medium);
         group.add(large);
 
         // top panel
         topPanel.add(reset);
         topPanel.add(small);
         topPanel.add(medium);
         topPanel.add(large);
 
         // bottom panel
         bottomPanel.add(status);
 
         // contentpane
         getContentPane().setLayout(new BorderLayout());
         getContentPane().add(topPanel, BorderLayout.NORTH);
         getContentPane().add(bottomPanel, BorderLayout.SOUTH);
 
         // JFrame properties
         setVisible(true);
         setResizable(false);
        if (x==4&&y==4)
        {
            smallEvent();
        }
        else if (x==7&&y==7)
        {
            mediumEvent();
        }
        else if (x==10&&y==10)
        {
            largeEvent();
        }
    }

    public chompGame() {
        // main game JFrame name
        super("Game Of Chomp");
        setSize(500, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // intialise panels
        topPanel = new JPanel();
        middlePanel = new JPanel();
        bottomPanel = new JPanel();

        // sets initial status
        setStatus("initial");
        over = false;

        // buttons declaration
        reset.addActionListener(e -> resetEvent());
        small = new JRadioButton("Small");
        small.addActionListener(e -> smallEvent());
        medium = new JRadioButton("Medium");
        medium.addActionListener(e -> mediumEvent());
        large = new JRadioButton("Large");
        large.addActionListener(e -> largeEvent());

        // button group
        group.add(reset);
        group.add(small);
        group.add(medium);
        group.add(large);

        // top panel
        topPanel.add(reset);
        topPanel.add(small);
        topPanel.add(medium);
        topPanel.add(large);

        // bottom panel
        bottomPanel.add(status);

        // contentpane
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(topPanel, BorderLayout.NORTH);
        getContentPane().add(bottomPanel, BorderLayout.SOUTH);

        // JFrame properties
        setVisible(true);
        setResizable(false);
    }

    // create Chocolate + adds chocolate to middlepanel + store last played
    // chocolate size
    private void createChocolate(int x, int y) {
        removePanel();

        prevx = x;
        prevy = y;
        row = x;
        col = y;
        middlePanel = new JPanel();
        middlePanel.setLayout(new GridLayout(x, y));
        barray = new gridSquare[x][y];
        for (int r = 0; r < x; r++) {
            for (int c = 0; c < y; c++) {
                barray[r][c] = new gridSquare(r, c);
                int remx, remy;
                remx = r;
                remy = c;
                barray[r][c].addActionListener(e -> delete(remx, remy));
                middlePanel.add(barray[r][c]);
            }
        }
        getContentPane().add(middlePanel, BorderLayout.CENTER);
        setSize(500, 500);

    }

 

    private void delete(int r, int c) 
    {
        if ((userdel==false)&&(pcdel==true)&&(turn.equals("user")))
        {
            rem(r, c);
            userdel=true;
            pcdel=false;
        }
        else if ((userdel==true)&&(pcdel==false)&&(turn.equals("pc")))
        {
            barray[xc][yr].setBackground(Color.yellow);
            try{
                //print something here
                Thread.sleep(1000); //sleep for 3 seconds
                //print something else here
                }
                catch(InterruptedException e)
                {    
                    System.out.println("got interrupted!");
                }
            rem(xc, yr);
            pcdel = true;
            userdel = false;
        }
        
    }

    // removes chocolate if valid
    private void rem(int r, int c) {
        for (int x = row - 1; x >= r; x--) {
            for (int y = col - 1; y >= c; y--) {
                del(x, y);
            }
        }

        JButton b = barray[0][1];
        JButton b2 = barray[1][0];
        if (!b.isEnabled() && !b2.isEnabled()) {
            over = true;
        }
    }

    private void playagain() {
        if (turn.equals("user"))
        {
            winner = "COMPUTER";
        }
        else
        {
            winner = "USER";
        }
        setStatus(winner + " wins");
        int result = JOptionPane.showConfirmDialog(null, winner + " WINS..DO YOU WANT TO PLAY AGAIN?", "GAME OVER",
                JOptionPane.YES_NO_OPTION);
        if (result == 0) {
            this.setVisible(false);
            dispose();
            chompGame g = new chompGame(prevx,prevy);
        }
        else {
            System.exit(0);
        }
    }

    
    // delete command
    private void del(int r, int c) {
        JButton button = barray[r][c];
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setEnabled(false);
    }

    // reset button action event
    private void resetEvent() {
        this.setVisible(false);
        dispose();
        chompGame game1 = new chompGame();
    }



    // small button action event
    private void smallEvent() {
        setStatus("small");
        small.setSelected(true);
        small.setEnabled(false);
        medium.setEnabled(false);
        large.setEnabled(false);
        createChocolate(4, 4);
        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {

            @Override
            protected Void doInBackground() throws Exception {
                play();
                return null;
            }
        };
        worker.execute();

    }

    // medium button action event
    private void mediumEvent() {
        over = false;
        pcdel = false;
        userdel = false;
        setStatus("medium");
        medium.setSelected(true);
        small.setEnabled(false);
        medium.setEnabled(false);
        large.setEnabled(false);
        createChocolate(7, 7);
        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {

            @Override
            protected Void doInBackground() throws Exception {
                play();
                return null;
            }
        };
        worker.execute();
    }

    // large button action event
    private void largeEvent() {
        over = false;
        pcdel = false;
        userdel = false;
        setStatus("large");
        large.setSelected(true);
        small.setEnabled(false);
        medium.setEnabled(false);
        large.setEnabled(false);
        createChocolate(10, 10);
        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {

            @Override
            protected Void doInBackground() throws Exception {
                play();
                return null;
            }
        };
        worker.execute();
    }

    // sets current status of game
    public void setStatus(String s) {
        switch (s) {
        case "initial":
            status.setText("Select a size to start game");
            break;
        case "small":
            status.setText("Small selected");
            break;
        case "medium":
            status.setText("Medium selected");
            break;
        case "large":
            status.setText("Large selected");
            break;
        case "reset":
            status.setText("Game reset, select a size to start game");
            break;
        default:
            status.setText(s);
            break;
        }
    }

    // removes middlePanel after reset or game over
    private void removePanel() {
        middlePanel.removeAll();
        this.getContentPane().remove(middlePanel);
        setSize(500, 100);
    }

    // game starts
    public void play()
    {
        int x = rand.nextInt(2);
        if (x==0)
        {
            turn = "user";
            pcdel = true;
            userdel = false;
        }
        else if (x==1)
        {
            turn = "pc";
            pcdel = false;
            userdel = true;
        }
        while (!over)
        {
            if (turn.equals("user"))
            {
                setStatus("User turn");
                while(!((pcdel==false)&&(userdel==true)))
                {
                    setStatus("User turn");
                    s.setText("text");
                }
                turn = "pc";
            }
            else if (turn.equals("pc"))
            {
                while(!((pcdel==true)&&(userdel==false)))
                {
                    setStatus("Computer turn");
                    xc = rand.nextInt(row);
                    yr = rand.nextInt(col);
                    if (isValid(xc, yr))
                    {
                        delete(xc, yr);
                    }
                }
                turn = "user";
            }
        }
        if (over==true)
        {
            playagain();
        }
    }

    private boolean isValid(int r, int c) {
        JButton b = barray[r][c];
        if (r == 0 && c == 0) {
            return false;
        } else if (b.isEnabled()&&b.isOpaque()&&b.isContentAreaFilled()&&b.isBorderPainted()) {
            return true;
        } else {
            return false;
        }
    }
}
