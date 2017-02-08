package org.smart4j.framework.bean;

/**
 * JSON数据对象
 * Created by osx on 17/2/8.
 */
public class Data {
    /**
     * 模型数据
     */
    private Object model;

    public Data(Object model) {
        this.model = model;
    }

    public Object getModel() {
        return model;
    }
}
