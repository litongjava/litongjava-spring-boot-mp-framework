package com.litong.spring.boot.v158.mp.utils.array;

import java.util.ArrayList;
import java.util.List;

/**
 * @author bill robot
 * @date 2020年6月9日_上午10:40:42 
 * @version 1.0 
 * @desc
 */

public class ListUtils {
  public static List<String> toList(String[] ids) {
    List<String> idList = new ArrayList<>(ids.length);
    for (String i : ids) {
      idList.add(i);
    }
    return idList;
  }

  public static List<Integer> toList(int[] ids) {
    List<Integer> idList = new ArrayList<>(ids.length);
    for (int i : ids) {
      idList.add(i);
    }
    return idList;
  }

  public static String[] toArrayString(List<String> list) {
    int size = list.size();
    String[] array = new String[size];
    for (int i = 0; i < size; i++) {
      array[i] = list.get(i);
    }
    return array;
  }
}
