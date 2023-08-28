package schattierteKugel.KreisOOP;

class Kreis {
    
    String pic_zwei_dim; // = dimensionen
    String pic_drei_dim;

    int side_screen;
    int r;

    public Kreis(int side) {
        
        side_screen = side;

        // Vereinfachung: nur ungerade zulassen
        if (side_screen % 2 == 0) {
            System.out.println("wrong length");
            System.out.println("Lenght of side of screen "+ side_screen);
            String fail_pic = "";
            for (int i = 0; i < side_screen*side_screen; i++) {fail_pic += "0";}
            pic_zwei_dim = fail_pic;

        } else {
        
            String picture = "";
            String row = ""; 

            r = Math.round((float) (side_screen/3));
            
            // Formel y = root(r²-x²)
            // Herangehensweise: x-werte (Ränder) für jede Zeile -> y = root(...) -> Nach x auflösen wird zu x = root(r²-y²)
            
            for (int i = 0; i <= (side_screen-1)/2; i++) {
                int current_height = (side_screen-1)/2 - i;
                if (current_height < r) { // sonst kommt kreis nicht an Zeile/Höhe
                    int x = (int) Math.round(Math.sqrt(r*r - current_height*current_height));
                    
                    
                    // Alles füllen (aka wie muss die Reihe auf der Höhe aussehen
                    row = ""; // Reset
                    int von = (side_screen-1)/2-x; // Index von der Mitte des Bildes +- x
                    int bis = (side_screen-1)/2+x;
                    for (int a = 0; a < side_screen; a++) {
                        if (a >= von && a <= bis) {
                            row += 1;
                        } else {
                            row += 0;
                        }
                    
                    }   
                    picture += row;               
                }
                // wenn Kreis nicht rankommt 
                else {
                    for (int t = 0; t<side_screen; t++) {
                        picture += "0";
                    }
                }
            }

            // Alles nach unten Spiegeln
            int start_middle_row = side_screen*((side_screen-1)/2);
            for (int j = 1; j <= (start_middle_row); j++) {
                picture += picture.charAt(start_middle_row-j);
            }
            pic_zwei_dim = picture; 
        } 
    }
    
    public void schattiere(int[] v_bright_spot) {
        
        // Make into list
        int pic_array[] = new int[pic_zwei_dim.length()];
        
        // put numbers into the array 
        for (int i = 0; i < pic_zwei_dim.length(); i++) {    
            pic_array[i]  = (int) pic_zwei_dim.charAt(i) - 48; // -48 = Ascii wegbekommen 
        }
        
        for (int zeile = 0; zeile < side_screen; zeile++) {
            for (int spalte = 0; spalte < side_screen; spalte++) {
                if (pic_array[zeile*side_screen + spalte] != 0) {
                    int vh =0;
                    int lr = -((side_screen-1)/2) +spalte;
                    int ou = ((side_screen-1)/2 -zeile);;

                    // Vorne-hinten vektor
                    double r_an_zeile = Math.sqrt(r*r - ou*ou);
                    vh = Math.round((float) Math.sqrt(r_an_zeile*r_an_zeile - lr*lr));
                    int[] vec_to_point = {vh,lr,ou}; // Schema 0: vorne, hinten, 1: y links, rechts, 2: oben unten
                    
                    // cos_winkel = (skalarprodukt(v*u))/(|u|*|v|)
                    int skalar = gibSkalar(v_bright_spot, vec_to_point);
                    double laengen = gibLaengen(v_bright_spot, vec_to_point);
                    // Get Deg
                    int deg = (int) Math.round(Math.acos(skalar/laengen)/Math.acos(-1)*180); // Von Bogenmaß in Gradmaß (switch nimmt Grad)
                    // In Nummer converten (erste Zahl von winkel macht helligkeit (Einer-stelle egal))
                    // einer weg
                    deg = deg - (deg % 20);
                    deg /= 20;
                    pic_array[side_screen*zeile+spalte] *= (deg+1); // überall wo angepasst werden soll steht 1 also 1 * deg = deg = Helligkeit 
                } 
            }
        }
        pic_drei_dim = "";
        for (int num : pic_array) {
            pic_drei_dim += num;
        }
    }

    public void printKreis() {
         
        String current_row;
        //check for right side_screen
        if (side_screen != Math.sqrt(pic_zwei_dim.length())) {
            System.out.println("error");

        }
        else {
            for (int row = 0; row < side_screen; row++) { 
                current_row = "";
                //Make the row
                for (int i = 0; i < side_screen; i++) {
                    String thingtoadd="";
                    switch (pic_drei_dim.charAt(i+row*side_screen)) {
                        // |  LLCCOOTTIIAAXXMM@@|
                        case '1': 
                            thingtoadd = "@@";
                            break;
                        case '2': 
                            thingtoadd = "BB";
                            break;
                        case '3': 
                            thingtoadd = "MM";
                            break;
                        case '4': 
                            thingtoadd = "OO";
                            break;
                        case '5': 
                            thingtoadd = "AA";
                            break;
                        case '6': 
                            thingtoadd = "II";
                            break;
                        case '7':
                            thingtoadd = "CC";
                            break;
                        case '8':
                            thingtoadd = "TT";
                            break;
                        case '9':
                            thingtoadd = "++";
                            break;
                        default:
                            thingtoadd = "  ";
                    }
                    current_row += thingtoadd;
                }
                // Print the row
                System.out.println(current_row);
            }
        }
    }

    public static int gibSkalar(int[] v_spot, int[] v_current) {
        int res = 0;
        for (int i = 0; i < 3; i++) {
            res += v_spot[i]*v_current[i]; 
        }
        return res;
    }

    public static double gibLaengen(int[] v_spot, int[] v_current) {
        double l = Math.sqrt(v_spot[0]*v_spot[0]+v_spot[1]*v_spot[1]+v_spot[2]*v_spot[2]);
        double k = Math.sqrt(v_current[0]*v_current[0]+v_current[1]*v_current[1]+v_current[2]*v_current[2]);
        return l*k;
    }

}
