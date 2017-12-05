import java.util.Random;

public class AntsColony {
    private double[][] distance;
    private int vertex; 
    private double priorityMap[][];
    private double pheromoneMap[][];

    public AntsColony(double[][] distance) {
        this.distance = distance;
        this.vertex = distance.length;
        priorityMap = new double[vertex][vertex];
        pheromoneMap = new double[vertex][vertex];
    }


    private double probability(int to, Ant ant) {
        double sum = 0.0;
        int from = ant.getLocate();
        for (int j = 0; j < vertex; j++) {
            if (!ant.getWay().contains(j) && from != j)
                sum += Math.pow(pheromoneMap[from][j], Constanta.ALPHA) * Math.pow(priorityMap[from][j], Constanta.BETTA);
        }
        return Math.pow(pheromoneMap[from][to], Constanta.ALPHA) * Math.pow(priorityMap[from][to], Constanta.BETTA) / sum;
    }

    private void initialization() {
        for (int z = 0; z < vertex; ++z) {
            for (int j = 0; j < vertex; ++j) {
                priorityMap[z][j] = vertex - 1;
                pheromoneMap[z][j] = 1.0 / (vertex - 1);
                if (z != j)
                    priorityMap[z][j] = 1.0 / distance[z][j];
              //  System.out.print(distance0[z][j] + " ");
            }
           // System.out.println();
        }
    }

    private void locateAnts(Ant[] ants) {
        for (int k = 0; k < Constanta.M; k++) {
            ants[k] = new Ant();
            ants[k].setLocate(new Random().nextInt(vertex));
            ants[k].setWayLength(0);
        }
    }

    private void updatePheromoneMap(Ant ant) {
        for (int l = 0; l < vertex; l++) {
            int j_max = -1;
            double priorityMax = 0.0;
            for (int j = 0; j < vertex; j++) {
                if (!ant.getWay().contains(j) && ant.getLocate() != j) {
                    double thisPriority = probability(j, ant);
                    if (thisPriority >= priorityMax) {
                        priorityMax = thisPriority;
                        j_max = j;
                    }
                }
            }
            ant.setWayLength(ant.getWayLength() + distance[ant.getLocate()][j_max]);
            pheromoneMap[ant.getLocate()][j_max] += Constanta.Q / ant.getWayLength();
            pheromoneMap[j_max][ant.getLocate()] = pheromoneMap[ant.getLocate()][j_max];
            ant.setLocate( j_max);
            ant.getWay().add(j_max);
        }
    }

    private void evaporationPheramone() {
        for (int p = 0; p < vertex; ++p) {
            for (int j = 0; j < vertex; j++) {
                if (p != j) pheromoneMap[p][j] *= (1 - Constanta.RHO);
            }
        }
    }

    public Ant AntColonyOptimization() {
        Ant Way = new Ant();
        Way.setLocate(0);
        Way.setWayLength(-1);

        initialization();

        Ant[] ants = new Ant[Constanta.M];
        Ant way = new Ant();
        way.setWayLength(-1);
        for (int t = 0; t < Constanta.T_MAX; t++) {
            locateAnts(ants);

            for (int k = 0; k < Constanta.M; k++) {

                updatePheromoneMap(ants[k]);

                if (ants[k].getWayLength() < way.getWayLength() || way.getWayLength() < 0) {
                    way.setWay(ants[k].getWay());
                    way.setWayLength(ants[k].getWayLength());
                }
                ants[k].setLocate(1);
                ants[k].setWayLength(0);
            }
            evaporationPheramone();
        }
        return way;
    }
}