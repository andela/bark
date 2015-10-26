package com.andela.bark.models;

import com.parse.ParseObject;

/**
 * Created by andela-cj on 26/10/2015.
 */

public abstract class BaseModel extends ParseObject {

    public BaseModel(){
        super();
    }
    public BaseModel(String className){
        super(className);
    }


    public  void setValueForKey(String key, Object value){
        put(key,value);
    }

    public  Object getValueForKey(String key){
        return (get(key) instanceof ParseObject)?getParseObject(key):get(key);
    }
    public String getObjectId(){
        return super.getObjectId();
    }

    public void setObjectId(String id){
        super.setObjectId(id);
    }

}
