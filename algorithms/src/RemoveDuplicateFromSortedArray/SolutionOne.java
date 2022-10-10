package RemoveDuplicateFromSortedArray;

import java.util.Iterator;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;

/**
 * @author Chopin
 * @date 2022/10/9
 */
public class SolutionOne {
    static final String JSON_SCHEMA
        = "{\"a\":1,\"b\":{\"b1\":2},\"c\":[1,2,3,4],\"d\":{\"d1\":{\"d1_1\":100,\"d1_2\":101},\"d2\":[\"d2_1\","
        + "\"d2_2\"]},\"e\":[{\"e1\":1},{\"e2\":2}]}";

    public static void main(String[] args) {
        flatJson(JSON.parse(JSON_SCHEMA));
    }

    public static void flatJson(Object object) {
        if (object instanceof JSONObject) {
            JSONObject jsonObject = (JSONObject) object;
            for (String key : jsonObject.keySet()) {
                Object value = jsonObject.get(key);
                if (value instanceof JSONArray) {
                    JSONArray jsonArray = (JSONArray)value;
                    flatJson(jsonArray);
                } else if (value instanceof JSONObject) {
                    flatJson(value);
                } else {
                    System.out.println("[" + key + "]" + value.toString() + "");
                }
            }
        } else if (object instanceof JSONArray) {
            JSONArray jsonArray = (JSONArray) object;
            for (Object o : jsonArray) {
                flatJson(o);
            }
        } else {
            System.out.println(object.toString() + "");
        }
    }
}