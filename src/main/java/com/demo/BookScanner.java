package com.demo;

import com.demo.common.model.Mybooks;
import com.jfinal.plugin.activerecord.Db;

import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nilszhang on 2016/3/22.
 */
public class BookScanner implements Runnable {

    public final static String BOOK_URI = "D:\\shucat";

    public void run() {
        try {

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception  {
        BookScanner kanHouseWatcher = new BookScanner();
        kanHouseWatcher.scanBook();
    }

    public void scanBook()  throws Exception {
        filelist = new ArrayList<String>();

        //Mybooks.dao.delete();
        getFiles(BOOK_URI);

    }
    static List<String> filelist;
    static DecimalFormat df = new DecimalFormat("0.000");// 以Mb为单位保留两位小数
    /*
 * 通过递归得到某一路径下所有的目录及其文件
 */
    static void getFiles(String filePath){
        File root = new File(filePath);
        File[] files = root.listFiles();
        for(File file:files){
            if(file.isDirectory()){
                getFiles(file.getAbsolutePath());
//                filelist.add(file.getAbsolutePath());
//                System.out.println("显示"+filePath+"下所有子目录及其文件"+file.getAbsolutePath());
            }else{

                String absolutePath = file.getAbsolutePath();

                String bookpos = absolutePath.substring(BOOK_URI.length());
                String[] split = bookpos.split("\\\\");


                try {
                    List result = Db.query("select count(*) from mybooks t where t.book_uri = '"+bookpos.replaceAll("\\\\","\\\\\\\\")+"'");
                    Object o = result.get(0);
                    int count  = Integer.parseInt(o.toString());

                    if(count>0) continue;


                    Mybooks book = new Mybooks();

                    Double size = (file.length() + 0.0) / (1024 * 1024);

                    String filesize = df.format(size);


                    book.setBookSize(filesize);
                    book.setBookUri(bookpos);
                    for (int i = 1; i < split.length; i++) {
                        String bookInfo =  split[i];
                        if(i==split.length-1)
                        {
                            book.setBookName(bookInfo);
                            String[] bookSp = bookInfo.split("\\.");
                            book.setBookFormat(bookSp[bookSp.length-1]);
                            break;
                        }
                        if(book.getTags()==null)
                            book.setTags(bookInfo);
                         else
                            book.setTags(bookInfo+","+book.getTags());
                        System.out.println(bookInfo);
                    }
                    book.save();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
    }

}
