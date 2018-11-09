package test;

import model.Labyrinthe;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestLabyrinthe {

    @Test
    public void testOpenRight() throws IOException {
        Labyrinthe laby=new Labyrinthe(new File("labyrinthe.txt"));

        //Case accessible
        assertTrue(laby.open(1,1));

        //Case inaccessible
        assertFalse(laby.open(4,2));
    }

    //La bordure est inaccessible
    @Test
    public void testOpenBoundary() throws IOException {
        Labyrinthe laby=new Labyrinthe(new File("labyrinthe.txt"));
        int w=laby.getWidth();
        int h=laby.getHeight();

        for (int i=0;i<w;i++) {
            assertFalse(laby.open(i,0));
        }

        for (int i=0;i<w;i++) {
            assertFalse(laby.open(i,h-1));
        }

        for (int i=0;i<h;i++) {
            assertFalse(laby.open(0,i));
        }

        for (int i=0;i<h;i++) {
            assertFalse(laby.open(0,w-1));
        }
    }
}
