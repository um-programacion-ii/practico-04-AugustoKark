package services;
import entidades.Despensa;
import interfaces.Despensable;

import java.util.List;

public class DespensaService {

    private Despensa despensa;

    public DespensaService(Despensa despensa) {
        this.despensa = despensa;
    }

    public boolean verificarStock(List<Despensable> ingredientes) {
        for (Despensable ingrediente : ingredientes) {
            if (!despensa.contieneSuficienteDespensable(ingrediente)) {
                return false;
            }}
        return true;
    }

    public boolean verificarVidaUtil(List<Despensable> utensilios) {
        for (Despensable utensilio : utensilios) {
            if (!despensa.contieneSuficienteDespensable(utensilio)) {
                System.out.println("no hay suficiente vida util de "+ utensilio.getNombre() + " en la despensa.");
                return false;
            }

        }
         return true;
    }

}