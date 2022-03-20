package fr.litopia.views.menus.station.emprun;

import fr.litopia.controler.ControlerFactory;
import fr.litopia.controler.api.EmprunControler;
import fr.litopia.model.Station;
import fr.litopia.utils.ReadingConsole;
import fr.litopia.views.struct.api.ViewContext;
import fr.litopia.views.struct.impl.ViewContextImpl;
import fr.litopia.views.struct.impl.ViewImpl;

import java.util.HashMap;

public class EmprunView extends ViewImpl {
    private Station station;
    private EmprunNonAboView emprunNonAboView;
    private EmprunControler emprunControler;
    private EmprunAboView emprunAboView;


    @Override
    protected void onContextSet() {
        this.station = (Station) this.viewContext.getContext().get("station");
        if(this.station == null) {
            System.out.println("Erreur : StationView n'a pas pu récupérer la station");
            ReadingConsole.readLine();
            this.stop();
        }
    }

    @Override
    protected void init() {
        emprunControler = ControlerFactory.getEmprunControler();

        HashMap<String, Object> context = new HashMap<>();
        context.put("station", this.station);
        context.put("emprunControler", emprunControler);

        ViewContext viewContext = new ViewContextImpl(this.name,context);

        emprunNonAboView = new EmprunNonAboView();
        emprunNonAboView.setContext(viewContext);

        emprunAboView = new EmprunAboView();
        emprunAboView.setContext(viewContext);
    }

    @Override
    protected void display() {
        if(emprunControler.peutEmprunter(this.station)) {
            displayEmprun();
        } else {
            displayError();
        }
    }

    private void displayEmprun(){
        System.out.println("=================");
        System.out.println("EMPRUNTER UN VÉLO");
        System.out.println("==================");
        System.out.println("1. Vous êtes abonné ?");
        System.out.println("2. Vous n'êtes pas abonné ?");
        System.out.println("3. Retour");
        System.out.println("Votre choix : ");
        Integer choice = ReadingConsole.readInt(1,3);
        switch (choice){
            case 1 -> emprunAboView.run();
            case 2 -> emprunNonAboView.run();
            case 3 -> this.close();
        }
    }

    private void displayError(){
        System.out.println("=================");
        System.out.println("EMPRUNTER UN VÉLO");
        System.out.println("==================");
        System.out.println("Vous ne pouvez plus emprunter de vélo à cette station");
        System.out.println("Appuyez sur une touche pour revenir au menu");
        ReadingConsole.readLine();
        this.close();
    }

    @Override
    protected void close() {

    }
}
