package Concept;

import weka.core.Attribute;
import weka.core.Instance;
import weka.core.Instances;

import java.util.ArrayList;
import java.util.Enumeration;


/**
 * Created by pedro on 26/07/2015.
 */
public class Conceito {
    private ArrayList<Attribute> atributos;
    private ArrayList<Integer> idxConceito;

    public  Conceito(ArrayList<Attribute> atributos,Instances dataSet){
        if(atributos.isEmpty()||dataSet.isEmpty()){
            System.out.println("Conceito nao pode ser representado.");
            return;
        }
        this.atributos=atributos;
        idxConceito= new ArrayList<>();
        loadConceito(dataSet);
    }
    public ArrayList<Integer> getConceitoValues(){
        return idxConceito;
    }
    public ArrayList<Attribute> getAtributos(){
        return atributos;
    }
    private void loadConceito(Instances dataSet){
        int temp=0;
        for(Instance instance:dataSet){
            if(isValid(instance))idxConceito.add(temp+1);
            temp++;
        }
    }
    private boolean isValid(Instance record){
        Attribute temp;
        boolean resposta;
        int idx=0;
        for(Enumeration<Attribute>sa1=record.enumerateAttributes();sa1.hasMoreElements();){
            temp=sa1.nextElement();
            resposta=false;
            for(Attribute at1:atributos) {
                if (temp.name().equals(at1.name())) {
                    for(Enumeration<Object> s1=at1.enumerateValues();s1.hasMoreElements();){
                        if(temp.isNumeric()){
                            if(record.value(idx)==(double)s1.nextElement()){
                                resposta=true;
                                break;
                            }

                        }
                        else{
                            if(record.stringValue(idx).equals(s1.nextElement())){
                                resposta=true;
                                break;
                            }
                        }
                    }
                    if(!resposta)return false;
                    break;
                }
            }
            idx++;
        }

        return true;
    }

}
