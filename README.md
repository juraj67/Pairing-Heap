# Pairing-Heap
The pairing heap is an implementation of the priority queue, the heap is represented in binary form.

# How to use it
Copy the package "pairing_heap" into your project, import it and create new Pairing_Heap object:
```java
import pairing_heap.*;

Pairing_Heap<Integer> pairing_heap = new Pairing_Heap<>();
```
# Example
```java
import pairing_heap.*;

public class TestClass {
  public static void main(String[] args) {
    Pairing_Heap<Integer> pairing_heap = new Pairing_Heap<>();
    
    //push
    pairing_heap.push(2);
    pairing_heap.push(1);
    pairing_heap.push(3);
    
    //peek 
    System.out.println(pairing_heap.peek()); //prints 1
    
    //pop 
    System.out.println(pairing_heap.pop()); //removes and prints 1
    System.out.println(pairing_heap.pop()); //removes and prints 2
  }
}
```

