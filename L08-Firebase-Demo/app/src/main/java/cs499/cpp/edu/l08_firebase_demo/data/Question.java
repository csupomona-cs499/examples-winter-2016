package cs499.cpp.edu.l08_firebase_demo.data;

/**
 * Created by yusun on 2/10/16.
 */
public class Question {

    private int id;
    private int op1;
    private int op2;
    private int opType;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOp1() {
        return op1;
    }

    public void setOp1(int op1) {
        this.op1 = op1;
    }

    public int getOp2() {
        return op2;
    }

    public void setOp2(int op2) {
        this.op2 = op2;
    }

    public int getOpType() {
        return opType;
    }

    public void setOpType(int opType) {
        this.opType = opType;
    }

    public String toString() {
        String res = op1 + " ";
        switch (opType) {
            case 0 :
                res += " + ";
                break;
            case 1 :
                res += " - ";
                break;
            case 2 :
                res += " * ";
                break;
        }
        res += op2 + " = ?";
        return res;
    }

    public boolean isCorrect(int answer) {
        switch (opType) {
            case 0 :
                return answer == op1 + op2;
            case 1 :
                return answer == op1 - op2;
            case 2 :
                return answer == op1 * op2;
        }
        return false;
    }
}
