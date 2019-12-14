import pairing_heap.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import java.util.Random;
import java.util.PriorityQueue;
import java.util.Stack;


/**
 *
 * @author juraj67
 */
public class Pairing_Heap_Tests {
    
    private Pairing_Heap<Integer> pairing_heap;
    private PriorityQueue<Integer> priority_queue;
    private ArrayList<Integer> array_list;
    
    private int countPop;
    private int countPush;
    private int countWrongPop;
    private int actualCount;
    private int changePriorCount;
    private Random rand;
    
    public Pairing_Heap_Tests() {
        this.pairing_heap = new Pairing_Heap<>();
        this.priority_queue = new PriorityQueue<>();
        this.array_list = new ArrayList<>();
        
        this.actualCount = 0;
        this.countPop = 0;
        this.countPush = 0;
        this.countWrongPop = 0;
        this.changePriorCount = 0;
        
        rand = new Random();
    }
    
    public void testing(int myCount) {
        for (int i = 0; i < myCount; i++) {
            double d = rand.nextDouble();

            if (d > 0.6) {
               push(); 
            } else if( d > 0.2) {
               pop();
            } else {
               changePrior();
            }
            
            if((i + 1) == myCount) {
                System.out.println("\nNumber of operations: " + (i+1));
                compare();
            }
        }
    }
    
    public void push() {

        Integer priority = 1 + rand.nextInt(1000000);
        
        pairing_heap.push(new Heap_Item<>(priority));
        priority_queue.add(priority);
        array_list.add(priority);
        
        countPush++;
        actualCount++;
        
    }
    
    public void pop() {
        if(actualCount > 0) {
            
            Integer data = pairing_heap.pop();
            Integer data2 = priority_queue.remove();
            array_list.remove((Object)data2);
           
            
            actualCount--;
            
            if(Objects.equals(data, data2)) {
                countPop++;
            } else {
                countWrongPop++;
            }
        }
    }
    
    public void changePrior() {
        if(actualCount > 0) {
            
            Integer x = actualCount - 1;
            Integer numb = 0;
            if(x > 0) {
                numb = rand.nextInt(x);
            }
            Integer to_delete = array_list.get(numb);
            
            Integer priority = 1 + rand.nextInt(1000000);
            Stack<Heap_Item<Integer>> stack = new Stack<>();
            Heap_Item<Integer> actual_node = this.pairing_heap.peek();
            
            while(actual_node != null || !stack.empty()) {
                if(actual_node != null && Objects.equals(actual_node.getData(), to_delete)) {
                    actual_node.setData(priority);
                    pairing_heap.checkPriority(actual_node);

                    priority_queue.remove(to_delete);
                    priority_queue.add(priority);

                    array_list.remove((Object)to_delete);
                    array_list.add(priority);
                    
                    changePriorCount++;
                    
                    break;
                }
                if (actual_node != null) {   
                    stack.push(actual_node);
                    actual_node = actual_node.getLeftSon();

                } else {                        
                    actual_node = stack.pop();
                    actual_node = actual_node.getRightSon();
                }
            }
        }
    }

    public int getChangePriorCount() {
        return changePriorCount;
    }
    
    public int getCountPop() {
        return countPop;
    }

    public int getCountPush() {
        return countPush;
    }

    public int getCountWrongPop() {
        return countWrongPop;
    }
    
    public int getActualCount() {
        return actualCount;
    }
    
    public void compare() {
        Heap_Item<Integer> actual_node = this.pairing_heap.peek();           
        ArrayList<Integer> array_heap = new ArrayList<>();
            
        Stack<Heap_Item<Integer>> stack = new Stack<>();
        while(actual_node != null || !stack.empty()) {
            if (actual_node != null) {      
                stack.push(actual_node);
                actual_node = actual_node.getLeftSon();
            } else {                        
                actual_node = stack.pop();
                array_heap.add(actual_node.getData());
                actual_node = actual_node.getRightSon();
            }
        }
       
        System.out.println("\nCount of items in pairing heap: " + array_heap.size());
        System.out.println("\nCount of items in  priority queue: " + array_list.size());

        Collections.sort(array_list); 
        Collections.sort(array_heap); 
        
	System.out.println("\n\nPairing heap items:");
        for (Integer integer : array_heap) {
            System.out.print(integer + ", ");
        }
        System.out.println("\n\nArrayList items:");
        for (Integer integer : array_list) {
            System.out.print(integer + ", ");
        }

        boolean equals = array_list.equals(array_heap);
        System.out.println("\nItems in the priority queue and in the pairing heap are equal: " + equals);
    }
    
    public static void main(String[] args) {
        Pairing_Heap_Tests test = new Pairing_Heap_Tests();
        
        test.testing(1000000);
        
        System.out.println("\nNumber of insertions: " + test.getCountPush());
        System.out.println("Number of priority changes : " + test.getChangePriorCount());
        System.out.println("Number of equal items after 'pop' operation: " + test.getCountPop());
        System.out.println("Number of different items after 'pop' operation: " + test.getCountWrongPop());   
    }
}
