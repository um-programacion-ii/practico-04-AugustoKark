# prog2.2024.practico04

Consigna:

Siguiendo la consigna del TP 3 se deben reflejar los siguientes cambios:

1. Recordar agregar una regla a la branch main para que no se pueda hacer push directamente, igual que en los anteriores.

2. Cada chef tendrá una Despensa de Ingrediente's a su disposición, pero habrá que crear una clase nueva Estante que implemente Despensable donde se guardarán los Utensilio's para cocinar, hay que implementar el patrón Singleton sobre esta clase para asegurarse que haya sólo 1 instancia, o sea, los Utensilio's se comparten entre todos los Chef's

3. Considerando que los Utensilio's se comparten entre todos los Chef's deberá implementarse un esquema sincronizado para el caso en que 2 o mas Chef's necesiten la utilización del mismo recurso.

4. Si un Chef encuentra un recurso bloqueado podrá continuar con otra Receta pendiente hasta que se desocupe el recurso que necesita.

5. Construir una rama diferente a partir de lo anterior donde se implemente el mismo proyecto pero usando Maven
