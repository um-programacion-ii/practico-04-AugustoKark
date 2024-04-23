import entidades.*;
import excepciones.StockInsuficienteException;
import excepciones.VidaUtilInsuficienteException;
import interfaces.Despensable;
import recetas.*;
import services.CocinaService;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

import static entidades.Despensa.crearDespensableMap;

public class Main {
        public static void main(String[] args) throws StockInsuficienteException, VidaUtilInsuficienteException {


            Random random = new Random();

            // Crear despensas
            Despensa despensa1 = new Despensa(crearDespensableMap());
            Despensa despensa2 = new Despensa(crearDespensableMap());
            Despensa despensa3 = new Despensa(crearDespensableMap());
            Despensa despensa4 = new Despensa(crearDespensableMap());
            Despensa despensa5 = new Despensa(crearDespensableMap());
            Despensa despensa6 = new Despensa(crearDespensableMap());
            Despensa despensa7 = new Despensa(crearDespensableMap());
            Despensa despensa8 = new Despensa(crearDespensableMap());


            // Crear utensilios
            Utensilio bowl = new Utensilio("Bowl", 50);
            Utensilio batidora = new Utensilio("Batidora", 50);
            Utensilio horno = new Utensilio("Horno", 50);
            Utensilio sarten = new Utensilio("Sarten", 500);
            Utensilio cuchara = new Utensilio("Cuchara", 100);
            Utensilio cuchillo = new Utensilio("Cuchillo", 100);
            Utensilio tenedor = new Utensilio("Tenedor", 100);
            Utensilio cucharon = new Utensilio("Cucharon", 100);
            Utensilio olla = new Utensilio("Olla", 10);
            Utensilio espatula = new Utensilio("Espatula", 100);


            Estante estante = Estante.getInstancia();
            estante.agregarUtensilio(bowl);
            estante.agregarUtensilio(batidora);
            estante.agregarUtensilio(horno);
            estante.agregarUtensilio(sarten);
            estante.agregarUtensilio(cuchara);
            estante.agregarUtensilio(cuchillo);
            estante.agregarUtensilio(tenedor);
            estante.agregarUtensilio(cucharon);
            estante.agregarUtensilio(olla);
            estante.agregarUtensilio(espatula);

            // Crear listas de recetas
            List<Receta> recetas1 = new ArrayList<>();
            List<Receta> recetas2 = new ArrayList<>();
            List<Receta> recetas3 = new ArrayList<>();
            List<Receta> recetas4 = new ArrayList<>();
            List<Receta> recetas5 = new ArrayList<>();
            List<Receta> recetas6 = new ArrayList<>();


            HuevoFrito huevoFrito = new HuevoFrito();
            Torta torta = new Torta();
            HuevoDuro huevoDuro = new HuevoDuro();

            recetas1.add(huevoFrito);
            // -------------------------------------------------------------------
            recetas2.add(huevoDuro);
            recetas2.add(torta);
            // -------------------------------------------------------------------

            recetas3.add(torta);


            // Crear cocineros
            List<Chef> chefsSemana = new ArrayList<>();
            Chef chef1 = new Chef("Juan", 3, despensa1, recetas1);
            Chef chef2 = new Chef("Pedro", 3, despensa2, recetas2);
            Chef chef3 = new Chef("Maria", 3, despensa3, recetas3);

            chefsSemana.add(chef1);
            chefsSemana.add(chef2);
            chefsSemana.add(chef3);

            Brownie brownie = new Brownie();
            Pasta pasta = new Pasta();
            Torta torta1 = new Torta();
            EnsaladaCesar ensaladaCesar = new EnsaladaCesar();
            SopaDeVerduras sopaDeVerduras = new SopaDeVerduras();
            // -------------------------------------------------------------------

            recetas4.add(brownie);
            recetas4.add(pasta);
            // -------------------------------------------------------------------

            recetas5.add(ensaladaCesar);
            recetas5.add(sopaDeVerduras);
            // -------------------------------------------------------------------

            recetas6.add(sopaDeVerduras);
            recetas6.add(brownie);



            List<Chef> chefsFinde = new ArrayList<>();
            Chef chef4 = new Chef("Linguini", 3, despensa4, recetas1);
            Chef chef5 = new Chef("Pedrito Electrocutador", 3, despensa5, recetas6);
            Chef chef6 = new Chef("Luquitas Rodriguez", 3, despensa6, recetas5);
            Chef chef7 = new Chef("Mariano Closs", 3, despensa7, recetas3);
            Chef chef8 = new Chef("Fernando Niembro", 3, despensa8, recetas4);
            chefsFinde.add(chef4);
            chefsFinde.add(chef5);
            chefsFinde.add(chef6);
            chefsFinde.add(chef7);
            chefsFinde.add(chef8);


            int finDeSemana = random.nextInt(2);
//            int finDeSemana = 0;
            if (finDeSemana == 0) {
                System.out.println("Es un dia de semana");
                ExecutorService executor = Executors.newFixedThreadPool(3);
                List<Future<String>> futures = new ArrayList<>();
                for (Chef chef : chefsSemana) {

                    Future<String> labureChefsito = executor.submit(chef);
                    futures.add(labureChefsito);
                }
                for (Future<String> future : futures) {
                    try {
                        System.out.println(future.get());
//                        System.out.println(labureChefsito.get());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                executor.shutdown();


            }
            if (finDeSemana == 1) {
                System.out.println("Es fin de semana");
                ExecutorService executor = Executors.newFixedThreadPool(5);
                for (Chef chef : chefsFinde) {
                    Future<String> labureChefsito = executor.submit(chef);
                    try {
                        System.out.println(labureChefsito.get());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                executor.shutdown();
            }
        }










}














