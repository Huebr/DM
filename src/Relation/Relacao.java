package Relation;

import weka.core.Attribute;
import weka.core.Instance;
import weka.core.Instances;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Enumeration;

/**
 * Created by pedro on 26/07/2015.
 */
public class Relacao {
    private Dictionary<Integer,ArrayList<Integer>> idxclasses;

    public Dictionary<Integer, ArrayList<Integer>> getIdxclasses() {
        return idxclasses;
    }
    public Relacao(){}
    public void loadRelacao(ArrayList<ArrayList<Attribute>> classes,Instances dataSet){
        int idx,idy;
        idx=0;
        ArrayList<Integer> myarray=new ArrayList<>();
        for(ArrayList<Attribute> relacao:classes){
            idy=0;
            for(Instance record:dataSet){
                idy++;
                if(isValid(record,relacao))myarray.add(idy);
            }
            idxclasses.put(idx, myarray);
            idx++;
        }


    }
    public boolean isEmpty(){
        return idxclasses.isEmpty();
    }
    private boolean isValid(Instance record,ArrayList<Attribute> atributos) {
        Attribute temp;
        boolean resposta;
        int idx = 0;
        for (Enumeration<Attribute> sa1 = record.enumerateAttributes(); sa1.hasMoreElements(); ) {
            temp = sa1.nextElement();
            resposta = false;
            for (Attribute at1 : atributos) {
                if (temp.name().equals(at1.name())) {
                    for (Enumeration<Object> s1 = at1.enumerateValues(); s1.hasMoreElements(); ) {
                        if (temp.isNumeric()) {
                            if (record.value(idx) == (double) s1.nextElement()) {
                                resposta = true;
                                break;
                            }

                        } else {
                            if (record.stringValue(idx).equals(s1.nextElement())) {
                                resposta = true;
                                break;
                            }
                        }
                    }
                    if (!resposta) return false;
                    break;
                }
            }
            idx++;
        }
        return true;
    }
}
