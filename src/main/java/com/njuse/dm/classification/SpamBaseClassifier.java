package com.njuse.dm.classification;

import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.core.Instances;
import weka.core.SerializationHelper;
import weka.core.converters.ConverterUtils;

public class SpamBaseClassifier {

    static String dataSetPath = "dataset/classification/spambase.train.arff";
    static String testSetPath = "dataset/classification/spambase.test.arff";
    static String modelPath = "model/spambase.model";

    public static void main(String[] args) throws Exception {
        Instances trainSets = ConverterUtils.DataSource.read(dataSetPath);
        trainSets.setClassIndex(trainSets.numAttributes() - 1);

        NaiveBayes bayes = new NaiveBayes();
        bayes.buildClassifier(trainSets);

        SerializationHelper.write(modelPath, bayes);

        Instances testSets = ConverterUtils.DataSource.read(testSetPath);
        testSets.setClassIndex(testSets.numAttributes() - 1);
        Evaluation evaluation = new Evaluation(testSets);
        evaluation.evaluateModel(bayes, testSets);
        System.out.println("the error rate on test data sets:");
        System.out.println(evaluation.errorRate());
    }
}
