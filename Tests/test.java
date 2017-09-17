import com.company.work1;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class test {

    @Test
    void ex1() {
        assertEquals(List.of(), work1.searchMass(List.of()));
        assertEquals(List.of(1, 2, 3, 4, 5), work1.searchMass(List.of(1, 2, 3, 4, 5)));
        assertEquals(List.of(), work1.searchMass(List.of(-10, -10, -10, -10)));
        assertEquals(List.of(1, 2, 3), work1.searchMass(List.of(1, 2, 3, -7, 2)));
        assertEquals(List.of(100,10), work1.searchMass(List.of(100,10,-50,40,-50,30,-50,20,-50,10,-50,100)));
        assertEquals(List.of(1000), work1.searchMass(List.of(100,10,-50,40,-50,30,-50,20,-50,10,-50,1000)));
        assertEquals(List.of(), work1.searchMass(List.of(0,0,0,0,0)));
        assertEquals(List.of(14), work1.searchMass(List.of(10,-11,12,-13,14,-15)));
        assertEquals(List.of(10,5,-5,-5,10,-5,25), work1.searchMass(List.of(10,5,-5,-5,10,-5,25)));
    }
}
