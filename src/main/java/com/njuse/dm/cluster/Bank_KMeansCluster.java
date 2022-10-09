package com.njuse.dm.cluster;

import weka.clusterers.SimpleKMeans;
import weka.core.Instances;
import weka.core.converters.ConverterUtils;

public class Bank_KMeansCluster {

    static String dataSetPath = "dataset/cluster/bank_data.arff";
    static String modelPath = "model/cluster/bank_data.model";

    public static void main(String[] args) throws Exception {

        SimpleKMeans kMeans = new SimpleKMeans();
        Instances instances = ConverterUtils.DataSource.read(dataSetPath);
        kMeans.setNumClusters(6);

        kMeans.buildClusterer(instances);
        System.out.println(kMeans);

    }
}
