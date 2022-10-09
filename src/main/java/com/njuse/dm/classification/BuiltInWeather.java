package com.njuse.dm.classification;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.trees.J48;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ArffLoader;
import weka.core.converters.ConverterUtils;

import java.io.File;
import java.util.Random;

public class BuiltInWeather {

    public static void main(String[] args) throws Exception {
        J48 dTree = (J48) weka.core.SerializationHelper.read("model/weather_j48.model");
        System.out.println(dTree);
        Instances instances = ConverterUtils.DataSource.read("dataset/weather.numeric.arff");
        instances.setClassIndex(instances.numAttributes() - 1);
        Evaluation evaluation = new Evaluation(instances);
        for (Instance instance : instances) {
            evaluation.evaluateModelOnceAndRecordPrediction(dTree, instance);
        }
        System.out.println(evaluation.errorRate());
    }

    private static void j48Weather() throws Exception {
        File file = new File("dataset/weather.numeric.arff");
        ArffLoader loader = new ArffLoader();
        loader.setFile(file);

        Instances ins = loader.getDataSet();
        ins.randomize(new Random(0));
        ins.setClassIndex(ins.numAttributes() - 1);
//
//        int trainSize = (int) Math.round(ins.numInstances() * 0.8);
//        int testSize = ins.numInstances() - trainSize;
//
//        Instances train = new Instances(ins, 0, trainSize);
//        Instances test = new Instances(ins, trainSize, testSize);

        J48 cls = new J48();
        cls.setConfidenceFactor(0.25f);
        cls.setMinNumObj(2);
        cls.buildClassifier(ins);
//        Evaluation testingEvaluation = new Evaluation(ins);
//        for (Instance t_ins : test) {
//            double v = testingEvaluation.evaluateModelOnceAndRecordPrediction(cls, t_ins);
//            System.out.println(t_ins);
//            System.out.println("correct rate is :" + (1 - v));
//        }
//        System.out.println("correct rate is :" + (1 - testingEvaluation.errorRate()));
//        System.out.println(testingEvaluation.toSummaryString());

        weka.core.SerializationHelper.write("model/weather_j48.model", cls);

        J48 cls2 = (J48) weka.core.SerializationHelper.read("model/weather_j48.model");
        System.out.println(cls2);
    }


    private static void naiveByesWeather() throws Exception {
        File file = new File("dataset/weather.numeric.arff");
        ArffLoader loader = new ArffLoader();
        loader.setFile(file);

        Instances ins = loader.getDataSet();
        System.out.println(ins.numAttributes());
        ins.setClassIndex(ins.numAttributes() - 1);

        Classifier cls = new NaiveBayes();

        cls.buildClassifier(ins);

        Instance testInst;

        Evaluation testingEvaluation = new Evaluation(ins);

        int length = ins.numInstances();

        for (int i = 0; i < length; i++) {
            testInst = ins.instance(i);
            testingEvaluation.evaluateModelOnceAndRecordPrediction(cls, testInst);
        }

        System.out.println("correct rate is :" + (1 - testingEvaluation.errorRate()));
    }
}
