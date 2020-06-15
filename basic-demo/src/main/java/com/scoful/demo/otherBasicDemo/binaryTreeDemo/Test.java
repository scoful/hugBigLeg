package com.scoful.demo.otherBasicDemo.binaryTreeDemo;

import java.util.*;

// https://blog.csdn.net/qq_43255303/article/details/89416512?depth_1-utm_source=distribute.pc_relevant.none-task&utm_source=distribute.pc_relevant.none-task
//把一个数组的值存入二叉树中，然后进行3种方式的遍历
public class Test {
    private static List<Node> nodeList = null;
    private int[] array = {1, 2, 3, 2};

    public static void main(String[] args) {
        Test binTree = new Test();
        binTree.createBinTree();
        // nodeList中第0个索引处的值即为根节点
        Node root = nodeList.get(0);
//        Node p = nodeList.get(9);
//        Node q = nodeList.get(10);
//        System.out.println("先序遍历：");
//        preOrderTraverse(root);
//        System.out.println();
//        System.out.println("中序遍历：");
//        inOrderTraverse(root);
//        System.out.println();
//        System.out.println("后序遍历：");
//        postOrderTraverse(root);

//        System.out.println(generateParenthesis(3));


        System.out.println(minDepth(root));
//        System.out.println(maxDepth(root));
//        System.out.println(lowestCommonAncestor(root, p, q).data);

//        Stack<Node> stack = new Stack<>();
//        Node node = root;
//        double inOrder = -Double.MAX_VALUE;
//        while (node != null || !stack.isEmpty()) {
//            if (node != null) {
//                stack.push(node);
//                node = node.leftChild;
//            } else {
//                Node tem = stack.pop();
//                if (inOrder < tem.data) {
//                    System.out.println("是二叉搜索树");
//                } else {
//                    System.out.println("不是");
//                }
//                inOrder = tem.data;
////                System.out.print(tem.data + "->");
//                node = tem.rightChild;
//            }
//        }


//        if (root == null) {
//            System.out.println("空的");
//        } else if (root.leftChild == null && root.rightChild == null) {
//            System.out.println("只有一个");
//        } else {
//            System.out.println("中序遍历：");
//            inOrderTraverse(root);
//            for (int i = 0; i < aa.size() - 1; i++) {
//                System.out.println("");
//                System.out.println(aa.get(i).data);
//                if (aa.get(i).data > aa.get(i + 1).data) {
//                    System.out.println("不是二叉搜索树");
//                } else {
//                    System.out.println("是");
//                }
//            }
//
//        }

//        System.out.println(isValidBST(root));
    }

    /**
     * 先序遍历
     * <p>
     * 这三种不同的遍历结构都是一样的，只是先后顺序不一样而已
     *
     * @param node 遍历的节点
     */
    public static void preOrderTraverse(Node node) {
        if (node == null)
            return;
        System.out.print(node.data + " ");
        preOrderTraverse(node.leftChild);
        preOrderTraverse(node.rightChild);
    }

    /**
     * 中序遍历
     * <p>
     * 这三种不同的遍历结构都是一样的，只是先后顺序不一样而已
     *
     * @param node 遍历的节点
     */
    public static void inOrderTraverse(Node node) {
        if (node == null)
            return;
        inOrderTraverse(node.leftChild);
        System.out.print(node.data + " ");
        inOrderTraverse(node.rightChild);
    }

    public static void inOrderTraverse2(Node node) {
        Stack<Node> stack = new Stack<>();
        while (node != null || !stack.isEmpty()) {
            if (node != null) {
                stack.push(node);
                node = node.leftChild;
            } else {
                Node tem = stack.pop();
                System.out.print(tem.data + "->");
                node = tem.rightChild;
            }
        }
    }

    /**
     * 后序遍历
     * <p>
     * 这三种不同的遍历结构都是一样的，只是先后顺序不一样而已
     *
     * @param node 遍历的节点
     */
    public static void postOrderTraverse(Node node) {
        if (node == null)
            return;
        postOrderTraverse(node.leftChild);
        postOrderTraverse(node.rightChild);
        System.out.print(node.data + " ");
    }

