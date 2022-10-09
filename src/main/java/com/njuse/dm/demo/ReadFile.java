package com.njuse.dm.demo;


import weka.core.Instances;
import weka.core.converters.ArffLoader;
import weka.core.converters.ConverterUtils.DataSource;

import java.io.File;

public class ReadFile {

    public static void main(String[] args) throws Exception {

        //方法一：使用DataSource类的read方法来加载arff文件
        Instances data1 = DataSource.read("data/weather.nominal.arff");

        //方法二：使用直接指定加载器的方法来加载arff文件
        ArffLoader arffLoader = new ArffLoader();//创建ArffLoader实例
        arffLoader.setSource(new File("data/weather.nominal.arff"));
        Instances data2 = arffLoader.getDataSet();

    }
}
