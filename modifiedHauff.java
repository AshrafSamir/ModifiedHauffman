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

        //variables
        ArrayList<Node> arr = new ArrayList<>();
        ArrayList<String> others = new ArrayList<>();
        String userInput;
        int nTimes=9;
        int choice = 1;

        //short code table
        caller.setShortCode(choice);
        //probability and character input
        System.out.println("Enter characters probability : ");
        caller.takeCharProbability( input, nTimes, arr, others);
        //build tree
        caller.buildTree(arr);

     /* assert false;
        caller.print(arr.get(0));
         */

        System.out.println(caller.table);
        System.out.println(caller.decompTable);

        //input sequence
        System.out.println("Enter sequence to decompress : ");
        userInput = input.next();
        //compress
        caller.compress(userInput, others);
        caller.decompress(input);

    }

    private void compress(String userInput,ArrayList<String> others){

        for (int i=0;i<userInput.length();i++){
            if(others.contains(String.valueOf(userInput.charAt(i)))){
                System.out.print(table.get("Others")+shortCodeComp.get(userInput.charAt(i))+" ");
            }
            else{
                System.out.print(table.get(String.valueOf(userInput.charAt(i)))+" ");
            }
        }
        System.out.println();
    }
    private void decompress(Scanner input){
        String code = "";
        System.out.println("Enter bits to decompress : ");
        String userInput = input.next();
        for (int i=0;i<=userInput.length();){
            if((decompTable.get(code)== null)&&(i<userInput.length())){
                code += String.valueOf(userInput.charAt(i));
                i++;
            }
            else if ((decompTable.get(code).equals("Others"))&&(i<userInput.length())){
                String tmp = "";
                for (int j=0;j<7;j++){
                    tmp += String.valueOf(userInput.charAt(i));
                    i++;
                }
                System.out.print(shortCodeDecomp.get(tmp));
                code = "";
            }
             else if((decompTable.get(code)!= null)&&(i<userInput.length())){
                 System.out.print(decompTable.get(code));
                 code = "";
             }
             if(i==userInput.length())break;
        }
    }
    private void buildTree(ArrayList<Node> arr){
        double tmpI;
        String tmpS;
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
            setCodeForTree(arr.get(0));
        }
    }
    private void takeCharProbability(Scanner input,int nTimes,ArrayList<Node> arr,ArrayList<String> others){
        int count = 1;
        double prob,tmpProb = 0.05;
        String st ;
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
    }
    private void setShortCode(int choice){
        for (int i = 0; i < 128; i++) {
            String code = Integer.toBinaryString(i);
            while (code.length() < 7){
                code = '0' + code;
            }

            shortCodeComp.put((char) i, code);
            shortCodeDecomp.put(code,(char) i);
        }
    }
    private void setCodeForTree(Node parent){
        if ((parent.getStr().length()==1)||(parent.getStr()=="Others")){
            table.put(parent.getStr(),parent.getCode());
            decompTable.put(parent.getCode(),parent.getStr());
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

}


