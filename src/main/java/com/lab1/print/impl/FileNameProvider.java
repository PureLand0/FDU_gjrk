package com.lab1.print.impl;


import com.lab1.print.provider.NameProvider;

import java.io.File;

public class FileNameProvider implements NameProvider<File> {

    @Override
    public String getName(File file) {
        return file.getName();
    }
}