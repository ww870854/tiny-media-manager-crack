package org.tinymediamanager.license.impl;

import net.lingala.zip4j.io.outputstream.ZipOutputStream;
import net.lingala.zip4j.model.ZipParameters;
import org.tinymediamanager.license.ZipArchiveHelper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;

public final class ZipArchiveHelperImpl implements ZipArchiveHelper {

    // 压缩包加密密码
    private static final char[] PASSWORD = new char[] {'1', '2', '3', '4', '5', '6'};

    private static ZipArchiveHelperImpl instance;

    public static synchronized ZipArchiveHelperImpl getInstance() {
        if (Objects.isNull(instance)) {
            instance = new ZipArchiveHelperImpl();
        }
        return instance;
    }

    @Override
    public ZipOutputStream createEncryptedZipOutputStream(FileOutputStream fileOutputStream) throws IOException {
        ZipOutputStream zipOutputStream = new ZipOutputStream(fileOutputStream, PASSWORD);

        ZipParameters zipParameters = new ZipParameters();
        zipParameters.setFileNameInZip("解压密码：" + new String(PASSWORD));

        zipOutputStream.putNextEntry(zipParameters);
        zipOutputStream.closeEntry();

        return zipOutputStream;
    }

}
