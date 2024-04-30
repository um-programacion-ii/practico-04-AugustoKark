package entidades;

import excepciones.StockInsuficienteException;
import excepciones.VidaUtilInsuficienteException;
import interfaces.Despensable;
import services.CocinaService;
import services.DespensaService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class Chef  implements Callable{
    private String nombre;

    private int estrellasMichelin;

    private Despensa despensa;

    private List<Receta> receta;

    public CocinaService cocinaService = CocinaService.getInstance();
    private List<Receta> recetasPendientes = new ArrayList<>();

    public Despensa getDespensa() {
        System.out.println("La despensa del chef ");
        return despensa;
    }

    public void setDespensa(Despensa despensa) {
        this.despensa = despensa;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEstrellasMichelin() {
        return estrellasMichelin;
    }

    public void setEstrellasMichelin(int estrellasMichelin) {
        this.estrellasMichelin = estrellasMichelin;
    }

    public Chef() {
    }

    public Chef(String nombre, int estrellasMichelin, Despensa despensa, List<Receta> receta) {
        this.nombre = nombre;
        this.estrellasMichelin = estrellasMichelin;
        this.despensa = despensa;
        this.receta = receta;
    }

    private void toStringChef(){
        System.out.println(" El Chef" + nombre + " posee " +estrellasMichelin+ " estrellas Michelin ");
    }





//@Override
//public synchronized Void call() {
//    Estante estante = Estante.getInstancia();
//    for (Receta receta : receta) {
//        try {
//            List<Despensable> utensilios = receta.getUtensilios();
//            List<Utensilio> utensiliosSacados = new ArrayList<>();
//            for (Despensable utensilio : utensilios) {
//                Utensilio utensilioSacado = estante.sacarUtensilioEstante(utensilio.getNombre());
//                if (utensilioSacado == null) {
//                    recetasPendientes.add(receta);
//                    // Devolver los utensilios que ya se habían sacado
//                    for (Utensilio utensilioDevolver : utensiliosSacados) {
//                        estante.devolverUtensilio(utensilioDevolver);
//                    }
//                    break;
//                } else {
//                    utensiliosSacados.add(utensilioSacado);
//                    if (utensiliosSacados.size() == utensilios.size()) {
//                        // Si se sacaron todos los utensilios, se puede cocinar la receta
//                        cocinaService.prepararPlatos(receta, this.despensa, this.nombre);
//                        for (Utensilio utensilioDevolver : utensiliosSacados) {
//                            estante.devolverUtensilio(utensilioDevolver);
//                        }
//                    }
//                }
//            }
//        } catch (StockInsuficienteException | VidaUtilInsuficienteException e) {
//            e.printStackTrace();
//        }
//    }
//
//    // Intentar recetas pendientes
//    for (Receta receta : recetasPendientes) {
//        try {
//            List<Despensable> utensilios = receta.getUtensilios();
//            for (Despensable utensilio : utensilios) {
//                Utensilio utensilioSacado = estante.sacarUtensilioEstante(utensilio.getNombre());
//                if (utensilioSacado != null) {
//                    cocinaService.prepararPlatos(receta, despensa, this.nombre);
//                    estante.devolverUtensilio(utensilioSacado);
//                }
//            }
//        } catch (StockInsuficienteException | VidaUtilInsuficienteException e) {
//            e.printStackTrace();
//        }
//    }
//    System.out.println("--------------------------------------------------------------------");
//    return null;
//}

    @Override
    public synchronized Void call() {
        Estante estante = Estante.getInstancia();
        for (Receta receta : receta) {
            try {
                List<Despensable> utensilios = receta.getUtensilios();
                List<Utensilio> utensiliosSacados = new ArrayList<>();
                for (Despensable utensilio : utensilios) {
                    if (estante.contieneSuficienteUtensilio((Utensilio) utensilio)) {
                        Utensilio utensilioSacado = estante.sacarUtensilioEstante(utensilio.getNombre());
                        utensiliosSacados.add(utensilioSacado);
                    } else {
                        recetasPendientes.add(receta);
                        // Devolver los utensilios que ya se habían sacado
                        for (Utensilio utensilioDevolver : utensiliosSacados) {
                            estante.devolverUtensilio(utensilioDevolver);
                        }
                        break;
                    }
                }
                if (utensiliosSacados.size() == utensilios.size()) {
                    // Si se sacaron todos los utensilios, se puede cocinar la receta
                    cocinaService.prepararPlatos(receta, this.despensa, this.nombre);
                    for (Utensilio utensilioDevolver : utensiliosSacados) {
                        estante.devolverUtensilio(utensilioDevolver);
                    }
                }
            } catch (StockInsuficienteException | VidaUtilInsuficienteException e) {
                e.printStackTrace();
            }
        }

        // Intentar recetas pendientes
        for (Receta receta : recetasPendientes) {
            try {
                List<Despensable> utensilios = receta.getUtensilios();
                for (Despensable utensilio : utensilios) {
                    Utensilio utensilioSacado = estante.sacarUtensilioEstante(utensilio.getNombre());
                    if (utensilioSacado != null) {
                        cocinaService.prepararPlatos(receta, despensa, this.nombre);
                        estante.devolverUtensilio(utensilioSacado);
                    }
                }
            } catch (StockInsuficienteException | VidaUtilInsuficienteException e) {
                e.printStackTrace();
            }
        }
        System.out.println("--------------------------------------------------------------------");
        return null;
    }
}





