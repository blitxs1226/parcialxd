

package arbolavl;


import java.util.Arrays;



public class ArbolAVL {
    NodoArbolAVL raiz;
 
    public ArbolAVL(){
        raiz = null;
    }
   
/**
 * permite buscar dentro de los nodos del arbol
 * @param d valor a buscar
 * @param r raiz
 * @return nos retorna el nodo si se encuentra el valor en el arbol sino, nulo
 */    
 
    public NodoArbolAVL buscar(int d, NodoArbolAVL r){
        int encontrado=0;
        if(raiz==null || r==null){
            return null;
        }else
         if(r.dato == d){
            
            encontrado=1;
          
        }else if(d>r.dato){
            return buscar(d, r.hijoDerecho);
        }else if(d<r.dato){
            return buscar(d, r.hijoIzquierdo);
        }else{
                encontrado=0;
                }
         
         if(encontrado==1){
             return r;
         }else{
             return null;
         }
    }
   
    
    /**
     * factor de equilibrio
     * @param x
     * @return x.fe
     */
    public int obtenerFE(NodoArbolAVL x){
        if(x == null)
            return -1;
        else
            return x.fe;
    }
    //Propiedades del orden
    //Rotacion de nodo izquierda
    public NodoArbolAVL rotacionIzquierda(NodoArbolAVL c){
        NodoArbolAVL auxiliar = c.hijoIzquierdo;
        c.hijoIzquierdo = auxiliar.hijoDerecho;
        auxiliar.hijoDerecho = c;
        c.fe = Math.max(obtenerFE(c.hijoIzquierdo) + 1, obtenerFE(c.hijoDerecho) + 1);
        auxiliar.fe = Math.max(obtenerFE(auxiliar.hijoIzquierdo) + 1, obtenerFE(auxiliar.hijoDerecho) + 1);
        return auxiliar;
    }
    //Rotacion de nodo derecha
    public NodoArbolAVL rotacionDerecha(NodoArbolAVL c){
        NodoArbolAVL auxiliar = c.hijoDerecho;
        c.hijoDerecho = auxiliar.hijoIzquierdo;
        auxiliar.hijoIzquierdo = c;
        c.fe = Math.max(obtenerFE(c.hijoIzquierdo) + 1, obtenerFE(c.hijoDerecho) + 1);
        auxiliar.fe = Math.max(obtenerFE(auxiliar.hijoIzquierdo) + 1, obtenerFE(auxiliar.hijoDerecho) + 1);
        return auxiliar;
    }
    //Rotacion de nodo doble izquierda
    public NodoArbolAVL rotacionDobleIzquierda(NodoArbolAVL c){
        NodoArbolAVL temporal;
        c.hijoIzquierdo = rotacionDerecha(c.hijoIzquierdo);
        temporal = rotacionIzquierda(c);
        return temporal;
    }
    //Rotacion de nodo doble derecha
    public NodoArbolAVL rotacionDobleDerecha(NodoArbolAVL c){
        NodoArbolAVL temporal;
        c.hijoDerecho = rotacionIzquierda(c.hijoDerecho);
        temporal = rotacionDerecha(c);
        return temporal;
    }
    // logica de insercion
    /**
     * en este apartado logramos denotar principalmente el pocicionamiento e 
     * insercion de los datos, ya que se ingresa el nodo el cual seria el padre
     * luego se revisa si este tiene algun hijo y de caso contrario pasa a 
     * pocisionarse ya sea en la izquierda o derecha de a cuerdo el valor 
     * del nuevo dato ingresado tanto como a reestructurarse de ser necesario
     * @param nuevo nuevo valor
     * @param subArb sub raiz anterior equisde.
     * @return 
     */
    public NodoArbolAVL insertarAVL(NodoArbolAVL nuevo, NodoArbolAVL subArb){
        NodoArbolAVL nuevoPadre = subArb;
        
        if(nuevo.dato < subArb.dato){
            if(subArb.hijoIzquierdo == null){
                subArb.hijoIzquierdo = nuevo;
            }else
            {
                subArb.hijoIzquierdo = insertarAVL(nuevo, subArb.hijoIzquierdo);
                if((obtenerFE(subArb.hijoIzquierdo) - obtenerFE(subArb.hijoDerecho)== 2))
                    if(nuevo.dato < subArb.hijoIzquierdo.dato)
                        nuevoPadre = rotacionIzquierda(subArb);
                    else
                        nuevoPadre = rotacionDobleIzquierda(subArb);
            }                                        
        }else if(nuevo.dato > subArb.dato){
            if(subArb.hijoDerecho == null){
                subArb.hijoDerecho = nuevo;
            } else
            {
                subArb.hijoDerecho = insertarAVL(nuevo, subArb.hijoDerecho);
                if((obtenerFE(subArb.hijoDerecho) - obtenerFE(subArb.hijoIzquierdo) == 2))
                    if(nuevo.dato > subArb.hijoDerecho.dato)
                        nuevoPadre = rotacionDerecha(subArb);
                    else
                        nuevoPadre = rotacionDobleDerecha(subArb);
            }        
        }
        
        if(subArb.hijoIzquierdo == null && subArb.hijoDerecho != null)
            subArb.fe = subArb.hijoDerecho.fe + 1;
        else if(subArb.hijoDerecho == null && subArb.hijoIzquierdo != null)
            subArb.fe = subArb.hijoIzquierdo.fe + 1;
        else
            subArb.fe = Math.max(obtenerFE(subArb.hijoIzquierdo), obtenerFE(subArb.hijoDerecho))+1;

        return nuevoPadre;
    }
    
    /**
     * recibe el numero que el usuario desee ingresar al arbol
     * @param d 
     */
    public void insertar (int d){
        
        NodoArbolAVL nuevo = new NodoArbolAVL(d);
       
        if(raiz == null)
            raiz = nuevo; 
        else{
            try{
                NodoArbolAVL aux=null;
                aux=buscar(d,raiz);
          if(aux==null){
                 raiz = insertarAVL(nuevo, raiz);
            
          }else{
                aux.duplicados.add((int)nuevo.dato);
             
            }
            }catch(Exception c){
                System.out.println("Error:"+c.getMessage());
            }
           
          
        }
             
           
        
      
    }
    //listas
        public static String str="";
      /**
       * en esta funcion recibe un parametro
       * @param n este nodo nos sirve para recorrer de manera preorder el arbol y retorna
       * @return el string con la informacion de las listas asi como que nodos tienen elementos 
       * y se maneja la excepcion
       * @throws InterruptedException 
       */  
        
        
    public String mostrarlistas(NodoArbolAVL n) throws InterruptedException{
        
        if(n != null)
        {
            
           if(n.duplicados.size()>0){
               
                str+="El Nodo: " +n.dato+ "  Tiene "+n.duplicados.size()+"  Duplicados que son : "+Arrays.asList(n.duplicados)+"\n";
               
           }else{
                 str+= "El Nodo: " +n.dato+ "  Tiene "+n.duplicados.size()+"\n";
              
           }
           
               mostrarlistas(n.hijoIzquierdo);
            mostrarlistas(n.hijoDerecho);}
           
          
    return str;
    }
    
    
     
}

