package com.six.data_structure.graph;

import java.util.Iterator;
import java.util.List;

/**
 * @author sixliu
 * @date 2017年12月29日
 * @email 359852326@qq.com
 * @Description
 */
public class VertexImpl<T> implements Vertex<T> {

	private T label;// 标识标点,可以用不同类型来标识顶点如String,Integer....
	private List<Edge> edgeList;// 到该顶点邻接点的边,实际以java.util.LinkedList存储
	private boolean visited;// 标识顶点是否已访问
	private Vertex<T> previousVertex;// 该顶点的前驱顶点
	private double cost;// 顶点的权值,与边的权值要区别开来

	public T getLabel() {
		return label;
	}

	public Iterator<Vertex<T>> getNeighborInterator() {
		return null;
	}
}
