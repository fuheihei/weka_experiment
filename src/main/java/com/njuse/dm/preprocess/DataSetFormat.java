package com.njuse.dm.preprocess;

import java.io.File;

public class DataSetFormat {

    String relationName;

    static String RELATION_PREFIX = "@RELATION";

    static String ATTR_PREFIX = "@ATTRIBUTE";

    static String DATA_PREFIX = "@DATA";

    public void load(File file) {
        if (file.getName().endsWith(".xls") || file.getName().endsWith(".xlsx")) {

        }
    }
}
