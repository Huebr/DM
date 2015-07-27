package Console;

/**
 * Created by pedro on 25/07/2015.
 */

import Concept.Conceito;
import weka.core.Attribute;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Scanner;


import DM_File_Handler.DMHandler;

public class Console {
    static DMHandler arquivo;
    public static void main(String[] args) {




        System.out.println("DataSet program version v0.1");
        while (!carregarDataSet());
        carregaConcept();


    }
    public static void carregaConcept(){
        int op;
        boolean saida = true;
        String nomeConceito;


        Scanner sc = new Scanner(System.in);

        System.out.println("Escolha do conceito");
        System.out.println("Digite 1 para importar o arquivo.");
        System.out.println("Digite 2 para inserir um novo Conceito");

        while (saida) {
            op=sc.nextInt();
            switch (op) {
                case 1:
                    saida = false;
                    break;
                case 2:
                    saida = false;
                    System.out.println("Digite o Nome do Conceito:");
                    nomeConceito=sc.nextLine();

                    System.out.println("Dado os Atributos");

                    for (Enumeration<Attribute> e = arquivo.getInstances().enumerateAttributes(); e.hasMoreElements();)
                        System.out.println(e.nextElement());
                    insereConceito();
                    break;
                default:
                    System.out.println("Numero invalido!!");
                    break;
            }
        }
    }
    public static boolean carregarDataSet() {
        Scanner sc = new Scanner(System.in);
        String fileName;

        System.out.print("Informe o diretorio do DataSet: ");
        fileName = sc.nextLine();

        try {
            arquivo = new DMHandler(fileName);
            // arquivo.imprimirDataset();
        } catch (Exception e) {
            System.out.println("erro no caminho");
            return false;
        }
        return true;
    }
    //lembrar
    public static void insereConceito(){
        String atribConceito;
        String tempoAttribute;
        String meuAtributo;
        boolean loopAttribute;
        boolean verification=false;
        Attribute atributoTestado=new Attribute(null);
        ArrayList<String> atributeValues=new ArrayList<String>();
        ArrayList<Attribute> atributos = new ArrayList<Attribute>();
        Scanner sc = new Scanner(System.in);

        System.out.println("Digite o Campo a ser analisado(Digite quit para sair).");
        while(!(atribConceito= sc.nextLine()).equals("quit")){
            for (Enumeration<Attribute> e = arquivo.getInstances().enumerateAttributes(); e.hasMoreElements();){
                atributoTestado=e.nextElement();
                if(atributoTestado.name().equals(atribConceito)){
                    verification=true;
                    break;
                }
            }
            if(!verification){
                System.out.println("Campo invalido");
            }
            else{
                System.out.println("Digite valores dos atributos(Digite q para terminar)");
                while(true){
                    tempoAttribute= sc.nextLine();
                    if(tempoAttribute.equals("q")){
                        break;
                    }else{//Seria bom colocar algo  para dizer se o valor é invalido
                        for(Enumeration<Object>s=atributoTestado.enumerateValues();s.hasMoreElements();){
                            if(s.nextElement().equals(tempoAttribute)){
                                atributeValues.add(tempoAttribute);
                                break;
                            }
                        }

                    }

                }

            }
            if(verification){
                if(!atributeValues.isEmpty())atributos.add(new Attribute(atribConceito,atributeValues));
            }

            System.out.println("Digite o Campo a ser analisado :");
            verification=false;
            atributeValues.clear();
        }
        for (Attribute attribute : atributos) {
            System.out.println(attribute);
        }
        Conceito c1;
        c1 = new Conceito(atributos,arquivo.getInstances());
    }
}
