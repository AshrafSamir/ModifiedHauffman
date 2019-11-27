import java.util.*;

public class modifiedHauff {

    private Map<String,String> table = new HashMap<String,String>();
    private Map<Character, String> shortCodeComp= new HashMap<Character, String>();
    private Map<String, Character> shortCodeDecomp= new HashMap<String, Character>();
    Map<String,String> decompTable= new HashMap<String,String>();

    public static void main(String[] args) {


        modifiedHauff caller = new modifiedHauff();
        Scanner input = new Scanner(System.in);
        input.useLocale( Locale.US );

        ArrayList<Node> arr = new ArrayList<>();
        ArrayList<String> others = new ArrayList<>();
        String st;
        String tmpS;
        String userInput;
        double prob,tmpI,tmpProb = 0.05;
        int nTimes=9;
        int choice = 1;
        int count = 1;

        for (int i = 0; i < 128; i++) {
            String code = Integer.toBinaryString(i);
            while (code.length() < 7)
                code = '0' + code;

            if(choice==1)
                caller.shortCodeComp.put((char) i, code);
            else caller.shortCodeDecomp.put(code,(char) i);
        }



        for (int i=0;i<nTimes;i++){

            st = input.next();
            prob = input.nextDouble();

            if(prob<=0.05){
                tmpProb = 0.05;
                others.add(st);
                tmpProb *= count  ;
                count++;
            }
            else {
                Node node = new Node(st, prob);
                arr.add(node);
            }
            if(i==nTimes-1){
                Node node = new Node("Others", tmpProb);
                arr.add(node);
            }

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
        userInput = input.next();
        for (int i=0;i<userInput.length();i++){
            if(others.contains(String.valueOf(userInput.charAt(i)))){
                System.out.print(caller.table.get("Others")+caller.shortCodeComp.get(userInput.charAt(i))+" ");
            }
            else{
                System.out.print(caller.table.get(String.valueOf(userInput.charAt(i)))+" ");
            }
        }

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
        if ((parent.getStr().length()==1)||(parent.getStr()=="Others")){
            table.put(parent.getStr(),parent.getCode());
        }

        if( parent.getRight() != null){
            parent.getRight().setCode(parent.getCode()+"0");
            setCodeForTree(parent.getRight());
        }

        if( parent.getLeft() != null){
            parent.getLeft().setCode(parent.getCode()+"1");
            setCodeForTree(parent.getLeft());
        }
    }
}


