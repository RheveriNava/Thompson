package clases;

import java.util.ArrayList;
import java.util.TreeSet;
import java.util.Stack;

public class algoritmoThompson{
    private Automata afn;
    private String expre;
      
    public algoritmoThompson(String expre) {
        this.expre = expre;
    }
      
    // Metodo que construye el autómata
    public void constructor(String[] alfabeto){
        try {
        Stack<Automata> pilaAFN = new Stack<Automata>();
        //Crea un automata por cada operacion
        for (Character c : this.expre.toCharArray()) {
            switch(c){
                case '*':
                     Automata kleene = cerraduraKleene((Automata) pilaAFN.pop());
                     pilaAFN.push(kleene);
                     this.afn=kleene;
                    break;
                case '+':
                    Automata positiva = cerraduraPositiva((Automata) pilaAFN.pop());
                    pilaAFN.push(positiva);
                    this.afn=positiva;
                   break;
                case '?':
                   Automata exclusiva = cerraduraExclusiva((Automata) pilaAFN.pop());
                   pilaAFN.push(exclusiva);
                   this.afn=exclusiva;
                  break;
                case '.':
                    Automata concat_param1 = (Automata)pilaAFN.pop();
                    Automata concat_param2 = (Automata)pilaAFN.pop();
                    Automata concat_result = concatenacion(concat_param1,concat_param2);
                   
                    pilaAFN.push(concat_result);
                    this.afn=concat_result;
                    break;
                    
                case '|':
                    
                    Automata union_param1 = (Automata)pilaAFN.pop();
                    Automata union_param2 = (Automata)pilaAFN.pop();
                    Automata union_result = union(union_param1,union_param2);
                   
                    
                    pilaAFN.push(union_result);
                   
                    this.afn = union_result;
                    break;
                    
                default:
                    //crear un automata con cada simbolo
                    Automata simple = afnSimple(Character.toString(c));
                    pilaAFN.push(simple);
                    this.afn=simple;
                    
                    
            }
        }
        this.afn.crearAlfabeto(alfabeto,expre);
        this.afn.setTipo("AFN");
        
        }catch(Exception e){
            System.out.println("Expresión mal ingresada");
        }
    
                
    }
    
    // Meotodo para crear un AFN simple con un simbolo
    public Automata afnSimple(String simboloexpre)
    {
        Automata automataFN = new Automata();
        //definir los nuevos estados
        Estado inicial = new Estado(0);
        Estado aceptacion = new Estado(1);
        //crear una transicion unica con el simbolo
        Transicion tran = new Transicion(inicial, aceptacion,  simboloexpre);
        inicial.setTransiciones(tran);
        //agrega los estados creados
        automataFN.addEstados(inicial);
        automataFN.addEstados(aceptacion);
        //colocar los estados iniciales y de acpetacion
        automataFN.setInicial(inicial);
        automataFN.addEstadosAceptacion(aceptacion);
        automataFN.setLenguajeR(simboloexpre);
        return automataFN;
       
    }   
    // Metodo unario para crear un automataFN con cerradura de Kleene (*)
    public Automata cerraduraKleene(Automata automataFN)
    {
        Automata afn_kleene = new Automata();
        
        //se crea un nuevo estado inicial
        Estado nuevoInicio = new Estado(0);
        afn_kleene.addEstados(nuevoInicio);
        afn_kleene.setInicial(nuevoInicio);
        
        //agregar todos los estados intermedio
        for (int i=0; i < automataFN.getEstados().size(); i++) {
            Estado tmp = (Estado) automataFN.getEstados().get(i);
            tmp.setId(i + 1);
            afn_kleene.addEstados(tmp);
        }
        
        //Se crea un nuevo estado de aceptacion
        Estado nuevoFin = new Estado(automataFN.getEstados().size() + 1);
        afn_kleene.addEstados(nuevoFin);
        afn_kleene.addEstadosAceptacion(nuevoFin);
        
        //definir estados clave para realizar la cerraduras
        Estado anteriorInicio = automataFN.getInicial();
        
        ArrayList<Estado> anteriorFin = automataFN.getEstadosAceptacion();
        
        
        // agregar transiciones desde el nuevo estado inicial
        nuevoInicio.getTransiciones().add(new Transicion(nuevoInicio, anteriorInicio, Automata.EPSILON));
        nuevoInicio.getTransiciones().add(new Transicion(nuevoInicio, nuevoFin, Automata.EPSILON));

        // agregar transiciones desde el anterior estado final
        for (int i =0; i<anteriorFin.size();i++){
            anteriorFin.get(i).setTransiciones(new Transicion(anteriorFin.get(i), anteriorInicio, Automata.EPSILON));
            anteriorFin.get(i).setTransiciones(new Transicion(anteriorFin.get(i), nuevoFin, Automata.EPSILON));
        }
        afn_kleene.setAlfabeto(automataFN.getAlfabeto());
        afn_kleene.setLenguajeR(automataFN.getLenguajeR() + "*");
        return afn_kleene;
    }

