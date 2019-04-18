package common;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;
//第一次更新
//第二次更新
@Getter
@Setter
public class JsonData {
    private boolean result;
    private String msg;
    private Object data;
    JsonData (boolean result){
        this .result=result;
    }
    public static JsonData success(Object o,String s){
        JsonData jsonData = new JsonData(true);
        jsonData.msg=s;
        jsonData.data=o;
        return jsonData;
    }
    public static JsonData success(Object o){
        JsonData jsonData = new JsonData(true);
        jsonData.data=o;
        return jsonData;
    }
    public  static JsonData fail(String s){
        JsonData jsonData = new JsonData(false);
        jsonData.msg=s;
        return jsonData;

    }
    public static JsonData success(){
        JsonData jsonData = new JsonData(true);
        return jsonData;
    }
    public Map<String ,Object> toMap(){
        HashMap<String, Object> hashMap = new HashMap();
        hashMap.put("result", result);
        hashMap.put("data", data);
        hashMap.put("msg", msg);
        return hashMap;
    }
}
