package lt.liudas.controllers;

import lt.liudas.services.DbSeederServiceImpl;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TestDbController {

    @Test
    public void testDbController(){
        assertEquals(4, DbSeederServiceImpl.add(2, 2));
//        assertEquals(-1,Calculation.findMax(new int[]{-12,-1,-3,-4,-2}));
    }
}
