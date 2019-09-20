package nio;

import java.nio.ByteBuffer;

/**
 * 缓冲区:负责读取数据。缓冲区是数组
 * 根据数据类型（boolean除外），提供相应类型的缓冲区
 * ByteBuffer
 * CharBuffer
 * IntBuffer
 * LongBuffer
 * ...
 * 上述缓冲区管理方式基本一致，通过allocate()分配缓冲区
 * put():存入数据到缓冲区
 * get():获取缓冲区中的数据
 * <p>
 * 缓冲区的4个核心属性：
 * capacity：容量，表示缓冲区中最大存储数据的容量。声明后不能改变。
 * limit：界限，表示缓冲区中可以操作数据的大小。
 * position:位置，表示缓冲区中正在操作的数据的位置。
 * mark:标记，记录当前position到位置，可以通过rest()恢复到mark到位置
 *
 * mark <= position <= limit <= capacity
 *
 * 5 直接缓冲区和非直接缓冲区
 * 非直接缓冲区：通过allocate()方法分配缓冲区，将缓冲区建立在JVM的内存中
 * 直接缓冲区：通过allocateDirect()方法分配内存，将缓冲区建立在物理内存中，可以提高效率
 */
public class TestBuffer {


    public static void main(String[] args) {
        String str = "abcde";
        //1 分配一个指定大小的缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        System.out.println("------------allocate----------");
        System.out.println(buffer.position());
        System.out.println(buffer.limit());
        System.out.println(buffer.capacity());

        //2 使用put()写入缓冲区
        System.out.println("------------put----------");
        buffer.put(str.getBytes());
        System.out.println(buffer.position());
        System.out.println(buffer.limit());
        System.out.println(buffer.capacity());

        //3 读取数据前需要使用flip切换到读取数据模式
        buffer.flip();
        System.out.println("------------flip----------");
        System.out.println(buffer.position());
        System.out.println(buffer.limit());
        System.out.println(buffer.capacity());

        //4 使用get()读取缓冲区到数据
        byte[] dest = new byte[buffer.limit()];
        buffer.get(dest);

        System.out.println("------------get----------");
        System.out.println(buffer.position());
        System.out.println(buffer.limit());
        System.out.println(buffer.capacity());

        //5 rewind(),可重复读数据，就是令position=0，把读下标移到数组头
        buffer.rewind();
        System.out.println("------------rewind----------");
        System.out.println(buffer.position());
        System.out.println(buffer.limit());
        System.out.println(buffer.capacity());

        //6 clear(),清空缓冲区，但是缓冲区数据依然存在，实质是把3个指针归零
        buffer.rewind();
        System.out.println("------------clear----------");
        System.out.println(buffer.position());
        System.out.println(buffer.limit());
        System.out.println(buffer.capacity());
        //读取一个字节，仍然能打印出字节
        System.out.println((char) buffer.get());

        test2();
        test3();
    }

    public static void test2() {
        System.out.println("----------mark-----------");
        String str = "abcde";
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        buffer.put(str.getBytes());

        buffer.flip();
        byte[] dst = new byte[buffer.limit()];
        buffer.get(dst, 0, 2);
        System.out.println(new String(dst, 0, 2));
        System.out.println("position:"+buffer.position());

        //mark标
        buffer.mark();
        buffer.get(dst, 2, 2);
        System.out.println(new String(dst, 2, 2));
        System.out.println(buffer.position());

        //reset恢复到mark的位置
        buffer.reset();
        System.out.println("reset position:"+buffer.position());

        //判断缓冲区是否有可以操作的剩余数据
        if(buffer.hasRemaining()){
            System.out.println("剩余数据量："+buffer.remaining());
            byte[] dest = new byte[3];
            buffer.get(dest);
            System.out.println(new String(dest));
        }
    }

    //直接缓冲区
    public static void test3(){
        ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
        System.out.println(buffer.isDirect());
    }
}
