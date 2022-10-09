package com.njuse.dm.classification;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.trees.J48;
import weka.core.Instances;
import weka.core.converters.ConverterUtils;

public class AdultIncomeClassification {

    static String dataSetPath = "dataset/classification/adult_income_uk.arff";
    static String modelPath = "model/adult_income_uk.model";

    public static void main(String[] args) throws Exception {
        Instances instances = ConverterUtils.DataSource.read(dataSetPath);
        instances.setClassIndex(instances.numAttributes() - 1);

        int dataSize = instances.size();
        int trainDataSize = (int) Math.round(dataSize * 0.8);
        int testDataSize = dataSize - trainDataSize;
        Instances trainData = new Instances(instances, 0, trainDataSize);
//        trainData.setClassIndex(trainData.numAttributes()-1);
        Instances testData = new Instances(instances, trainDataSize, testDataSize);
//        testData.setClassIndex(testData.numAttributes()-1);

        Classifier cls = new J48();
        cls.buildClassifier(trainData);
        System.out.println(cls);

        Evaluation evaluation = new Evaluation(testData);
        evaluation.evaluateModel(cls, testData);
        System.out.println(evaluation.errorRate());
    }

    public boolean checkOnesSegment(String s) {
        String[] split = s.split("[0]+");
        return split.length <= 1;
    }
}
