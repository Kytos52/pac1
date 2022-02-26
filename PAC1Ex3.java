package edu.uoc.pac1;

public class PAC1Ex3 {

    public static final int GAMES_PLAYED = 38;

    public static int[][] getCompleteClassification(int [][] array) {
        //TODO


        int[][] clasificacionCompleta = new int[20][6];

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                if(j==0)
                    clasificacionCompleta[i][j]=array[i][j];
                if (j==1){
                    clasificacionCompleta[i][j]=0;
                    clasificacionCompleta[i][j+1]=array[i][j];
                }
                if(j==2 || j==3){
                    clasificacionCompleta[i][j+1]=array[i][j];
                }
            }

        }
        for(int i=0; i<clasificacionCompleta.length;i++){
            for (int j=0; j<clasificacionCompleta[i].length;j++) {
                if (j == 1) {
                    clasificacionCompleta[i][j] = (38 - (clasificacionCompleta[i][j - 1]) - clasificacionCompleta[i][j+1]);
                }
                if (j == 5) {
                    clasificacionCompleta[i][j] = clasificacionCompleta[i][0] * 3 + clasificacionCompleta[i][1];
                }
            }
        }

        return clasificacionCompleta;
    }


    public static void print(int [][] array){
        //TODO
        if(array[0].length !=6){
            System.out.println("[ERROR] Classification table does not have the correct columns");
        }else {


            System.out.println("#   |  W  |  D  |  L  |  GF |  GA |  PTS|");
            for (int i = 0; i < array.length; i++) {
                if (i < 9) {
                    System.out.printf("%-1d", (i + 1));
                    System.out.print(".  |  ");
                } else {
                    System.out.printf("%-2d", (i + 1));
                    System.out.print(". |  ");
                }
                for (int j = 0; j < array[i].length - 1; j++) {

                    System.out.printf("%-2d", array[i][j]);
                    System.out.print(" |  ");
                }
                System.out.printf("%-2d", array[i][5]);
                System.out.print(" |");

                System.out.println("");
            }
        }
    }



    public static int getNumTeamsByGoalDifference(int [][] array, boolean positive){
        //TODO
        if(array[0].length !=6){
            System.out.println("[ERROR] Classification table does not have the correct columns");
            return -1;
        }else {


            int equipos = 0;
            for (int i = 0; i < array.length; i++) {
                if (array[i][3] >= array[i][4]) {
                    equipos++;
                }
            }
            if (!positive) {
                equipos = 20 - equipos;
            }

            return equipos;
        }
    }

    public static void sort(int [][] array){
        //TODO


        int [][] arrayOrdenado= new int[20][6];


        //Ordenación por puntos
        for(int j = 0; j <array.length; j++) {
            int maximo =0;
            int indice = 0;
            for (int i = 0; i < array.length ; i++) {
                if (array[i][5] > maximo) {
                    maximo = array[i][5];
                    indice = i;
                }
            }

            for(int z =0;z <6;z++) {
                arrayOrdenado[j][z] = array[indice][z];
            }
            array[indice][5]=0;
        }

        //Ordenacion de empates a puntos
        int [][] arrayOrdenadoEmpates= new int[20][6];
        for(int j = 0; j <arrayOrdenado.length-1; j++) {
            int maximo =0;
            int indice = 0;
            for (int i = 0; i < arrayOrdenado.length - 1; i++) {
                if (arrayOrdenado[i][5] == arrayOrdenado[i+1][5]) {
                    if(arrayOrdenado[i][3] < arrayOrdenado[i+1][3]){
                        arrayOrdenadoEmpates[i]=arrayOrdenado[i+1];
                        arrayOrdenadoEmpates[i+1]=arrayOrdenado[i];
                        i++;
                    }else{
                        arrayOrdenadoEmpates[i]=arrayOrdenado[i];
                        arrayOrdenadoEmpates[i+1]=arrayOrdenado[i+1];
                        i++;
                    }
                }else{
                    arrayOrdenadoEmpates[i]=arrayOrdenado[i];
                    arrayOrdenadoEmpates[i+1]=arrayOrdenado[i+1];
                }
            }
        }
        for(int i=0; i<arrayOrdenadoEmpates.length;i++){
            for (int j=0; j< arrayOrdenadoEmpates[i].length;j++){
                array[i][j]=arrayOrdenadoEmpates[i][j];
            }

        }



    }

}
