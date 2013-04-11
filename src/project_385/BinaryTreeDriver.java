package project_385;

public class BinaryTreeDriver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ADTBinaryTree<String> tree=new ADTBinaryTree<String>();
		tree.setRoot("A");
		tree.getRoot().setLeftItem("B");
		tree.getRoot().setRightItem("C");
		tree.getRoot().getLeftChild().setLeftItem("D");
		tree.getRoot().getRightChild().setLeftItem("E");
		tree.getRoot().getRightChild().setRightItem("F");
		System.out.println("Preorder : " + tree.processPreOrder());
		System.out.println("Inorder  : " + tree.processInOrder());
		System.out.println("Postorder: " + tree.processPostOrder());
	}

}
