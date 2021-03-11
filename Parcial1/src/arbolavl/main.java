/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbolavl;


import javax.swing.JOptionPane;

/**
 *
 * @author Rodrigo V.
 */

/**
 * este programa principalmente crea la clase NodoArbolAvl en donde se colocan los datos
 * factores de equilibrio, los hijos y la lista
 * @author Compufire
 */
public class main {

    public static void main(String[] args) throws InterruptedException {
        // TODO code application logic here
        ArbolAVL arbolAVL = new ArbolAVL();
       int numero,opc;
       do{
           numero=0;opc=0;
           numero=Integer.parseInt(JOptionPane.showInputDialog(null,"Ingrese un valor para agregarlo al arbol:","Insertar Valor ",JOptionPane.QUESTION_MESSAGE));
           arbolAVL.insertar(numero);
           opc=JOptionPane.showConfirmDialog(null, "Desea insertar otro elemento en el arbol?","Seguir?",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
       }while(opc!=JOptionPane.NO_OPTION);
        
        
       
    JOptionPane.showMessageDialog(null,arbolAVL.mostrarlistas(arbolAVL.raiz),"Recorriendo el arbol con listas en preorden",JOptionPane.INFORMATION_MESSAGE);
        
       
    }
    
   
    
}
