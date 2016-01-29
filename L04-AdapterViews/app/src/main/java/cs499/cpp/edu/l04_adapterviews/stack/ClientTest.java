package cs499.cpp.edu.l04_adapterviews.stack;

import java.util.ArrayList;

/**
 * Created by yusun on 1/27/16.
 */
public class ClientTest {

    public static void main(String[] args) {


        MyStack stack = new MyStackImpl();
        stack.push(4);
        stack.push(4);
        int v = stack.popup();
    }
}
