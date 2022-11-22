

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Random;
import java.awt.Color.*;

public class gridSquare extends JButton
{
    public static final Color BROWN_COLOR = new Color(102, 51, 0);
    public int xcoord,ycoord;

    public gridSquare(int x,int y)
    {
        super();
        xcoord = x;
        ycoord = y;
        if (x==0&&y==0)
        {
            setSoap();
        }
        else
        {
            setChocolate();
        }
    }

    public void setChocolate()
    {
        this.setBackground(BROWN_COLOR);
    }

    public void setSoap()
    {
        this.setBackground(Color.green);
        this.setEnabled(false);
    }
}
