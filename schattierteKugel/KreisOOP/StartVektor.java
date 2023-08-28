package schattierteKugel.KreisOOP;

class StartVektor {
    
    int[] vektor = new int[3];
    
    public StartVektor() {
        vektor[0] = 10; 
        vektor[1] = 10;
        vektor[2] = 10;
        System.out.println(vektor[0]); 
    }
    public StartVektor(int vh, int lr, int ou) {
        vektor[0] = vh; 
        vektor[1] = lr;
        vektor[2] = ou;
    }
    
    public void drehVektor(int winkel) {
        int[] new_vec = new int[3];
        
        double winkel_bg = (double)(winkel/180)*Math.PI;
        double[][] drehmatrix = {{Math.cos(winkel_bg),Math.sin(winkel_bg),0},{-Math.sin(winkel_bg),Math.cos(winkel_bg),0},{0,0,1}};

        for (int a = 0; a < vektor.length; a++) {
            float val_new_vec = 0; 
            for (int b = 0; b < vektor.length; b++) {
                val_new_vec += vektor[b]*drehmatrix[a][b];
            }
            new_vec[a] = Math.round(val_new_vec);
        }
        vektor = new_vec;
    }
}
