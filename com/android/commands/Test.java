package com.android.commands;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;

import android.app.ActivityThread;
import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;

public class Test {
    void main() throws NameNotFoundException, CertificateException, NoSuchAlgorithmException {
        Application app = ActivityThread.currentApplication();
        PackageManager pm = app.getPackageManager();
        String packageName = app.getPackageName();
        PackageInfo pmInfo = pm.getPackageInfo(packageName, 0x40);
        byte[] sign = pmInfo.signatures[0].toByteArray();

        CertificateFactory factory = CertificateFactory.getInstance("X.509");
        InputStream inStream = new ByteArrayInputStream(sign);
        Certificate certificate = factory.generateCertificate(inStream);
        byte[] e = certificate.getEncoded();
        MessageDigest digest = MessageDigest.getInstance("MD5");
        byte[] o = digest.digest(e);

    }
}