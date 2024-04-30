package services;

import entidades.*;
import excepciones.StockInsuficienteException;
import interfaces.Despensable;
import excepciones.VidaUtilInsuficienteException;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class CocinaService {

    public static CocinaService instancia = null;


    private CocinaService() {
    }



    public static  CocinaService getInstance() {
        if (instancia == null) {
            instancia = new CocinaService();
        }
        return instancia;
    }


     public  synchronized void prepararPlatos(Receta receta,Despensa despensa, String nombreChef) throws StockInsuficienteException, VidaUtilInsuficienteException {

         List<Despensable> ingredientes = receta.getIngredientes();
         boolean sePuedePreparar = true;
         DespensaService despensaService = new DespensaService(despensa);
         EstanteService estanteService = new EstanteService(Estante.getInstancia());

         if (!despensaService.verificarStock(ingredientes)) {

             sePuedePreparar = false;
         }
         if (!estanteService.verificarVidaUtil(receta.getUtensilios())) {
             sePuedePreparar = false;
         }


         for (Despensable ingrediente : ingredientes) {
             despensa.sacar(ingrediente.getNombre(), ingrediente.getCantidadDisponible());
         }
         if (sePuedePreparar) {
             System.out.println("El Chef " + nombreChef +" ha preparado exitosamente la receta: " + receta.getClass().getSimpleName());
         } else {
             System.out.println("El Chef "+ nombreChef+" NO pudo preparar la receta: " + receta.getClass().getSimpleName());
         }

     }



}





