    public static List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        generateOneByOne("", res, n, n);
        return res;
    }

    private static void generateOneByOne(String s, List<String> res, int left, int right) {
        if (left == 0 && right == 0) {
            res.add(s);
            return;
        }
        if (left > 0) {
            generateOneByOne(s + "(", res, left - 1, right);
        }
        if (right > left) {
            generateOneByOne(s + ")", res, left, right - 1);
        }
    }

    public static int minDepth(Node root) {
        int res = 0;
        if (root == null) {
            return 0;
        }
        Queue<Node> q = new LinkedList<>();
        q.add(root);

        while (!q.isEmpty()) {
            int levelSize = q.size();

            for (int i = 0; i < levelSize; i++) {
                Node currNode = q.poll();
                if (currNode.leftChild == null && currNode.rightChild == null) {
                    q.clear();
                    break;
                }
                if (currNode.leftChild != null) {
                    q.add(currNode.leftChild);
                }
                if (currNode.rightChild != null) {
                    q.add(currNode.rightChild);
                }
            }
            res++;
        }
        return res;
    }

    public static int maxDepth(Node root) {
        int res = 0;
        if (root == null) {
            return 0;
        }
        Queue<Node> q = new LinkedList<>();
        q.add(root);

        while (!q.isEmpty()) {
            int levelSize = q.size();

            for (int i = 0; i < levelSize; i++) {
                Node currNode = q.poll();
                if (currNode.leftChild != null) {
                    q.add(currNode.leftChild);
                }
                if (currNode.rightChild != null) {
                    q.add(currNode.rightChild);
                }
            }
            res++;
        }
        return res;
    }

    public static Node lowestCommonAncestor(Node root, Node p, Node q) {
//        if (root == null || root == p || root == q) return root;
//        Node left = lowestCommonAncestor(root.leftChild, p, q);
//        Node right = lowestCommonAncestor(root.rightChild, p, q);
//        return left == null ? right : right == null ? left : root;
        if (root == null || root == p || root == q) {
            return root;
        }
        Node left = lowestCommonAncestor(root.leftChild, p, q);
        Node right = lowestCommonAncestor(root.rightChild, p, q);
        if (left == null) {
            return right;
        } else {
            if (right == null) {
                return left;
            } else {
                return root;
            }
        }
//        return left == null ? right : right == null ? left : root;
    }

    public static boolean helper(Node node, Integer lower, Integer upper) {
        if (node == null) return true;

        int val = node.data;
        if (lower != null && val <= lower) return false;
        if (upper != null && val >= upper) return false;

        if (!helper(node.rightChild, val, upper)) return false;
        if (!helper(node.leftChild, lower, val)) return false;
        return true;
    }

    public static boolean isValidBST(Node root) {
        return helper(root, null, null);
    }

    public void createBinTree() {
        if (array.length > 0) {
            nodeList = new LinkedList<Node>();
            // 将一个数组的值依次转换为Node节点
            for (int nodeIndex = 0; nodeIndex < array.length; nodeIndex++) {
                nodeList.add(new Node(array[nodeIndex]));
            }
            // 对前lastParentIndex-1个父节点按照父节点与孩子节点的数学关系建立二叉树
            for (int parentIndex = 0; parentIndex < array.length / 2 - 1; parentIndex++) {
                if (nodeList.get(parentIndex * 2 + 1).data != -1) {
                    // 左孩子
                    nodeList.get(parentIndex).leftChild = nodeList.get(parentIndex * 2 + 1);
                }
                if (nodeList.get(parentIndex * 2 + 2).data != -1) {
                    // 右孩子
                    nodeList.get(parentIndex).rightChild = nodeList.get(parentIndex * 2 + 2);
                }
            }
            // 最后一个父节点:因为最后一个父节点可能没有右孩子，所以单独拿出来处理
            int lastParentIndex = array.length / 2 - 1;
            // 左孩子
            if (array.length != 1) {
                nodeList.get(lastParentIndex).leftChild = nodeList.get(lastParentIndex * 2 + 1);
            }
            // 右孩子,如果数组的长度为奇数才建立右孩子
            if (array.length != 1 && array.length % 2 == 1) {
                nodeList.get(lastParentIndex).rightChild = nodeList.get(lastParentIndex * 2 + 2);
            }

        }
    }

    /**
     * 广度优先迭代写法
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            List<Integer> currLevel = new ArrayList<>();

            for (int i = 0; i < levelSize; i++) {
                Node currNode = queue.poll();
                currLevel.add(currNode.data);
                if (currNode.leftChild != null) {
                    queue.add(currNode.leftChild);
                }
                if (currNode.rightChild != null) {
                    queue.add(currNode.rightChild);
                }
            }
            res.add(currLevel);
        }
        return res;
    }

    public boolean isValidBST2(Node root) {
        Stack<Node> stack = new Stack();
        double inorder = -Double.MAX_VALUE;

        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.leftChild;
            }
            root = stack.pop();
            // If next element in inorder traversal
            // is smaller than the previous one
            // that's not BST.
            if (root.data <= inorder) return false;
            inorder = root.data;
            root = root.rightChild;
        }
        return true;
    }

}
