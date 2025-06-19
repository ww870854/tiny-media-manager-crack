package org.tinymediamanager.license;

import net.lingala.zip4j.io.outputstream.ZipOutputStream;
import org.tinymediamanager.license.impl.ZipArchiveHelperImpl;

import java.io.FileOutputStream;
import java.io.IOException;

public interface ZipArchiveHelper {

    static ZipArchiveHelper getInstance() {
        return ZipArchiveHelperImpl.getInstance();
    }

    ZipOutputStream createEncryptedZipOutputStream(FileOutputStream fileOutputStream) throws IOException;
}
