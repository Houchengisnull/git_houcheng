[TOC]

# 术语

* [根结点]无双亲的结点，一棵树最多一个根结点

* [边]双亲与孩子结点的链接

* [叶子结点]无孩子结点的结点

* [兄弟结点]拥有相同双亲的结点

* [祖先结点]如果存在一条根结点到结点q的路径，并且p在该路线上，则p为q的祖先结点

* [结点大小]子孙个数，包括其自身

* [层]位于相同深度的所有结点的集合叫做树的层

* [结点深度]根结点到该结点的路径长度

* [结点高度]从该结点到最深结点的路径长度

* [树的高度]树中所有结点高度最大值 / 根结点到最深结点的路径长度

  > 深度与高度为分别以***根结点***与***最深结点***为***起点***的标量

* [斜树]类链表的树，即除叶子结点每个结点仅一个孩子结点



# 二叉树

## 二叉树类型

- [严格二叉树] 除叶子结点外，每个结点均有两个孩子结点

- [满二叉树] ***所有叶子结点都在同一层***的严格二叉树
- [完全二叉树] 从左到右自上而下结点有序无遗漏的二叉树

## 二叉树性质

https://www.cnblogs.com/willwu/p/6007555.html

## 二叉树结构

```java
public class BinaryTreeNode {
	// 省事
	public int data;
	public BinaryTreeNode left;
	public BinaryTreeNode right;
	
}
```

## 二叉树常用操作

- 基本操作
  - 插入
  - 删除
  - 查找
  - 遍历

- 辅助操作
  - 获取树的大小
  - 获取树的高度
  - 获取其和最大的层
  - 对于两个或多个结点，找出他们最近的公共结点

## 二叉树应用

* 编译器表达式树
* 数据压缩算法中的哈夫曼树
* 支持在集合中查找，插入和删除，其平均时间复杂度为O(logn)的二叉搜索查找树（BST）
* 优先队列（PQ），它支持以对数时间（最坏情况）对集合中的最小或最大数据进行搜索和删除

## 非递归前序遍历  

```java
/**
	 * 中左右
	 * 非递归前续遍历
	 */
	public void preOrderNoRecursive(BinaryTreeNode root) {
		LinkedList<BinaryTreeNode> stack = new LinkedList<>();
		while (true) {
			while (root != null) {
				System.out.print(root.data);
				stack.push(root);
				root = root.left;
			}
			if (stack.size() == 0) {
				break;
			}
			root = stack.pop();
			root = root.right;
		}
	}
```



## 非递归中序遍历  

```java
/**
	 * 左中右
	 * 非递归中序遍历
	 * @param root
	 */
	public void inOrderNoRecursive(BinaryTreeNode root) {
		LinkedList<BinaryTreeNode> stack = new LinkedList<>();
		while (true) {
			while (root != null) {
				stack.push(root);
				root = root.left;
			}
			if (stack.size() == 0) {
				break;
			}
			root = stack.pop();
			System.out.print(root.data);
			root = root.right;
		}
	}
```



## 非递归后序遍历  

```java
/**
	 * 左右中
	 * 非递归后序遍历
	 * 
	 * 递推与递归不同的是,递推方法采用自底向上的方式产生计算序列,其首先计算规模问题最小的子问题的解,在此基础上依次计算规模较大的子问题的解,直到最后产生原问题的解
	 * 大多数递归问题在求解过程中无法保证求解动作一直向前,往往需要设置一些回溯点
	 * 
	 * 实现关键: 
	 * 检查这个元素与栈顶元素的右子结点是否相同
	 * 如果相同，则说明已经完成左右子树遍历。
	 * 此时,只需要将栈顶元素出栈一次并输出该结点数据即可。
	 * 
	 * @param root
	 */
	public void postOrderNoRecursive(BinaryTreeNode root) {
		LinkedList<BinaryTreeNode> stack = new LinkedList<>();
		while (true) {
			if (root != null ){ // 左子树遍历
				stack.push(root);
				root = root.left;
			} else {
				if (stack.size() == 0) {
					return;
				} else {
					if (stack.getFirst().right == null) {
						root = stack.pop();
						System.out.print(root.data);
						if (root == stack.getFirst().right) {
							System.out.print(stack.getFirst().data);
							stack.pop();
						}
					}
				}
				if (stack.size() > 0) {
					root = stack.getFirst().right;
				}else {
					root = null;
				}
			}
		}
	}
```
