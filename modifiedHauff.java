import java.util.*;

public class modifiedHauff {

    Map<String,String> table = new HashMap<String,String>();

    public static void main(String[] args) {

        modifiedHauff caller = new modifiedHauff();


        Scanner input = new Scanner(System.in);
        ArrayList<Node> arr = new ArrayList<>();
        String st;
        String tmpS;
        String userInput;
        double prob,tmpI;
        int nTimes=13;

        input.useLocale( Locale.US );

        for (int i=0;i<nTimes;i++){

            st = input.next();
            prob = input.nextDouble();

            Node node = new Node(st, prob);
            arr.add(node);
        }
        arr.sort(Collections.reverseOrder());
        while (arr.size()>1){
            tmpI = arr.get(arr.size()-2).getProb()+arr.get(arr.size()-1).getProb();
            tmpS = arr.get(arr.size()-2).getStr()+arr.get(arr.size()-1).getStr();
            Node newNode = new Node(tmpS,tmpI);
            newNode.setLeft( arr.get(arr.size()-1));
            newNode.setRight(arr.get(arr.size()-2));
            arr.remove(arr.size()-1);
            arr.remove(arr.size()-1);
            arr.add(newNode);
            arr.sort(Collections.reverseOrder());
        }
        if(arr.size()==1){
            assert false;
            caller.setCodeForTree(arr.get(0));
        }

        //assert false;
        //caller.print(arr.get(0));
        System.out.println(caller.table);

        /*userInput = input.next();
        for (int i=0;i<userInput.length();i++){
            System.out.print(caller.table.get(String.valueOf(userInput.charAt(i)))+" ");
        }
         */
    }

    private void print(Node node){

        if (node == null)
            return;

        // first recur on left subtree
        print(node.getLeft());

        // then recur on right subtree
        print(node.getRight());

        // now deal with the node

        System.out.println(node.toString());

       /* if (node.getParent() != null){
            System.out.println(node.getParent().toString());
        } */
    }
    private void setCodeForTree(Node parent){
        if (parent.getStr().length()==1){
            table.put(parent.getStr(),parent.getCode());
        }

        if( parent.getRight() != null){
            parent.getRight().setCode(parent.getCode()+"1");
            setCodeForTree(parent.getRight());
        }

        if( parent.getLeft() != null){
            parent.getLeft().setCode(parent.getCode()+"0");
            setCodeForTree(parent.getLeft());
        }
    }
}


