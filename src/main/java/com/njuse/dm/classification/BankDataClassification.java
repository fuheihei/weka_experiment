package com.njuse.dm.classification;

import weka.classifiers.Evaluation;
import weka.classifiers.trees.J48;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ArffLoader;
import weka.core.converters.ConverterUtils;

import java.io.File;
import java.util.Random;

public class BankDataClassification {

    static String dataSetPath = "dataset/bank_data.arff";
    static String modelPath = "model/bank_data_j48.model";

    public static void main(String[] args) throws Exception {
        train();
        J48 dTree = (J48) weka.core.SerializationHelper.read(modelPath);
        System.out.println(dTree);
        Instances instances = ConverterUtils.DataSource.read(dataSetPath);
        instances.setClassIndex(instances.numAttributes() - 1);
        Evaluation evaluation = new Evaluation(instances);
        for (Instance instance : instances) {
            evaluation.evaluateModelOnceAndRecordPrediction(dTree, instance);
        }
        System.out.println(evaluation.errorRate());
    }

    private static void train() throws Exception {
        File file = new File(dataSetPath);
        ArffLoader loader = new ArffLoader();
        loader.setFile(file);

        Instances ins = loader.getDataSet();
        ins.randomize(new Random(0));
        ins.setClassIndex(ins.numAttributes() - 1);

        J48 cls = new J48();
        cls.setConfidenceFactor(0.25f);
        cls.setMinNumObj(2);
        cls.buildClassifier(ins);

        weka.core.SerializationHelper.write(modelPath, cls);
    }
}
