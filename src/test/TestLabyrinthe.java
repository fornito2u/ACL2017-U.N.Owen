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

        //La bordure est inaccessible
        assertFalse(laby.open(0,0));

        //Case accessible
        assertTrue(laby.open(1,1));

        //Case inaccessible
        assertFalse(laby.open(4,2));
    }
}