     // Metodo unario para crear un automataFN con cerradura positiva (*)
    public Automata cerraduraPositiva(Automata automataFN)
    {
        Automata afn_positivo = new Automata();
        
        //se crea un nuevo estado inicial
        Estado nuevoInicio = new Estado(0);
        afn_positivo.addEstados(nuevoInicio);
        afn_positivo.setInicial(nuevoInicio);
        
        //agregar todos los estados intermedio
        for (int i=0; i < automataFN.getEstados().size(); i++) {
            Estado tmp = (Estado) automataFN.getEstados().get(i);
            tmp.setId(i + 1);
            afn_positivo.addEstados(tmp);
        }
        
        //Se crea un nuevo estado de aceptacion
        Estado nuevoFin = new Estado(automataFN.getEstados().size() + 1);
        afn_positivo.addEstados(nuevoFin);
        afn_positivo.addEstadosAceptacion(nuevoFin);
        
        //definir estados clave para realizar la cerraduras
        Estado anteriorInicio = automataFN.getInicial();
        
        ArrayList<Estado> anteriorFin = automataFN.getEstadosAceptacion();
        
        // agregar transiciones desde el nuevo estado inicial
        nuevoInicio.getTransiciones().add(new Transicion(nuevoInicio, anteriorInicio, Automata.EPSILON));
        
        // agregar transiciones desde el anterior estado final
        for (int i =0; i<anteriorFin.size();i++){
            anteriorFin.get(i).getTransiciones().add(new Transicion(anteriorFin.get(i), automataFN.getInicial(),Automata.EPSILON));
            anteriorFin.get(i).getTransiciones().add(new Transicion(anteriorFin.get(i), nuevoFin, Automata.EPSILON));
        }
        afn_positivo.setAlfabeto(automataFN.getAlfabeto());
        afn_positivo.setLenguajeR(automataFN.getLenguajeR() + "+");
        return afn_positivo;
    }

    // Metodo unario para crear un automataFN con cerradura exclusiva (?)
    public Automata cerraduraExclusiva(Automata automataFN)
    {
        Automata afn_exclusivo = new Automata();
        
        //se crea un nuevo estado inicial
        Estado nuevoInicio = new Estado(0);
        afn_exclusivo.addEstados(nuevoInicio);
        afn_exclusivo.setInicial(nuevoInicio);
        
        //agregar todos los estados intermedio
        for (int i=0; i < automataFN.getEstados().size(); i++) {
            Estado tmp = (Estado) automataFN.getEstados().get(i);
            tmp.setId(i + 1);
            afn_exclusivo.addEstados(tmp);
        }
        
        //Se crea un nuevo estado de aceptacion
        Estado nuevoFin = new Estado(automataFN.getEstados().size() + 1);
        afn_exclusivo.addEstados(nuevoFin);
        afn_exclusivo.addEstadosAceptacion(nuevoFin);
        
        //definir estados clave para realizar la cerraduras
        Estado anteriorInicio = automataFN.getInicial();
        
        ArrayList<Estado> anteriorFin    = automataFN.getEstadosAceptacion();
        
        // agregar transiciones desde el nuevo estado inicial
        nuevoInicio.getTransiciones().add(new Transicion(nuevoInicio, anteriorInicio, Automata.EPSILON));
        nuevoInicio.getTransiciones().add(new Transicion(nuevoInicio, nuevoFin, Automata.EPSILON));
        
        // agregar transiciones desde el anterior estado final
        for (int i =0; i<anteriorFin.size();i++){
            anteriorFin.get(i).getTransiciones().add(new Transicion(anteriorFin.get(i), nuevoFin, Automata.EPSILON));
        }
        afn_exclusivo.setAlfabeto(automataFN.getAlfabeto());
        afn_exclusivo.setLenguajeR(automataFN.getLenguajeR() + "?");
        return afn_exclusivo;
    }

