package com.njuse.dm.cluster;

import weka.clusterers.SimpleKMeans;
import weka.core.Instances;
import weka.core.converters.ConverterUtils;

public class Bmw_browsers_KMeansCluster {

    static String dataSetPath = "dataset/cluster/bmw-browsers.arff";
    static String modelPath = "model/cluster/bmw-browsers.model";

    public static void main(String[] args) throws Exception {

        SimpleKMeans kMeans = new SimpleKMeans();
        Instances instances = ConverterUtils.DataSource.read(dataSetPath);
        kMeans.setNumClusters(5);

        kMeans.buildClusterer(instances);


        System.out.println(kMeans);

    }
}
