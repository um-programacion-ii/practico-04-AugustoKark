package entidades;

import excepciones.StockInsuficienteException;
import excepciones.VidaUtilInsuficienteException;
import interfaces.Despensable;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Estante implements Despensable {

    public static Estante instancia = null;
    private ConcurrentHashMap<String , Utensilio> utensilios;

    private Estante() {
        this.utensilios = new ConcurrentHashMap<>();

    }

    public static synchronized Estante getInstancia() {
        if (instancia == null) {
            instancia = new Estante();
        }
        return instancia;
    }

    public  synchronized void agregarUtensilio(Utensilio utensilio) {
        utensilios.put(utensilio.getNombre(), utensilio);
    }

    public synchronized void devolverUtensilio(Utensilio utensilio) {
        utensilios.put(utensilio.getNombre(), utensilio);
        System.out.println("Utensilio: " + utensilio.getNombre() + " devuelto");
    }

    public synchronized Utensilio sacarUtensilioEstante(String nombre) {
        Utensilio utensilio = utensilios.remove(nombre);
        if (utensilio != null) {
            System.out.println("Utensilio: " + nombre + " sacado del estante");
        }
        return utensilio;
    }

    public synchronized boolean contieneSuficienteUtensilio(Utensilio utensilio) {
        Utensilio estanteItem = utensilios.get(utensilio.getNombre());
        return estanteItem != null && estanteItem.getCantidadDisponible() >= utensilio.getCantidadDisponible();
    }








    @Override
    public void sacar(int cantidad) throws VidaUtilInsuficienteException, StockInsuficienteException {

    }

    @Override
    public String getNombre() {
        return "";
    }

    @Override
    public int getCantidadDisponible() {
        return 0;
    }
}
