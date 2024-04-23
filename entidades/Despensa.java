package entidades;

import excepciones.StockInsuficienteException;
import excepciones.VidaUtilInsuficienteException;
import interfaces.Cocinable;
import interfaces.Despensable;


import java.util.HashMap;
import java.util.Map;

public class Despensa implements Cocinable {

    private Map<String, Despensable> despensableMap;

    public Despensa(Map<String, Despensable> despensableMap) {
        this.despensableMap = despensableMap;
    }

    public void sacar(String nombre, int cantidad) throws StockInsuficienteException, VidaUtilInsuficienteException {
        Despensable despensable = despensableMap.get(nombre);

        if (despensable != null) {
            try {
                despensable.sacar(cantidad);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        } else {
            System.out.println("No se encontro el despensable: " + nombre);
        }
    }


    public boolean contieneSuficienteDespensable(Despensable despensable) {
        Despensable despensaItem = despensableMap.get(despensable.getNombre());
        return despensaItem != null && despensaItem.getCantidadDisponible() >= despensable.getCantidadDisponible();
    }

    public static Map<String, Despensable> crearDespensableMap() {
        // Crear ingredientes
        Ingrediente huevo = new Ingrediente("Huevo", 4);
        Ingrediente harina = new Ingrediente("Harina", 500);
        Ingrediente azucar = new Ingrediente("Azucar", 250);
        Ingrediente sal = new Ingrediente("Sal", 100);
        Ingrediente aceite = new Ingrediente("Aceite", 550);
        Ingrediente agua = new Ingrediente("Agua", 8000);
        Ingrediente leche = new Ingrediente("Leche", 1000);
        Ingrediente manteca = new Ingrediente("Manteca", 600);
        Ingrediente queso = new Ingrediente("Queso", 100);
        Ingrediente pollo = new Ingrediente("Pollo", 1000);
        Ingrediente polvoDeHornear = new Ingrediente("Polvo de hornear", 100);
        Ingrediente zanahoria = new Ingrediente("Zanahoria", 100);
        Ingrediente cebolla = new Ingrediente("Cebolla", 100);
        Ingrediente papa = new Ingrediente("Papa", 100);
        Ingrediente pasta = new Ingrediente("Pasta", 100);
        Ingrediente tomate = new Ingrediente("Tomate", 100);

        // Crear despensableMap
        Map<String, Despensable> despensableMap = new HashMap<>();
        despensableMap.put(huevo.getNombre(), huevo);
        despensableMap.put(harina.getNombre(), harina);
        despensableMap.put(azucar.getNombre(), azucar);
        despensableMap.put(sal.getNombre(), sal);
        despensableMap.put(aceite.getNombre(), aceite);
        despensableMap.put(agua.getNombre(), agua);
        despensableMap.put(leche.getNombre(), leche);
        despensableMap.put(manteca.getNombre(), manteca);
        despensableMap.put(queso.getNombre(), queso);
        despensableMap.put(pollo.getNombre(), pollo);
        despensableMap.put(polvoDeHornear.getNombre(), polvoDeHornear);
        despensableMap.put(zanahoria.getNombre(), zanahoria);
        despensableMap.put(cebolla.getNombre(), cebolla);
        despensableMap.put(papa.getNombre(), papa);
        despensableMap.put(pasta.getNombre(), pasta);
        despensableMap.put(tomate.getNombre(), tomate);

        return despensableMap;
    }



}


