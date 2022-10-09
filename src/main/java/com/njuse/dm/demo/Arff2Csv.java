package com.njuse.dm.demo;

import weka.core.Instances;
import weka.core.converters.ArffSaver;
import weka.core.converters.CSVSaver;
import weka.core.converters.ConverterUtils;

import java.io.File;

public class Arff2Csv {

    public static void main(String[] args) throws Exception {

    }

    private static void csv2Arff() throws Exception {
        Instances allData = ConverterUtils.DataSource.read("dataset/wine.csv");
        ArffSaver saver = new ArffSaver();
        saver.setInstances(allData);
        saver.setFile(new File("dataset/wine.arff"));
        saver.writeBatch();
        System.out.println("已经转化为arrf文件");
    }

    private static void arff2Csv() throws Exception {
        //方法一
        Instances data = new Instances(ConverterUtils.DataSource.read("data/weather.nominal.arff"));
        ConverterUtils.DataSink.write("data/weather.csv", data);

        //方法二：明确指定转换器，保存为csv文件
        CSVSaver saver = new CSVSaver();
        saver.setInstances(data);
        saver.setFile(new File("data/weather2.csv"));
        saver.writeBatch();
    }
}
