package schattierteKugel.KreisOOP;

class Main {
    public static void main(String[] args) {
        int side_of_screen = 55;  
        Kreis k = new Kreis(side_of_screen);
        StartVektor v = new StartVektor(10,10,10);
        k.schattiere(v.vektor);
        k.printKreis();
    }
}