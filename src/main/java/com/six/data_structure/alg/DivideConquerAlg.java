package com.six.data_structure.alg;

/**
 * @author:MG01867
 * @date:2018年10月17日
 * @email:359852326@qq.com
 * @version:
 * @describe //TODO
 */
public class DivideConquerAlg {

	public static void main(String[] args) {
		Block block = new Block(19872, 608);
		Block maxBlock = maxBlockByLoop(block);
		System.out.println(maxBlock);
	}

	/**
	 * 求最大方块
	 * 
	 * @param block
	 * @return
	 */
	public static Block maxBlock(Block block) {
		if (block.getLength() == block.getWidth()) {
			return block;
		}
		Block newBlock = new Block();
		int newLength=0;
		int newWidth=0;
		if (block.getLength() > block.getWidth()) {
			newLength=block.getLength()%block.getWidth();
			if(0==newLength) {
				newLength=block.getWidth();
			}
			newWidth=block.getWidth();
		} else {
			newLength=block.getWidth()%block.getLength();
			if(0==newLength) {
				newLength=block.getLength();
			}
			newWidth=block.getLength();
		}
		newBlock.setLength(newLength);
		newBlock.setWidth(newWidth);
		return maxBlock(newBlock);
	}
	
	public static Block maxBlockByLoop(Block block) {
		while(true) {
			if (block.getLength() == block.getWidth()) {
				return block;
			}else {
				Block newBlock = new Block();
				int newLength=0;
				int newWidth=0;
				if (block.getLength() > block.getWidth()) {
					newLength=block.getLength()%block.getWidth();
					if(0==newLength) {
						newLength=block.getWidth();
					}
					newWidth=block.getWidth();
				} else {
					newLength=block.getWidth()%block.getLength();
					if(0==newLength) {
						newLength=block.getLength();
					}
					newWidth=block.getLength();
				}
				newBlock.setLength(newLength);
				newBlock.setWidth(newWidth);
				block=newBlock;
			}
		}
	}
}
