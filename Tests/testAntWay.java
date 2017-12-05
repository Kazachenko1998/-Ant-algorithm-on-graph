import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class testAntWay {
    //можно сравнивать сами маршруты, но так как они почти всегда случайные и порядок цикличен,
    // тестировать их сложно, поэтому сравниваю только длннны
    //но даже так при многократном запуске иногда тесты ломаются(так как алгоритм не точный, а примерный и вероятностный
    @Test
    void random() {
        for (int p = 0; p < 20; p++) {
            int N = 30;
            double[][] D = new double[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = i; j < N; j++) {
                    D[i][j] = Math.random() * 100;
                    D[j][i] = D[i][j];
                }
            }

            Ant ant = new AntsColony(D).AntColonyOptimization();
            System.out.println(ant.getWayLength());
            Ant ant2 = new Ant();
            ant2.setLocate(0);
            for (int i = 0; i < N; i++) {
                Double priorityMax = Double.MAX_VALUE;
                int j_max = 0;
                for (int j = 0; j < N; j++) {
                    if (!ant2.getWay().contains(j) && ant2.getLocate() != j) {
                        if (D[ant2.getLocate()][j] <= priorityMax) {
                            priorityMax = D[ant2.getLocate()][j];
                            j_max = j;
                        }
                    }
                }
                ant2.setWayLength(ant2.getWayLength() + D[ant2.getLocate()][j_max]);
                ant2.setLocate(j_max);
                ant2.getWay().add(j_max);
            }
            System.out.println(ant2.getWayLength());
            assertTrue(ant.getWayLength() <= ant2.getWayLength());
        }
    }

    @Test
    void first() {
        double[][] D = new double[][]{
                {10, 9, 8, 7, 6},
                {5, 4, 3, 2, 1},
                {5, 4, 3, 2, 1},
                {5, 4, 3, 2, 1},
                {5, 4, 3, 2, 1}
        };
        Ant ant = new AntsColony(D).AntColonyOptimization();
        assertEquals(15, ant.getWayLength());
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
        Ant ant = new AntsColony(D).AntColonyOptimization();
        assertEquals(5, ant.getWayLength());
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
        Ant ant = new AntsColony(D).AntColonyOptimization();
        assertEquals(12, ant.getWayLength());
    }
}
