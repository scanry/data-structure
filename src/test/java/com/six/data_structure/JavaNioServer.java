package com.six.data_structure;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.util.Iterator;

public class JavaNioServer {

	public static void main(String[] args) throws IOException {
		Selector selector=SelectorProvider.provider().openSelector();
		ServerSocketChannel socketChanne =ServerSocketChannel.open();
		socketChanne.configureBlocking(false);
		socketChanne.socket().bind(null);
		socketChanne.register(selector, SelectionKey.OP_ACCEPT);
		while(true){
			selector.select();
			Iterator<SelectionKey> selectorKeys =selector.selectedKeys().iterator();  
            while (selectorKeys.hasNext()) {  
                System.out.println("running2 ... ");  
                //这里找到当前的选择键     
                SelectionKey key = (SelectionKey) selectorKeys.next();  
                //然后将它从返回键队列中删除     
                selectorKeys.remove();  
                if (!key.isValid()) { // 选择键无效  
                    continue;  
                }  
                if (key.isAcceptable()) {  
                    //如果遇到请求那么就响应     
                	 SocketChannel clientChannel = ((ServerSocketChannel) key.channel()).accept();  
                     clientChannel.configureBlocking(false);  
                     clientChannel.register(selector, SelectionKey.OP_READ,  
                             ByteBuffer.allocate(1024));  
                } else if (key.isReadable()) {  
                    //读取客户端的数据     
                }  
            }  
		}
	}

}
