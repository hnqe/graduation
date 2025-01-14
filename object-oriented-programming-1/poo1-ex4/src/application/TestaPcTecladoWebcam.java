package application;

import entities.PC;
import entities.Teclado;
import entities.Webcam;

public class TestaPcTecladoWebcam {
	
    public static void main(String[] args) {

    	Teclado teclado = new Teclado("Logitech", "Mecânico");
        Webcam webcam = new Webcam("Logitech", 1080);

        PC pc1 = new PC("Modelo XYZ", teclado);
        PC pc2 = new PC("Modelo XYZ", teclado, webcam);

        System.out.println(pc1.toString());
                
		System.out.println(pc2.toString());
		
        pc1.setWebcam(webcam);
        System.out.println(pc1.toString());
        
    }
}
