import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class SILab2Test {
    private final SILab2 silab = new SILab2();

    private List<String> createList(String... elems) {
        return new ArrayList<>(Arrays.asList(elems));
    }

    @Test
    public void every_branch_test() {
        RuntimeException ex = null;
        try {
            silab.function(null, createList("user"));
        } catch (RuntimeException e) {
            ex = e;
        }
        assertNotNull(ex);
        assertTrue(ex.getMessage().contains("The user argument is not initialized!"));

        User user1 = new User(null, "andrea123", "andrea.ristevska@hotmail.com");
        RuntimeException ex2 = null;
        try {
            silab.function(user1, createList("user"));
        } catch (RuntimeException e) {
            ex = e;
        }
        assertNotNull(ex);
        assertTrue(ex.getMessage().contains("User already exists!"));

        User user2 = new User("Andrea", "andrea123", null);
        assertFalse(silab.function(user2, createList("user")));

        User user3 = new User("Andrea", "Andrea123", "andrea@hotmail.com");
        assertTrue(silab.function(user3, createList("user")));

        User user4 = new User("Andrea", "andrea123", "andrearistevska");
        assertTrue(silab.function(user4, createList("user")));

    }


    @Test
    void multiple_conditions_test() {

        //za uslovot if(user.getUsername() == null || allUsers.contains(user.getUsername())
        //T || X
        User user1=new User(null,"andrea123","andrea.ristevska@hotmail.com");
        RuntimeException ex = null;
        try {
            silab.function(user1, createList("user"));
        } catch (RuntimeException e) {
            ex = e;
        }
        assertNotNull(ex);
        assertTrue(ex.getMessage().contains("User already exists!"));

        //F || T
        RuntimeException ex2 = null;
        User user2=new User("Andrea","andrea123","andrea.ristevska@hotmail.com");
        try {
            silab.function(user2, createList("user"));
        } catch (RuntimeException e) {
            ex = e;
        }
        assertNotNull(ex);
        assertTrue(ex.getMessage().contains("User already exists!"));

        //F || F
        User user3 = new User("Andrea", "Andrea123", "andrea@hotmail.com");
        assertTrue(silab.function(user3, createList("user")));


        //za uslovot if (atChar && user.getEmail().charAt(i)=='.')
        //T && T
        User user4=new User("Andrea","andrea123","andrea@hotmail.com");
        assertTrue(silab.function(user3, createList("user")));

        // T && F
        User user5=new User("Andrea","andrea123","andrea@hotmail");
        assertTrue(silab.function(user3, createList("user")));

        // F && X
        User user6=new User("Andrea","andrea123","andreahotmail");
        assertTrue(silab.function(user3, createList("user")));


        //za uslovot if(!atChar || !dotChar)
        //T || X
        User user7=new User("Andrea","andrea123","andrea.ristevska");
        assertFalse(silab.function(user3, createList("user")));

        //F || T
        User user8=new User("Andrea","andrea123","andrea@hotmail");
        assertFalse(silab.function(user3, createList("user")));

        //F || F
        User user9=new User("Andrea","andrea123","andrea.ristevska@hotmail.com");
        assertFalse(silab.function(user3, createList("user")));

    }
}