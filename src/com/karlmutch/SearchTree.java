/**
 * When was the last time you balanced a tree.  Probably before primates
 * roamed the rift valley.  Of course as the books and the people that stray
 * into this area will tell thousands of engineers are hired simply by
 * being adept as tree style ninja's.
 * 
 * Recursion and iteration questions abound but wait :
 * 
 * Recursion - Hardly ever do it much anymore
 * Iteration - I'm now using Java Streams with Lambda's
 * 
 * This all leaves me wondering if the people I know memorizing up on these 
 * problems end up as better engineers.
 * 
 */

package com.karlmutch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;
import java.util.stream.Collectors;

public class SearchTree<K extends Comparable<K>, V>
{
    private Optional<Node> mRoot;             // root of BST

    public class Node implements Comparable<Node>
    {
        public K mKey;
        public V mValue;
        public Optional<Node> mLeft = Optional.empty();
        public Optional<Node> mRight = Optional.empty();
        public int mNumInTree;

        public Node(K key, V value, int numInSubTree) 
        {
            mKey = key;
            mValue = value;
            mNumInTree = numInSubTree;
        }
        
        public int compareTo(Node otherNode)
        {
        	return(mKey.compareTo(otherNode.mKey));
        }
    }
    
    public SearchTree() {}

    public SearchTree(final List<Node> nodes)
    {
    	init(nodes);
    }

    public SearchTree(final Node [] nodes)
    {
    	List<Node> sortedNodes = Arrays.asList(nodes);
    	init(sortedNodes);
    }
    
    private void init(final List<Node> sortedNodes)
    {
    	Collections.sort(sortedNodes);
    	
    	mRoot = Optional.empty();
    	mRoot = rebalance(sortedNodes, 0, sortedNodes.size() - 1);
    }

    public boolean isValid()
    {
    	rebalance();
    	
    	return(isBalanced(mRoot, Optional.<Node>empty()));
    }

    public void rebalance()
    {
    	if (!mRoot.isPresent()) {
    		return;
    	}

    	ArrayList<Node> sortedNodes = new ArrayList<Node>();
    	traverseDepthOrder(sortedNodes, DepthVisit.InOrder);
    	Collections.sort(sortedNodes);

    	mRoot = Optional.empty();
    	mRoot = rebalance(sortedNodes, 0, sortedNodes.size() - 1);
    }

    private Optional<Node> rebalance(final List<Node> sortedNodes, int starting, int ending)
    {
    	if (starting <= ending) {
    		int midPoint = starting + (ending - starting) / 2;
    		Node newNode = new Node(sortedNodes.get(midPoint).mKey, 
    								sortedNodes.get(midPoint).mValue, 0);
    		
    		if (!mRoot.isPresent()) {
    			mRoot = Optional.of(newNode);
    		}

    		newNode.mLeft = rebalance(sortedNodes, starting, midPoint - 1);
    		newNode.mRight = rebalance(sortedNodes, midPoint + 1, ending);
    		
    		return(Optional.of(newNode));
    	}

    	return(Optional.empty());
    }

    private boolean isBalanced(Optional<Node> checkFromHere, Optional<Node> previous)
    {
    	if (checkFromHere.isPresent()) {
    		return(true);
    	}
    	
    	if (!isBalanced(checkFromHere.get().mLeft, checkFromHere)) {
    		return(false);
    	}

    	if (previous.isPresent() && 
    			(0 > checkFromHere.get().mKey.compareTo(previous.get().mKey)))
    	{
    		return(false);
    	}
    	
    	if (!isBalanced(checkFromHere.get().mRight, checkFromHere)) {
    		return(false);
    	}
    	
    	return(true);
    }

    private int size(Optional<Node> root)
    {
    	if (root.isPresent()) return(root.get().mNumInTree);

    	return(0);
    }
    
    public int height(Optional<Node> node)
    {
    	if (node.isPresent()) {
    		return(1 +  Math.max(height(node.get().mLeft), height(node.get().mRight)));
    	}
    	return(0);
    }

    public boolean contains(final K key) 
    {
        return get(key) != Optional.empty();
    }

    public Optional<V> get(final K key)
    {
        return get(mRoot, key);
    }

    private Optional<V> get(Optional<Node> root, K key) 
    {
        if (!root.isPresent()) return Optional.empty();

        int comprable = key.compareTo(root.get().mKey);
        if (comprable < 0) 
        	return get(root.get().mLeft, key);
        else 
        	if (comprable > 0) 
        		return get(root.get().mRight, key);
        	else
        		return Optional.of(root.get().mValue);
    }

    public void put(K key, V value)
    {
        mRoot = put(mRoot, key, value);
     
        rebalance();
    }

    private Optional<Node> put(Optional<Node> node, K key, V value) 
    {
    	if (!node.isPresent()) return Optional.of(new Node(key, value, 1));

        int comprable = key.compareTo(node.get().mKey);
        if (comprable < 0) 
        	node.get().mLeft  = put(node.get().mLeft,  key, value);
        else 
        	if (comprable > 0) 
        		node.get().mRight = put(node.get().mRight, key, value);
        	else
        		node.get().mValue = value;

        node.get().mNumInTree = 1 + size(node.get().mLeft) + size(node.get().mRight);

        return(node);
    }

