package com.kunyao.core.constant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.CodeSource;

public abstract class SystemConstant {

    public static final Logger logger = LoggerFactory.getLogger(SystemConstant.class);

    /**
     * line separator
     */
    public static final String LINE_SEPARATOR = System.getProperty("line.separator");

    /**
     * version
     */
    public static final String VERSION = "1.0.0";

    /**
     * GITHUB_URL
     */
    public static final String KUNYAO_GITHUB_URL = "https://github.com/yjn8888/kunyao";

    public static String getVersion(Class<?> cls) {
        try {
            // find version info from MANIFEST.MF first
            String version = cls.getPackage().getImplementationVersion();
            if (version == null || version.length() == 0) {
                version = cls.getPackage().getSpecificationVersion();
            }
            if (version == null || version.length() == 0) {
                // guess version fro jar file name if nothing's found from MANIFEST.MF
                CodeSource codeSource = cls.getProtectionDomain().getCodeSource();
                if (codeSource == null) {
                    logger.info("No codeSource for class " + cls.getName() + " when getVersion, use default version " + VERSION);
                } else {
                    String file = codeSource.getLocation().getFile();
                    if (file != null && file.length() > 0 && file.endsWith(".jar")) {
                        file = file.substring(0, file.length() - 4);
                        int i = file.lastIndexOf('/');
                        if (i >= 0) {
                            file = file.substring(i + 1);
                        }
                        i = file.indexOf("-");
                        if (i >= 0) {
                            file = file.substring(i + 1);
                        }
                        while (file.length() > 0 && !Character.isDigit(file.charAt(0))) {
                            i = file.indexOf("-");
                            if (i >= 0) {
                                file = file.substring(i + 1);
                            } else {
                                break;
                            }
                        }
                        version = file;
                    }
                }
            }
            // return default version if no version info is found
            return version == null || version.length() == 0 ? VERSION : version;
        } catch (Throwable e) {
            // return default version when any exception is thrown
            logger.error("return default version, ignore exception " + e.getMessage(), e);
            return VERSION;
        }
    }


}
