package com.scoful;

import cn.hutool.http.HttpUtil;
import cn.hutool.poi.excel.ExcelUtil;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

/**
 * @author scoful
 * @date 2020/7/20 22:19
 */
public class Scoful {

    public static void main(String[] args) throws Exception {

        OutputStream bout = new ByteArrayOutputStream();

        long download = HttpUtil.download("http://lulua.oss-cn-beijing.aliyuncs.com/upload.xlsx", bout, true);
        System.out.println(download);
        int sheetCount = ExcelUtil.getReader(parse(bout))
                                  .getSheetCount();
        for (int i = 0; i < sheetCount; i++) {
            List<Map<String, Object>> maps = ExcelUtil.getReader(parse(bout), i)
                                                      .readAll();
            System.out.println(maps);
        }

        if (bout != null) {
            bout.flush();
            bout.close();
        }




    }

    public static ByteArrayInputStream parse(final OutputStream out) throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        baos = (ByteArrayOutputStream) out;
        final ByteArrayInputStream swapStream = new ByteArrayInputStream(baos.toByteArray());
        return swapStream;
    }


}
