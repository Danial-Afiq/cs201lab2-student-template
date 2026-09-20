import java.util.ArrayList;
import java.util.HashMap;

public class SinglyLinkedList<E extends Comparable<E>> {
    private Node<E> head = null;
    private Node<E> tail = null;
    private int size = 0;

    private static class Node<E> {
        private E element;
        private Node<E> next;
    
        public Node(E e, Node<E> n){
            element = e;
            next = n;
        }
    
        public E getElement(){
            return element;
        }
    
        public Node<E> getNext(){
            return next;
        }
    
        public void setNext(Node<E> n){
            next = n;
        }
    }

    public SinglyLinkedList(){

    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public E first(){
        if (isEmpty()){
            return null;
        } 
        return head.getElement();
    }

    public E last(){
        if (isEmpty()){
            return null;
        }
        return tail.getElement();
    }

    public void addFirst(E e){
        head = new Node<>(e, head);

        if (isEmpty()){
            tail = head;
        }
        size++;
    }

    public void addLast(E e){
        Node<E> newest = new Node<>(e, null);
        if (isEmpty()){
            head = newest;
        } else {
            tail.setNext(newest);
        }
        tail = newest;
        size++;
    }

    public E removeFirst(){
        if (isEmpty()){
            return null;
        }

        E answer = head.getElement();
        head = head.getNext();
        size--;

        if (isEmpty()){
            tail = null;
        }
        return answer;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        Node<E> current = head;
        while (current != null) {
            sb.append(current.getElement());
            sb.append(" ");
            current = current.getNext();
        }
        return sb.toString();
    }

    // write your codes here
    public void swap() {
        // check if it's empty or only 1 item, nothing to swap
        if (isEmpty() || size() == 1) { 
            return; 
        }
        
        ArrayList<Node<E>> original = new ArrayList<>(); 
        Node<E> currNode = head; 

        while (currNode != null) { 
            original.add(currNode); 
            currNode = currNode.getNext(); 
        }
        
        // clone it so we can sort it
        ArrayList<Node<E>> sorted = new ArrayList<>(original); 
        
        sorted.sort((a, b) -> a.getElement().compareTo(b.getElement()));
        // for(int i = 0; i < sorted.size(); i++) {
        //     System.out.println("Sorted element: " + sorted.get(i).getElement());
        // }
        
        HashMap<Node<E>, Node<E>> swapMap = new HashMap<>(); 
        int totalElements = sorted.size(); 
        
        // map the first half to the last half and vice versa
        for (int i = 0; i < totalElements; i++) { 
            Node<E> keyNode = sorted.get(i);
            Node<E> valueNode = sorted.get(totalElements - 1 - i);
            swapMap.put(keyNode, valueNode); 
        }
        
        // System.out.println("map size: " + swapMap.size());
        
        // fix  head pointer first
        head = swapMap.get(original.get(0)); 
        currNode = head; 
        
        // stitch the rest of the nodes back tgt using the map
        for (int j = 1; j < original.size(); j++) { 
            Node<E> nextNode = swapMap.get(original.get(j)); 
            currNode.setNext(nextNode); 
            currNode = nextNode; // move pointer forward
        }
        
        // clear the last next pointer else it loops forever
        currNode.setNext(null); 
        tail = currNode; 
        
        // Node<E> temp = head;
        // while(temp != null) {
        //     System.out.print(temp.getElement() + " -> ");
        //     temp = temp.getNext();
        // }
        // System.out.println("null");

    }
   
}

