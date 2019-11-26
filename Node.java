
public class Node implements Comparable<Node>  {

    private String str;
    private double prob;
    private String code ;
    private Node left;
    private Node right;

    Node(String str, double prob){
        this.str = str;
        this.prob = prob;
        this.left = null;
        this.right = null;
        this.code = "";
    }



    public void setStr(String str) {
        this.str = str;
    }

    public void setProb(double prob) {
        this.prob = prob;
    }

    void setLeft(Node left) {
        this.left = left;
    }

    void setRight(Node right) {
        this.right = right;
    }

    void setCode(String code) {
        this.code = code;
    }

    String getStr() {
        return str;
    }

    double getProb() {
        return prob;
    }

    Node getLeft() {
        return left;
    }

    Node getRight() {
        return right;
    }

    String getCode() {
        return code;
    }

    @Override
    public int compareTo(Node o) {
        double val = o.prob;
        if (prob>val) return 1;

        return -1;
    }

    @Override
    public String toString() {
        return "Node{" +
                "str='" + str + '\'' +
                ", prob=" + prob +
                ", code='" + code + '\'' +"\n"+
                ", left=" + left + "\n"+
                ", right=" + right +
                '}';
    }
}
