package dataStructures.skiplist;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class SkipList<T> {
  private SkipListNode<T> head;
  private int maxLevel;
  private int listSize;
  private Random generator;

  public static void main(String[] args) {
    SkipList<Integer> myList = new SkipList<>(4);
    myList.add(5,5);
    myList.add(7,7);
    myList.add(5,5);
    myList.add(4,4);
    myList.add(1,1);

    System.out.println(myList);

    myList.add(11,11);
    myList.add(2,2);
    myList.add(6,6);
    myList.add(3,3);
    myList.add(22,22);

    System.out.println(myList);

    System.out.println(myList.contains(6));
    System.out.println(myList.contains(22));
    System.out.println(myList.contains(1));
    System.out.println(myList.contains(0));

    myList.delete(11);
    myList.delete(1);
    myList.delete(22);
    myList.deleteOne(5);

    System.out.println(myList);

    System.out.println(myList.contains(6));
    System.out.println(myList.contains(22));
    System.out.println(myList.contains(1));
    System.out.println(myList.contains(0));
  }

  public SkipList(int maxLevel) {
    this.maxLevel = maxLevel;
    head = new SkipListNode<>(Integer.MIN_VALUE, null, null, null, 0);

    listSize = 0;
    generator = new Random();
  }

  public void add(T data, int key) {

    if(getNode(key).isPresent()) {
      increaseOrderOfElement(key);
      return;
    }

    int newNodeLvl = getLevel();
    if(head.getLevel() < newNodeLvl) {
      head = new SkipListNode<>(Integer.MIN_VALUE, null, null, head, newNodeLvl);
    }

    SkipListNode<T> curr = head;
    SkipListNode<T> last = null;

    while(curr != null) {

      //Found the possible position for the new node in this lane
      if(curr.getNext() == null || curr.getNext().getKey() > key) {

        if(curr.getLevel() <= newNodeLvl) {
          //Add the new node to this lane
          SkipListNode<T> newNode = new SkipListNode<>(key, data, curr.getNext(), null, curr.getLevel());
          curr.setNext(newNode);

          if (last != null) {
            last.setDown(newNode);
          }

          last = newNode;
          listSize++;
        }
          curr = curr.getDown();
      } else if(curr.getNext().getKey() == key) {
        /*//A node with the same key is already in the list

        //If it contains the same data increase its order
        if(curr.getNext().getData().equals(data)) {
          curr.getNext().increaseOrder();
        }else{
          //else add the new data and reset order
          curr.getNext().setData(data);
          curr.resetOrder();
        }*/

        curr = curr.getDown();
      } else{
        curr = curr.getNext();
      }
    }
  }

  private void increaseOrderOfElement(int key) {
    SkipListNode<T> curr = head;

    while(curr != null) {

      if(curr.getNext() == null || curr.getNext().getKey() >= key) {

        if(curr.getNext() != null && curr.getNext().getKey() == key) {

          curr.getNext().increaseOrder();
        }

        curr = curr.getDown();
      } else {
        curr = curr.getNext();
      }
    }
  }

  public void update(T data, int key) {
    SkipListNode<T> curr = head;

    while(curr != null) {

      if(curr.getNext() == null || curr.getNext().getKey() >= key) {

        if(curr.getNext() != null && curr.getNext().getKey() == key) {

          curr.getNext().setData(data);
        }

        curr = curr.getDown();
      } else {
        curr = curr.getNext();
      }
    }
  }

  //Delete the entire node regardless of the order
  public void delete(int key) {
    SkipListNode<T> curr = head;

    while(curr != null) {

      if(curr.getNext() == null || curr.getNext().getKey() >= key) {

        if(curr.getNext() != null && curr.getNext().getKey() == key) {
          curr.setNext(curr.getNext().getNext());
          listSize--;
        }

        curr = curr.getDown();
      } else {
        curr = curr.getNext();
      }
    }
  }


  //Decrese the order by one, if order is 0 remove node
  public void deleteOne(int key) {
    SkipListNode<T> curr = head;

    while(curr != null) {

      if(curr.getNext() == null || curr.getNext().getKey() >= key) {

        if(curr.getNext() != null && curr.getNext().getKey() == key) {

          curr.getNext().decreaseOrder();

          if(curr.getNext().getOrder() == 0) {
            curr.setNext(curr.getNext().getNext());
            listSize--;
          }
        }

        curr = curr.getDown();
      } else {
        curr = curr.getNext();
      }
    }
  }

  public boolean contains(int key) {
    SkipListNode<T> curr = head;

    while(curr != null) {
      if(curr.getNext() == null || curr.getNext().getKey() >= key) {

        if (curr.getNext() != null && curr.getNext().getKey() == key) {
          return true;
        }

        curr = curr.getDown();
      } else {
        curr = curr.getNext();
      }
    }

    return false;
  }

  private Optional<SkipListNode<T>> getNode(int key){
    SkipListNode<T> curr = head;

    while(curr != null) {
      if(curr.getNext() == null || curr.getNext().getKey() >= key) {

        if (curr.getNext() != null && curr.getNext().getKey() == key) {
          return Optional.of(curr.getNext());
        }

        curr = curr.getDown();
      } else {
        curr = curr.getNext();
      }
    }

    return Optional.empty();
  }

  public boolean isEmpty() {
    return listSize == 0;
  }

  public int getSize() {
    return listSize;
  }

  private int getLevel() {
    int lvl = 0;
    while(generator.nextBoolean() && lvl < maxLevel) {
      ++lvl;
    }

    return lvl;
  }

  @Override
  public String toString() {
    //Get lowest head
    SkipListNode<T> curr = head;
    while (curr.getDown() != null) {
      curr = curr.getDown();
    }

    StringBuilder result = new StringBuilder();
    result.append("[");

    while(curr.getNext() != null) {

      for(int i = 0; i < curr.getNext().getOrder(); ++i) {
        result.append(curr.getNext().getData());

        if (curr.getNext().getNext() != null || i < curr.getNext().getOrder() - 1) {
          result.append(", ");
        }
      }

      curr = curr.getNext();
    }

    result.append("]");
    return result.toString();
  }

  public List<T> toList() {

    List<T> list = new ArrayList<>();
    SkipListNode<T> curr = head;

    while (curr.getDown() != null) {
      curr = curr.getDown();
    }

    while (curr.getNext() != null) {
      for(int i = 0; i < curr.getNext().getOrder(); ++i) {
        list.add(curr.getNext().getData());
      }
      curr = curr.getNext();
    }

    return list;
  }
}