    enum DepthVisit { PreOrder, InOrder, PostOrder } ;
    public ArrayList<Node> traverseDepthOrder(final ArrayList<Node> visited, final DepthVisit visitType)
    {
    	return(traverseDepthOrder(mRoot, visited, visitType));
    }

    // Recursion is the equivalent of a stack
    private ArrayList<Node> traverseDepthOrder(final Optional<Node> node, ArrayList<Node> visited, final DepthVisit visitType)
    {
    	if (node.isPresent()) {
    		if (visitType == DepthVisit.PreOrder) {
    			visited.add(node.get());
    		}
    		traverseDepthOrder(node.get().mLeft, visited, visitType);
    		if (visitType == DepthVisit.InOrder) {
    			visited.add(node.get());
    		}
    		traverseDepthOrder(node.get().mRight, visited, visitType);
    		if (visitType == DepthVisit.PostOrder) {
    			visited.add(node.get());
    		}
    	}
    	return(visited);
    }

    // AKA breadth-first
    public Iterable<K> traverseLevelOrder() 
    {
        Queue<K> keys = new PriorityQueue<K>();
        PriorityQueue<Optional<Node>> queue = new PriorityQueue<Optional<Node>>();
        queue.offer(mRoot);
        while (!queue.isEmpty()) 
        {
            Optional<Node> node = queue.poll();

            if (!node.isPresent()) continue;

            keys.offer(node.get().mKey);
            queue.offer(node.get().mLeft);
            queue.offer(node.get().mRight);
        }
        return(keys);
    }

    public Iterable<K> traverseSpiral()
    {
    	ArrayList<K> visited = new ArrayList<K>();

        Stack<Optional<Node>> leftToRight = new Stack<Optional<Node>>();
        Stack<Optional<Node>> rightToLeft = new Stack<Optional<Node>>();

        leftToRight.push(mRoot);

        while (!leftToRight.empty() || !rightToLeft.empty()) 
        {
            while (!leftToRight.empty()) {
                Optional<Node> node = leftToRight.pop();
                if (node.isPresent()) 
                {
                    visited.add(node.get().mKey);
                    rightToLeft.push(node.get().mRight);
                    rightToLeft.push(node.get().mLeft);
                }
            }

            while (!rightToLeft.empty()) {
                Optional<Node> node = rightToLeft.pop();
                if (node.isPresent()) 
                {
                    visited.add(node.get().mKey);
                    leftToRight.push(node.get().mLeft);
                    leftToRight.push(node.get().mRight);
                }
            }
        }
        return(visited);
    }

    public void print() 
    {
        printNodes(Collections.singletonList(mRoot), 1, height(mRoot));
    }

    private void printNodes(final List<Optional<Node>> nodes, int level, int maxLevel) 
    {
    	List<Optional<Node>> filtered = nodes.stream().filter(n -> n.isPresent()).collect(Collectors.<Optional<Node>>toList());

        if ((level > maxLevel) || filtered.isEmpty()) {
            return;
        }

        final int floor = maxLevel - level;
        final int firstSpaces = (int) Math.pow(2, (floor)) - 1;
        final int betweenSpaces = (int) Math.pow(2, (floor + 1)) - 1;

    	StringBuilder currentLine = new StringBuilder();
    	currentLine.append(new String(new char[firstSpaces]).replace("\0", " "));

        List<Optional<Node>> newNodes = new ArrayList<Optional<Node>>();
        for (Optional<Node> node : nodes) {
            if (node.isPresent()) {
                currentLine.append(node.get().mKey);
                newNodes.add(node.get().mLeft);
                newNodes.add(node.get().mRight);
            } 
            else {
                newNodes.add(Optional.empty());
                newNodes.add(Optional.empty());
                currentLine.append(" ");
            }

        	currentLine.append(new String(new char[betweenSpaces]).replace("\0", " "));
        }

        System.out.println(currentLine.toString());
        currentLine = new StringBuilder();

        if (level == maxLevel) {
        	return;
        }

        final int interLayerLines = (int) Math.pow(2, (Math.max(floor - 1, 0)));

        for (int i = 1 ; i <= interLayerLines ; i++) 
        {
            for (int j = 0 ; j < nodes.size() ; j++) 
            {
            	currentLine.append(new String(new char[firstSpaces - i]).replace("\0", " "));

                if (!nodes.get(j).isPresent()) {
                	currentLine.append(new String(new char[interLayerLines + interLayerLines + i + 1]).replace("\0", " "));
                    continue;
                }

                if (nodes.get(j).get().mLeft.isPresent()) {
                	currentLine.append("/");
                }
                else {
                	currentLine.append(" ");
                }

            	currentLine.append(new String(new char[i + i - 1]).replace("\0", " "));

                if (nodes.get(j).get().mRight.isPresent()) {
                	currentLine.append("\\");
                }
                else {
                	currentLine.append(" ");
                }

            	currentLine.append(new String(new char[interLayerLines + interLayerLines - i]).replace("\0", " "));
            }

            System.out.println(currentLine.toString());

            currentLine = new StringBuilder();
        }

        printNodes(newNodes, level + 1, maxLevel);
    }
}