    // Metodo binario para concatenar dos automatas
   public Automata concatenacion(Automata AFN1, Automata AFN2){
       
       Automata afn_concat = new Automata();
            
        //se utiliza como contador para los estados del nuevo automata
        int i=0;
        //agregar los estados del primer automata
        for (i=0; i < AFN2.getEstados().size(); i++) {
            Estado tmp = (Estado) AFN2.getEstados().get(i);
            tmp.setId(i);
            //se define el estado inicial
            if (i==0){
                afn_concat.setInicial(tmp);
            }
            if (i == AFN2.getEstados().size()-1){
                //se utiliza un ciclo porque los estados de aceptacion son un array
                for (int k = 0;k<AFN1.getInicial().getTransiciones().size();k++)
                {
                    tmp.setTransiciones(new Transicion((Estado) tmp,AFN1.getInicial().getTransiciones().get(k).getFin(),AFN1.getInicial().getTransiciones().get(k).getSimbolo()));
                }
                int indexTransicion = AFN1.getEstados().indexOf(AFN1.getInicial());
                AFN1.getEstados().remove(indexTransicion);
            }
            afn_concat.addEstados(tmp);

        }
        //termina de agregar los estados y transiciones del segundo automata
        for (int j =0;j<AFN1.getEstados().size();j++){
            Estado tmp = (Estado) AFN1.getEstados().get(j);
            tmp.setId(i);

            //define el ultimo con estado de aceptacion
            if (AFN1.getEstados().size()-1==j)
                afn_concat.addEstadosAceptacion(tmp);
             afn_concat.addEstados(tmp);
            i++;
        }
       
        TreeSet<String> alfabeto = new TreeSet<String>();
        alfabeto.addAll(AFN1.getAlfabeto());
        alfabeto.addAll(AFN2.getAlfabeto());
        afn_concat.setAlfabeto(alfabeto);
        afn_concat.setLenguajeR(AFN1.getLenguajeR() + AFN2.getLenguajeR()); 
        
       return afn_concat;
   }
   
    // Método binario para unir dos automatas (|)
    public Automata union(Automata AFN2, Automata AFN1){
        Automata afn_union = new Automata();
        //se crea un nuevo estado inicial
        Estado nuevoInicio = new Estado(0);
        //se crea una transicion del nuevo estado inicial al primer automata
        nuevoInicio.setTransiciones(new Transicion(nuevoInicio,AFN2.getInicial(),Automata.EPSILON));

        afn_union.addEstados(nuevoInicio);
        afn_union.setInicial(nuevoInicio);
        int i=0;//llevar el contador del identificador de estados
        //agregar los estados del segundo automata
        for (i=0; i < AFN1.getEstados().size(); i++) {
            Estado tmp = (Estado) AFN1.getEstados().get(i);
            tmp.setId(i + 1);
            afn_union.addEstados(tmp);
        }
        //agregar los estados del primer automata
        for (int j=0; j < AFN2.getEstados().size(); j++) {
            Estado tmp = (Estado) AFN2.getEstados().get(j);
            tmp.setId(i + 1);
            afn_union.addEstados(tmp);
            i++;
        }
        
        //se crea un nuevo estado final
        Estado nuevoFin = new Estado(AFN1.getEstados().size() +AFN2.getEstados().size()+ 1);
        afn_union.addEstados(nuevoFin);
        afn_union.addEstadosAceptacion(nuevoFin);
        
       
        Estado anteriorInicio = AFN1.getInicial();
        ArrayList<Estado> anteriorFin    = AFN1.getEstadosAceptacion();
        ArrayList<Estado> anteriorFin2    = AFN2.getEstadosAceptacion();
        
        // agregar transiciones desde el nuevo estado inicial
        nuevoInicio.getTransiciones().add(new Transicion(nuevoInicio, anteriorInicio, Automata.EPSILON));
        
         // agregar transiciones desde el anterior AFN 1 al estado final
        for (int k =0; k<anteriorFin.size();k++)
            anteriorFin.get(k).getTransiciones().add(new Transicion(anteriorFin.get(k), nuevoFin, Automata.EPSILON));
         // agregar transiciones desde el anterior AFN 2 al estado final
        for (int k =0; k<anteriorFin.size();k++)
            anteriorFin2.get(k).getTransiciones().add(new Transicion(anteriorFin2.get(k),nuevoFin,Automata.EPSILON));
        
        TreeSet<String> alfabeto = new TreeSet<String>();
        alfabeto.addAll(AFN1.getAlfabeto());
        alfabeto.addAll(AFN2.getAlfabeto());
        afn_union.setAlfabeto(alfabeto);
        afn_union.setLenguajeR(AFN1.getLenguajeR() + "|" + AFN2.getLenguajeR()); 
        return afn_union;
    }
    
    
    public Automata getAfn() {
        return this.afn;
    }

    public void setAfn(Automata afn) {
        this.afn = afn;
    }

    public String getRegex() {
        return expre;
    }

    public void setRegex(String expre) {
        this.expre = expre;
    }
}