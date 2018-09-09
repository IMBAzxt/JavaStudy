package com.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Sort {
    public static void main(String[] args) {
        Integer[] arr = {88,6,7,10,19,1,2,8,9,3,63,4,5};
//        List<Integer> result = Sort.quickSort(Arrays.asList(arr),0);
        List<Integer> result = Sort.sort(Arrays.asList(arr),0);
        for (int i :result) {
            System.out.println(i);
        }
    }

    /**
     * 每次都找出最小数
     *
     * @param data
     * @param count
     * @return
     */
    public static List<Integer> sort(List<Integer> data, int count) {
        List<Integer> result= new ArrayList<>();
        //在使用Arrays.asList()后调用add，remove这些method时出现java.lang.UnsupportedOperationException异常。
        // 这是由于Arrays.asList() 返回java.util.Arrays$ArrayList，
        // 而不是ArrayList。Arrays$ArrayList和ArrayList都是继承AbstractList，
        // remove，add等method在AbstractList中是默认throw UnsupportedOperationException而且不作任何操作。
        // ArrayList override这些method来对list进行操作，但是Arrays$ArrayList没有override remove()，add()等，
        // 所以throw UnsupportedOperationException。
        // 解决方法是使用Iterator，或者转换为ArrayList。
        List<Integer> list = new ArrayList(data);
        if(data.size()>0) {
            int min = Integer.MAX_VALUE;
            int removeIndex = 0;
            for (int i=0 ; i < list.size(); i++) {
                if(min > data.get(i)) {
                    min = data.get(i);
                    removeIndex =i;
                }
                count++;
                System.out.println("查找次数：" + count);
            }
            list.remove(removeIndex);
            result.add(min);
            result.addAll(Sort.sort(list,count));
        }
        return result;
    }

    /**
     * 快速排序，选取一个值，将队列按照小于和大于该值的规则分成两半，然后递归，最后得到的结果就是排序好的结果。
     * @param data
     * @param count
     * @return
     */
    public static List<Integer> quickSort(List<Integer> data, int count) {
        List<Integer> result= new ArrayList<>();
        List<Integer> leftList = new ArrayList<>();
        List<Integer> rightList = new ArrayList<>();
        if(data.size()>0) {
            int first = data.get(0);
            for (int i=1 ; i < data.size(); i++) {
                count++;
                int temp = data.get(i);
                if(temp < first) {
                    leftList.add(temp);
                } else {
                    rightList.add(temp);
                }
                System.out.println("查找次数：" + count);
            }
            result.addAll(Sort.quickSort(leftList,count));
            result.add(first);
            result.addAll(Sort.quickSort(rightList,count));
        }
        return result;
    }
}
