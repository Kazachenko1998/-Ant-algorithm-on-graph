import java.util.ArrayList;

public class ALG {
    private double[][] distance0;
    private int vertex;
    private double distance[][];
    private double pheromone[][];

    public ALG(double[][] distance0) {
        this.distance0 = distance0;
        this.vertex = distance0.length;
        distance = new double[vertex][vertex];
        pheromone = new double[vertex][vertex];
    }

    class termiteWay {
        int locate;
        double length = 0;
        ArrayList<Integer> way = new ArrayList<>();
    }

    private double probability(int to, termiteWay W) {
        double sum = 0.0;
        int from = W.locate;
        for (int j = 0; j < vertex; j++) {
            if (!W.way.contains(j) && from != j)
                sum += Math.pow(pheromone[from][j], Constanta.ALPHA) * Math.pow(distance[from][j], Constanta.BETTA);
        }
        return Math.pow(pheromone[from][to], Constanta.ALPHA) * Math.pow(distance[from][to], Constanta.BETTA) / sum;
    }

    private void initialization() {
        for (int z = 0; z < vertex; ++z) {
            for (int j = 0; j < vertex; ++j) {
                distance[z][j] = vertex - 1;
                pheromone[z][j] = 1.0 / (vertex - 1);
                if (z != j)
                    distance[z][j] = 1.0 / distance0[z][j];
              //  System.out.print(distance0[z][j] + " ");
            }
           // System.out.println();
        }
    }

    private void locateAnts(termiteWay[] ants) {
        for (int k = 0; k < Constanta.M; k++) {
            ants[k] = new termiteWay();
            ants[k].locate = Math.abs((int) (Math.round(Math.random() * 10000000) % vertex));
            ants[k].length = 0;
        }
    }

    private void updatePheromoneMap(termiteWay ant) {
        for (int l = 0; l < vertex; l++) {
            int j_max = -1;
            double p_max = 0.0;
            for (int j = 0; j < vertex; j++) {
                if (!ant.way.contains(j) && ant.locate != j) {
                    double p = probability(j, ant);
                    if (p >= p_max) {
                        p_max = p;
                        j_max = j;
                    }
                }
            }
            ant.length += distance0[ant.locate][j_max];
            pheromone[ant.locate][j_max] += Constanta.Q / ant.length;
            pheromone[j_max][ant.locate] = pheromone[ant.locate][j_max];
            ant.locate = j_max;
            ant.way.add(j_max);
        }
    }

    private void evaporationPheramone() {
        for (int p = 0; p < vertex; ++p) {
            for (int j = 0; j < vertex; j++) {
                if (p != j) pheromone[p][j] *= (1 - Constanta.RHO);
            }
        }
    }

    public termiteWay AntColonyOptimization() {
        termiteWay Way = new termiteWay();
        Way.locate = 0;
        Way.length = -1;

        initialization();

        termiteWay[] ants = new termiteWay[Constanta.M];
        termiteWay way = new termiteWay();
        way.length = -1;
        for (int t = 0; t < Constanta.T_MAX; t++) {
            locateAnts(ants);

            for (int k = 0; k < Constanta.M; k++) {

                updatePheromoneMap(ants[k]);

                if (ants[k].length < way.length || way.length < 0) {
                    way.way = (ArrayList<Integer>) ants[k].way.clone();
                    way.length = ants[k].length;
                }
                ants[k].locate = 1;
                ants[k].length = 0;
            }
            evaporationPheramone();
        }
        return way;
    }
}