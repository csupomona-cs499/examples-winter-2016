package cs499.cpp.edu.l04_adapterviews.stack;

import java.util.ArrayList;

/**
 * Created by yusun on 1/27/16.
 */
public class MyStackImpl implements MyStack {

    private ArrayList<Integer> arrayList = new ArrayList<Integer>();

    @Override
    public void push(int value) {
        arrayList.add(0, value);
    }

    @Override
    public int popup() {
        int value = arrayList.get(0);
        arrayList.remove(0);
        return value;
    }
}
