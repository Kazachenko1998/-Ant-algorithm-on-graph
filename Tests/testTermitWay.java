import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class testTermitWay {
    //можно сравнивать сами маршруты, но так как они почти всегда случайные и порядок цикличен,
    // тестировать их сложно, поэтому сравниваю только длннны
    //но даже так при многократном запуске иногда тесты ломаются(так как алгоритм не точный, а примерный и вероятностный
    @Test
    void first() {
        double[][] D = new double[][]{
                {10, 9, 8, 7, 6},
                {5, 4, 3, 2, 1},
                {5, 4, 3, 2, 1},
                {5, 4, 3, 2, 1},
                {5, 4, 3, 2, 1}
        };
        ALG.termiteWay WAY = new ALG(D).AntColonyOptimization();
        assertEquals(15, WAY.length);
    }

    @Test
    void second() {
        double[][] D = new double[][]{
                {1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1}
        };
        ALG.termiteWay WAY = new ALG(D).AntColonyOptimization();
        assertEquals(5, WAY.length);
    }

    @Test
    void test3() {
        double[][] D = new double[][]{
                {5, 5, 5, 5, 2},
                {5, 5, 5, 1, 5},
                {5, 2, 5, 5, 5},
                {3, 5, 5, 5, 5},
                {5, 5, 4, 5, 5}
        };
        ALG.termiteWay WAY = new ALG(D).AntColonyOptimization();
        assertEquals(12, WAY.length);
    }
}
