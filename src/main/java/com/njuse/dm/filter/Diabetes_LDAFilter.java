package com.njuse.dm.filter;

import com.njuse.dm.tool.FLDA;
import weka.classifiers.Classifier;
import weka.core.Instances;
import weka.core.converters.ArffSaver;
import weka.core.converters.ConverterUtils;

import java.io.File;
import java.io.IOException;

public class Diabetes_LDAFilter {


    static String dataSetPath = "dataset/dimension_reduce/diabetes.arff";
    static String saveDataSetPath = "dataset/other/filtered/diabetes_lda.arff";

    // About FLDA: Builds Fisher's Linear Discriminant function.
    // The threshold is selected so that the separator is half-way between centroids.
    // The class must be binary and all other attributes must be numeric.
    // Missing values are not permitted.
    // Constant attributes are removed using RemoveUseless.
    // No standardization or normalization of attributes is performed.
    // https://weka.sourceforge.io/doc.packages/discriminantAnalysis/weka/classifiers/functions/FLDA.html
    public static void main(String[] args) throws Exception {
        Instances instances = ConverterUtils.DataSource.read(dataSetPath);
        instances.setClassIndex(instances.numAttributes() - 1);

        Classifier cls = new FLDA();
        cls.buildClassifier(instances);

        // print the weight vector
        System.out.println(cls);

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

    public int maxAscendingSum(int[] nums) {
        int ans = nums[0];
        int sum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                sum += nums[i];
            } else {
                sum = nums[i];
            }
            ans = Math.max(ans, sum);
        }
        return ans;
    }
}
