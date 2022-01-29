package com.scoful.demo.otherBasicDemo.fileAndStream;

import java.io.*;

/**
 * @author scoful
 * @date 2020/7/20 23:13
 */
public class Test {

    public static void main(String[] args) {
//        fileToInputStream();
        inputStreamToFile();
    }

    private static void fileToInputStream() {
        String path = "C:\\Users\\scoful\\Desktop\\upload.xlsx";
        FileInputStream fileInputStream = null;
        BufferedReader bufferedReader = null;
        try {
            fileInputStream = new FileInputStream(path);
            bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            System.out.println(stringBuilder.toString());
            System.out.println("\nDone!");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void inputStreamToFile() {
        String path = "C:\\Users\\scoful\\Desktop\\upload.xlsx";
        InputStream fileInputStream = null;
        OutputStream fileOutputStream = null;
        try {
            fileInputStream = new FileInputStream(path);
            File file = new File("C:\\Users\\scoful\\Desktop\\new_upload.xlsx");
            fileOutputStream = new FileOutputStream(file);

            int bytesWritten = 0;
            int byteCount;

            byte[] bytes = new byte[111];

            while ((byteCount = fileInputStream.read(bytes)) != -1) {
                fileOutputStream.write(bytes, bytesWritten, byteCount);
                bytesWritten += byteCount;
            }
            System.out.println("Done!");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.flush();
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }


}
