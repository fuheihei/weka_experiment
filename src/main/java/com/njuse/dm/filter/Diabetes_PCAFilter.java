package com.njuse.dm.filter;

import weka.attributeSelection.PrincipalComponents;
import weka.attributeSelection.Ranker;
import weka.core.Instances;
import weka.core.converters.ArffSaver;
import weka.core.converters.ConverterUtils;
import weka.filters.Filter;
import weka.filters.supervised.attribute.AttributeSelection;

import java.io.File;
import java.io.IOException;

public class Diabetes_PCAFilter {


    static String dataSetPath = "dataset/dimension_reduce/diabetes.arff";
    static String saveDataSetPath = "dataset/other/filtered/diabetes.arff";

    public static void main(String[] args) throws Exception {
        Instances instances = ConverterUtils.DataSource.read(dataSetPath);
        instances.setClassIndex(instances.numAttributes() - 1);

        AttributeSelection filter = new AttributeSelection();
        Ranker ranker = new Ranker();
        ranker.setNumToSelect(5);
        PrincipalComponents pc = new PrincipalComponents();
        filter.setSearch(ranker);
        filter.setEvaluator(pc);
        filter.setInputFormat(instances);

        //generate new data
        Instances newData = Filter.useFilter(instances, filter);
        saveDataSet(newData);

        //show PCA
        System.out.println(pc);
    }

    private static void saveDataSet(Instances dataset) throws IOException {
        saveDataset(dataset, true);
    }

    public static void saveDataset(Instances dataset, boolean batchSave) throws IOException {
        ArffSaver saver = new ArffSaver();
        if (batchSave) {
            saver.setInstances(dataset);
            saver.setFile(new File(saveDataSetPath));
            saver.writeBatch();
        } else {
            saver.setRetrieval(ArffSaver.INCREMENTAL);
            saver.setInstances(dataset);
            saver.setFile(new File(saveDataSetPath));
            for (int i = 0; i < dataset.numInstances(); i++) {
                saver.writeIncremental(dataset.instance(i));
            }
            saver.writeIncremental(null);
        }
    }
}
