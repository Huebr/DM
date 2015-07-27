package DM_File_Handler;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import weka.core.Instances;
import weka.core.converters.ArffSaver;
import weka.core.converters.CSVLoader;
import weka.core.converters.CSVSaver;
import weka.core.converters.ConverterUtils.DataSource;
import weka.core.converters.Saver;

public class DMHandler {
    private Instances instances;

    public DMHandler(String caminho) throws Exception {
        setSource(caminho);
    }

    public Instances getInstances() {
        return instances;
    }

    public DMHandler() {

    }

    private void setSource(String caminho) throws Exception {
        DataSource source = new DataSource(caminho);
        this.instances = source.getDataSet();
    }

    // Manipular arquivos .arff e .csv
    // Salva em .csv
    public void exportCSV(String caminhoCSV) throws IOException {
        CSVSaver saver = new CSVSaver();
        save(saver, caminhoCSV);
    }

    // Salva em .arff
    public void exportARFF(CSVLoader loader, String caminho) throws IOException {
        ArffSaver saver = new ArffSaver();
        save(saver, caminho);
    }

    private void save(Saver saver, String caminho) throws IOException {
        saver.setInstances(this.instances);
        saver.setFile(new File(caminho));
        saver.writeBatch();
    }

    // Imprimi o dataSet
    public void imprimirDataset() throws Exception {
        System.out.println(this.instances);
    }

    // Manipular conceitos.

    // Manipular as relações
    // Exportar relações

    // Métodos privado usado para serializar objetos
    private void sereziableObjetos(Object o, String nome) {
        try {
            ObjectOutputStream arquivo = new ObjectOutputStream(
                    new FileOutputStream(nome));
            arquivo.writeObject(o);
        } catch (IOException e) {
            System.out.println("Erro ao exportar");
            e.printStackTrace();
        }
    }

    private Object serializableRetorno(String nome) {
        Object o = null;
        try {
            ObjectInputStream arquivo = new ObjectInputStream(
                    new FileInputStream(nome));
            o = arquivo.readObject();
            arquivo.close();
        } catch (Exception e) {
            System.out.println("Erro ao importar");
            e.printStackTrace();
        }
        return o;
    }
}
