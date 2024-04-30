package services;
import entidades.Estante;
import entidades.Utensilio;
import interfaces.Despensable;

import java.util.List;

public class EstanteService {

    private Estante estante;

    public EstanteService(Estante estante) {
        this.estante = estante;
    }

    public boolean verificarVidaUtil(List<Despensable> utensilios) {
        for (Despensable utensilio : utensilios) {
            if (!estante.contieneSuficienteUtensilio((Utensilio) utensilio)) {
                System.out.println("no hay suficiente vida util de "+ utensilio.getNombre() + " en el estante.");
                return false;
            }
        }
        return true;
    }
}