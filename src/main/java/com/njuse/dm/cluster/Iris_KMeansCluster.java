package com.njuse.dm.cluster;

import weka.clusterers.SimpleKMeans;
import weka.core.Instances;
import weka.core.converters.ConverterUtils;

public class Iris_KMeansCluster {

    static String dataSetPath = "dataset/cluster/iris.arff";
    static String modelPath = "model/cluster/iris.model";

    public static void main(String[] args) throws Exception {

        SimpleKMeans kMeans = new SimpleKMeans();
        Instances instances = ConverterUtils.DataSource.read(dataSetPath);
        kMeans.setNumClusters(3);

        kMeans.buildClusterer(instances);
        System.out.println(kMeans);

    }
}
