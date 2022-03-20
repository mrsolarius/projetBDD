package fr.litopia.views.menus.station.emprun;

import fr.litopia.model.LocationNonAbonne;
import fr.litopia.utils.ReadingConsole;

public class EmprunNonAboView extends EmprunComonContext {

    @Override
    protected void init() {

    }

    @Override
    protected void display() {
        System.out.println("=================");
        System.out.println("EMPRUNTER UN VÉLO");
        System.out.println("==================");
        System.out.println("Veuillez renseigner votre numéro de carte bancaire : ");
        String cb = ReadingConsole.readLineNumeric(16);
        LocationNonAbonne loc = this.emprunControler.emprunterVeloNonAbonne(this.station,cb);
        this.clean();
        System.out.println("=================");
        System.out.println("EMPRUNTER UN VÉLO");
        System.out.println("==================");
        System.out.println("Merci d'avoir choisie VePick !");
        System.out.println("Votre code d'emprunt est : "+loc.getCode());
        System.out.println("Veuillez le garder précieusement !");
        System.out.println("Il vous sera demandé lorsque vous retourner le vélo.");
        System.out.println("Appuyez sur entrée pour prendre votre vélo à la bornette n°"+loc.getVelo().getBornette().getNumero());
        System.out.println("==================");
        ReadingConsole.readLine();
        this.emprunControler.prendreVelo(loc);
        //On stope cette vue ainci que la vue du dessus d'emprun de vélo
        this.stop().stop();
    }

    @Override
    protected void close() {

    }
}
