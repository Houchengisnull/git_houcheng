package org.hc.learning.algorithm.sort;

import org.hc.learning.thread.forkjoin.learning.sum.MakeArray;

public class SeletionSort extends Sort<Integer>{

    public SeletionSort(Integer[] array) {
        super(array);
    }

    @Override
    void sort() {
        for (int i = 0; i < array.length; i++) {
            int max = array[i];
            for (int j = i+1; j < array.length; j++) {
                if (max < array[j]){
                    swap(i, j);
                }
            }
        }
    }

    @Override
    void swap(int posFront, int posBack) {
        int temp = array[posFront];
        array[posFront] = array[posBack];
        array[posBack] = temp;
    }

    /**
     * 10万数据耗时16703ms
     * @param args
     */
    public static void main(String[] args) {
        long l = System.currentTimeMillis();
        Integer[] array = MakeArray.makeArray();
        SeletionSort sort = new SeletionSort(array);
        sort.sort();
        sort.output(array);
        System.out.println("耗时" + (System.currentTimeMillis()-l) + "ms");
    }
}
